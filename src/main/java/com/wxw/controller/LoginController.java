package com.wxw.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wxw.bean.User;
import com.wxw.service.UserService;
import com.wxw.utils.CpachaUtil;



@Controller
public class LoginController {
	
    @Autowired
	UserService userService;
	
	@RequestMapping(value="index", method=RequestMethod.GET)
	public  ModelAndView index(ModelAndView model) {
		model.setViewName("index");
		return model;
	} 
	@RequestMapping(value="login1", method=RequestMethod.GET)
	public  ModelAndView login(ModelAndView model) {
		model.setViewName("login");
		return model;
	} 
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> login(
			@RequestParam(value="username",required = true) String username,
			@RequestParam(value="password",required = true) String password, 
			 HttpServletRequest request
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
		
		
	
	

		//1.获取subject主体
		Subject subject = SecurityUtils.getSubject();
		//2.创建令牌
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
	     
		//3.登陆
		try {
			subject.login(token);
		Session session = subject.getSession();
		User user = userService.findUserByName(username);
		session.setAttribute("user", user);
		ret.put("type","success");
		ret.put("msg", "登陆成功");	
		System.out.println("!1111111111111111111111111111111111");
		return ret; 
		}catch(Exception e) {
			e.printStackTrace();
			ret.put("type","error");
			ret.put("msg", "用户名或密码错误！");	
			return ret; 	
		}
		
		
	}
	@RequestMapping(value="cancellation",method=RequestMethod.GET)
	public String Cancellation(HttpServletRequest requset,HttpSession session) {
		
		session.removeAttribute("user");
		Enumeration<String> attributeNames = session.getAttributeNames();
		/*
		 * while(attributeNames.hasMoreElements()) { String name1 =
		 * attributeNames.nextElement().toString(); Object VALUE =
		 * requset.getSession().getAttribute(name1);
		 * System.out.println(name1+":"+VALUE.toString()); }
		 */
		session.invalidate();

		requset.setAttribute("msg", "退出成功");
		
		return "login";
	
		
	}
	

	/*
	 * @RequestMapping(value="get_cpacha", method=RequestMethod.GET) public void
	 * getcpacha(HttpServletRequest requset ,
	 * 
	 * @RequestParam(value="v1",defaultValue = "4",required=false) Integer v1,
	 * 
	 * @RequestParam(value="w",defaultValue = "98",required=false) Integer w,
	 * 
	 * @RequestParam(value="h",defaultValue = "33",required=false) Integer h,
	 * 
	 * 
	 * HttpServletResponse response) {
	 * 
	 * CpachaUtil cpachaUtil = new CpachaUtil(v1,w,h); String generatorVCode =
	 * cpachaUtil.generatorVCode(); requset.getSession().setAttribute("loginCpacha",
	 * generatorVCode); BufferedImage generatorRotateVCodeImage =
	 * cpachaUtil.generatorRotateVCodeImage(generatorVCode, true); try {
	 * ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
	 * } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

}
