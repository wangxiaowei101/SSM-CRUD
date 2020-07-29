package com.wxw.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxw.bean.Departmet;
import com.wxw.dao.DepartmetMapper;
import com.wxw.dao.EmployeeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
	DepartmetMapper departmetMapper;
    
    @Autowired
	EmployeeMapper employeeMapper; 
    
    @Autowired
	SqlSession sqlSession; 
	
	
	@Test
	public void testCRUD() {
		//1.创建springIOC容器
		
		//ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.从容器中获取mapper
		//DepartmetMapper bean = ioc.getBean(DepartmetMapper.class);
		
		
	System.out.println(departmetMapper);
		//1.插入几个部门

		departmetMapper.insertSelective(new Departmet(null,"测试部"));
		departmetMapper.insertSelective(new Departmet(null,"测试部"));
		departmetMapper.insertSelective(new Departmet(null,"销售部"));
		System.out.println("增加成功");
		
	//2.生成员工数据，测试员工插入
		
//		employeeMapper.insertSelective(new Employee(null,"jerry","M","jerry@sss",1));
//		System.out.println("增加成功");
		//3.批量插入多个员工，批量，可以执行批量操作的sqlSession
		
//	EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//	  for(int i=0;i<1000;i++) {
//		  String uid = UUID.randomUUID().toString().substring(0, 5)+i;
//		  mapper.insertSelective(new Employee(null,uid,"M","wa@qq",1));
//		  
//	  }
//	  System.out.println("批量增加成功");
	}
	
	
}
