package com.fastcampus.ch4.dao;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoAutowired {
	// 1. �ʵ� ����
	@Autowired
	private UserDao userDao;
	
	// 2. setter ����
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	// 3. ������ ����
	@Autowired
	public UserDaoAutowired(UserDao userDao) {
		this.userDao= userDao;
	}
}
