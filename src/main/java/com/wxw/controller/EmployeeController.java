package com.wxw.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wxw.bean.Employee;
import com.wxw.bean.Msg;
import com.wxw.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService	employeeService;
	
	/**单个批量二合一
	 * 删除员工
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{ids}",method = RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmp(@PathVariable("ids") String ids ) {
		//批量删除
		  if(ids.contains("-"))  {
			  List<Integer> del_ids=new ArrayList<>();
			  String[] str_ids = ids.split("-");
			  
			  for (String string : str_ids) {
				  del_ids.add( Integer.parseInt(string));
				
				  employeeService.deleteBatch(del_ids);
			}
			  
		  }else {
			  Integer id = Integer.parseInt(ids);
			      employeeService.deleteEmp(id);
		  }  
		  
		
		
		return Msg.success();
		
	}
	
	/**如果直接发送ajaxput形式请求
	 * 封装数据直接出empId之外都为空
	 * 员工更新
	 * 原因：
	 * Tomcat：
	 * 1，将请求中的数据封装成map
	 * 2，request.getParameter("empName")就会从map取值
	 * 3，SpringMVC封装POJO对象的时候request.getParameter("empName")；
	 * Ajax发送PUT请求引发的血案
	 * PUT请求的数据request.getParameter("empName")都拿不到
	 * Tomcat一看是put请求不会封装请求中数据为map，只有POST才会封装为Map
	 * 
	 * 会把POJO中的每个属性的值，
	 * 解决方案
	 * 我们要能直接发送PUT之类的请求需要自己封装，配置上HttpPutFormContentFilter
	 * 他的作用是将请求体中的数据解析包装成map
	 * request被重新包装，request.getParameter被重写，就会从自己封装的map中取值
	 * 
	 * 	 * @param employee
	 * @return
	 */
	
	@RequestMapping(value="/emp/{empId}",method = RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee) {
		
		 employeeService.updateEmp(employee);
		
		return Msg.success();
	}
	
	/**
	 * 根据id查询员工
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id) {
	 Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	/**
	 * 员工保存
	 * 1.支持JSR303校验
	 * @param employee
	 * @return
	 */
	
	@RequestMapping(value="/emp",method = RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			//校验失败，返回失败，在模态框中显示校验失败的错误信息
			Map<String,Object> map =new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}
		
	}
	/**
	 * 检查用户名是否可用
	 * @param empName
	 * @return
	 */
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkuse(@RequestParam("empName") String empName) {
	//判断用户名是否是合法的表达式
		String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!empName.matches(regx)) {
			return Msg.fail().add("va_msg","用户名必须是6-16位数字和字母的组合或者2-5位中文" );
					
		};
		//数据库用户名重复校验
		boolean b= 	employeeService.checkUser(empName);
	
	if(b) {
		return Msg.success();
	}else {
		return Msg.fail().add("va_msg", "用户名不可用");
	}
		
	}
	
	

	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue = "1")Integer pn) {
		//在查询之前调用分页插件,传入页码以及每页的大小
		PageHelper.startPage(pn, 5);
		//startPage后面紧跟的这个查询就是一个分页查询
	List<Employee> emps =  employeeService.getAll();
	//使用pageInfo包装查询结果，只需要将pageInfo交给页面
	//封装了详细的分页信息，包括我们查询出来的数据,连续传入显示的页数
       PageInfo page= 	new PageInfo(emps,5);
       
       return Msg.success().add("PageInfo", page);
       
		
	}
/**
 * 查询员工（分页）
 * @return
 */
//	@RequestMapping("/emps")
//	public String getEmps(@RequestParam(value="pn",defaultValue = "1")Integer pn
//			,Model model
//			) {
//		//在查询之前调用分页插件,传入页码以及每页的大小
//		PageHelper.startPage(pn, 5);
//		//startPage后面紧跟的这个查询就是一个分页查询
//	List<Employee> emps =  employeeService.getAll();
//	
//
//		
//	
//	//使用pageInfo包装查询结果，只需要将pageInfo交给页面
//	//封装了详细的分页信息，包括我们查询出来的数据,连续传入显示的页数
//   PageInfo page= 	new PageInfo(emps,5);
//	model.addAttribute("pageInfo",page);
//	
//	
//		return "list";
//	}
}
