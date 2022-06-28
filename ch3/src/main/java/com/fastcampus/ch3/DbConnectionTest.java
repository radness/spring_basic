package com.fastcampus.ch3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnectionTest {
	public static void main(String[] args) throws Exception {
		// ��Ű�� �̸�(springbasic)�� �ٸ� ��� �˸°� �����ؾ� ��
		String DB_URL = "jdbc:mysql://localhost:3306/springbasic?useUnicode=true&characterEncoding=utf8";

		// DB�� user id�� pwd�� �˸°� �����ؾ� ��
		String DB_USER = "radness";
		String DB_PASSWORD = "1234";

		Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);	// DB ������ ��´�.
		Statement stmt = conn.createStatement();	// Statement�� �����Ѵ�.
		
		String query = "SELECT now()";	// �ý����� ���� ��¥ �ð��� ����ϴ� ����
		ResultSet rs = stmt.executeQuery(query);
		
		System.out.println("DB Connection Success.");
		
		// ���� ����� ��� rs���� ���پ� �о ���
		while(rs.next()) {
			String curDate = rs.getString(1);	// �о�� ���� ù��° �÷��� ���� String���� �о curDate�� ����
			System.out.println(curDate);
			
		}
	}	// main()
}
