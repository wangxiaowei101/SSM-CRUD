package com.wxw.dao;

import com.wxw.bean.User;


public interface UserMapper {
	 /**
     * 管理员登陆
     * @param username
     * @return
     */
    User findUserByName(String username);
}
