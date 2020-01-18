package com.wxw.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



public class LoginInterceptor implements HandlerInterceptor{
	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		
		
		Object user = request.getSession().getAttribute("user");
		
		if(user==null) {
			request.setAttribute("msg", "没有权限请先登陆");
			
			
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return false;
		}
		return true;
	}
	
}
