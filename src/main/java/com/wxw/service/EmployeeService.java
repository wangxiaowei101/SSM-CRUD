package com.wxw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxw.bean.Employee;
import com.wxw.bean.EmployeeExample;
import com.wxw.bean.EmployeeExample.Criteria;
import com.wxw.bean.User;
import com.wxw.dao.EmployeeMapper;

@Service
public class EmployeeService {
    @Autowired
	EmployeeMapper employeeMapper;
	
	
	
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExampleWithDept(null);
	}

/**
 * 员工保存方法
 * @param employee
 */

	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
	}
/**
 * 检验用户名是否可用
 * 返回==0返回true 当前姓名可用
 * @param empName
 * @return
 */
	//查到相当返回大于0反之等于0
public boolean checkUser(String empName) {
	// TODO Auto-generated method stub
	 EmployeeExample example= new EmployeeExample();
	 Criteria criteria = example.createCriteria();
	 criteria.andEmpNameEqualTo(empName);
	long count = employeeMapper.countByExample(example);
	return count==0;
}

/**
 * 根据id查询
 * @param id
 * @return
 */
public Employee getEmp(Integer id) {
	// TODO Auto-generated method stub
	Employee employee = employeeMapper.selectByPrimaryKeyWithDept(id);
	return employee;
}
/**
 * 员工更新
 * @param employee
 */
public void updateEmp(Employee employee) {
	// TODO Auto-generated method stub
	
	employeeMapper.updateByPrimaryKeySelective(employee);
}
/**
 * 员工删除
 * @param id
 */

public void deleteEmp(Integer id) {
	employeeMapper.deleteByPrimaryKey(id);
	// TODO Auto-generated method stub
	
}

public void deleteBatch(List<Integer> ids) {
	// TODO Auto-generated method stub
	EmployeeExample example=new EmployeeExample();
	Criteria criteria = example.createCriteria();
	criteria.andEmpIdIn(ids);
	employeeMapper.deleteByExample(example);
}


public User findByUserName(String username) {
	// TODO Auto-generated method stub
	return employeeMapper.findByUserName(username);
}



	
}
