package com.wxw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxw.bean.User;
import com.wxw.dao.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;

	public User findUserByName(String username) {
		
		return userMapper.findUserByName(username);
	}
	

}
