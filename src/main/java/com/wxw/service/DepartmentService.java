package com.wxw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxw.bean.Departmet;
import com.wxw.dao.DepartmetMapper;


@Service
public class DepartmentService {

	@Autowired
	private DepartmetMapper departmetMapper;
	
	
	public List<Departmet> getDepts() {
		// TODO Auto-generated method stub
		 List<Departmet> list = departmetMapper.selectByExample(null);
		return list;
	}

}
