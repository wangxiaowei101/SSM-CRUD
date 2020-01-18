package com.wxw.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.wxw.bean.Employee;

/**
 * 测试请求功能
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class MvcTest {                                                   
	//传入springmvc的ioc
	@Autowired
	WebApplicationContext context;
	//虚拟mvc请求
	MockMvc mockMvc;
	@Before
	public void initMokcMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testPage() throws Exception {
		//模拟请求拿到返回值
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "5")).andReturn();
		//请求成功以后，请求域中会有pageInfo，我们可以取出验证
		MockHttpServletRequest request = result.getRequest();
		PageInfo<Employee> pi = (PageInfo) request.getAttribute("pageInfo");
		System.out.println("当前页码"+pi.getPageNum());
		System.out.println("总页码" +pi.getPages());
		System.out.println("总记录数" +pi.getTotal());
		System.out.println("在页面需要连续显示的页面");
                       int[] nums = pi.getNavigatepageNums();
	for (int i : nums) {
		System.out.println(""+i);
	}
	
	//获取员工数据
	List<Employee> list = pi.getList();
	for (Employee employee : list) {
		System.out.println("id"+employee.getEmpId()+"==Name"+employee.getEmpName()+"ssss"+employee.getDepartmet());
	}
	
	}

}
