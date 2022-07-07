package com.fastcampus.ch4.dao;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoAutowired {
	// 1. 필드 주입
	@Autowired
	private UserDao userDao;
	
	// 2. setter 주입
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	// 3. 생성자 주입
	@Autowired
	public UserDaoAutowired(UserDao userDao) {
		this.userDao= userDao;
	}
}
