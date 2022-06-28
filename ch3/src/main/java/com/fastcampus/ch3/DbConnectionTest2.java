package com.fastcampus.ch3;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DbConnectionTest2 {
	public static void main(String[] args) throws Exception {
		ApplicationContext ac = new GenericXmlApplicationContext(
				"file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
		DataSource ds = ac.getBean(DataSource.class);

		Connection conn = ds.getConnection(); // �����ͺ��̽��� ������ ��´�.

		System.out.println("conn = " + conn);
//		assertTrue(conn != null);
	}
}
