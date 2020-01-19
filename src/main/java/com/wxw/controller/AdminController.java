package com.wxw.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wxw.bean.User;
import com.wxw.service.EmployeeService;
import com.wxw.utils.CpachaUtil;






@Controller
public class AdminController {
	
	@Autowired
	EmployeeService ss;
	
	

	@RequestMapping("index")
	public String selectAll1() {
		
	
		return "forward:/index.jsp" ;
	}  


	/**
	 * 登陆控制器
	 * @param username
	 * @param password
	 * @param vcode
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(
			@RequestParam(value="username",required = true) String username,
			@RequestParam(value="password",required = true) String password, 
			@RequestParam(value="vcode",required = true) String vcode,
			 HttpServletRequest request,HttpSession session
			){
		Map<String,String> ret = new HashMap<String,String>();
		if(StringUtils.isEmpty(username)) {
			ret.put("type", "error");
			ret.put("msg", "用户名不能为空！");
			return ret;
		
		}
		if(StringUtils.isEmpty(password)) {
			ret.put("type", "error");
			ret.put("msg", "密码不能为空！");
			return ret;
		}
		if(StringUtils.isEmpty(vcode)) {
			ret.put("type", "error");
			ret.put("msg", "验证码不能为空！");
			return ret;
		}
		
		String loginCpacha  = (String) request.getSession().getAttribute("loginCpacha");
		if(StringUtils.isEmpty(loginCpacha)) {
			ret.put("type", "error");
			ret.put("msg","长时间未操作，会话已失效，请刷新后重试"); 
			return ret;
		}
		if(!vcode.toUpperCase().equals(loginCpacha.toUpperCase())) {
			ret.put("type", "error");
			ret.put("msg","验证码错误!");
			return ret;
		}
		request.getSession().setAttribute("loginCpacha",null);
		
	
			
		User user = ss.findByUserName(username);
		 	
	 		
	 		if(user==null) {
	 			  ret.put("type","error");
	 			  ret.put("msg", "不存在该用户！");
	 			 return ret;
	 		}
	 		if(!password.equals(user.getPassword())) {
	 			ret.put("type", "error");
	 			ret.put("msg", "密码错误！");
	 			return ret;
	 		}
	 		
	 		
		
	 		ret.put("type","success");
			ret.put("msg", "登陆成功");
			session.setAttribute("user", username);
			
			
			return ret; 
		
	}
	

	@RequestMapping("loginOut")
	public String loginOut(HttpServletRequest request,HttpSession session) {
		session.removeAttribute("user");
		request.setAttribute("msg1", "退出成功");
	   
		return "forward:/login.jsp" ;
	}  
	
	
	@RequestMapping(value="get_cpacha", method=RequestMethod.GET)
	public  void getcpacha(HttpServletRequest requset ,
			@RequestParam(value="v1",defaultValue = "4",required=false) Integer v1,
			@RequestParam(value="w",defaultValue = "98",required=false) Integer w,
			@RequestParam(value="h",defaultValue = "33",required=false) Integer h,
			
			
			HttpServletResponse response) {
		
		CpachaUtil cpachaUtil = new CpachaUtil(v1,w,h);
		String generatorVCode = cpachaUtil.generatorVCode();
		requset.getSession().setAttribute("loginCpacha", generatorVCode);
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		 try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	
}

