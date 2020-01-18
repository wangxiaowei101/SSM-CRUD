package com.wxw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxw.bean.Departmet;
import com.wxw.bean.Msg;
import com.wxw.service.DepartmentService;

@Controller
public class DepartmentController {

	
	@Autowired
	private DepartmentService DepartmentService;
	
	/**
	 * 返回所有部门的信息
	 * 
	 */
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDepts() {
	List<Departmet> list =	DepartmentService.getDepts();
	return Msg.success().add("depts", list);
	}
}
