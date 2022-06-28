package com.fastcampus.ch3;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class DBConnectionTest2Test {

	@Autowired
	DataSource ds;

	@Test
	public void insertUserTest() throws Exception {
		deleteAll();
		
		User user = new User("asdf", "1234", "smith", "aaa@aaa.com", new Date(), "instragram", new Date());
		int rowCnt = insertUser(user);

		assertTrue(rowCnt == 1);
	}

	@Test
	public void selectUserTest() throws Exception {
		deleteAll();
		
		User user = new User("asdf", "1234", "smith", "aaa@aaa.com", new Date(), "instragram", new Date());
		insertUser(user);
		
		User user2 = selectUser("asdf");
		
		assertTrue(user.getId().equals(user2.getId()));
	}
	
	@Test
	public void deleteUserTest() throws Exception {
		deleteAll();
		
		int rowCnt = deleteUser("asdf");
		
		assertTrue(rowCnt == 0);
		
		User user = new User("asdf", "1234", "smith", "aaa@aaa.com", new Date(), "instragram", new Date());
		rowCnt = insertUser(user);
		
		assertTrue(rowCnt == 1);
		
		rowCnt = deleteUser(user.getId());
		assertTrue(rowCnt == 1);
		
		assertTrue(selectUser(user.getId()) == null);
		
	}
	
	@Test
	public void updateUserTest() throws Exception {
		deleteAll();
		
		User user = new User("asdf", "1234", "smith", "aaa@aaa.com", new Date(), "instragram", new Date());
		insertUser(user);
		
		User user2 = new User("asdf", "1234", "shin", "bbb@bbb.com", new Date(), "facebook", new Date());
		int rowCnt = updateUser(user2);

		assertTrue(rowCnt == 1);
	}
	
	// �Ű������� ���� ����� ������ user_info ���̺��� update �ϴ� �޼���
	public int updateUser(User user) throws Exception {
		Connection conn = ds.getConnection();
		
		String sql = "UPDATE user_info SET NAME=?, EMAIL=?, BIRTH=?, SNS=?, REG_DATE=now()";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getEmail());
		pstmt.setDate(3, new java.sql.Date(user.getBirth().getTime()));
		pstmt.setString(4, user.getSns());
		
		int rowCnt = pstmt.executeUpdate();
		return rowCnt;
		
	}
	
	public int deleteUser(String id) throws Exception {
		Connection conn = ds.getConnection();
		
		String sql = "DELETE FROM user_info WHERE ID=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
//		int rowCnt = pstmt.executeUpdate();
//		return rowCnt;
		return pstmt.executeUpdate();
	}
	
	// id�� �ش��ϴ� ���� ��������
	public User selectUser(String id) throws Exception {
		Connection conn = ds.getConnection();

		String sql = "SELECT * FROM user_info WHERE ID=?";

		PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection ����, �������
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		
		// ���� ����� ������ ���� ä��� ��ȯ
		if (rs.next()) {
			User user = new User();
			user.setId(rs.getString(1));
			user.setPwd(rs.getString(2));
			user.setName(rs.getString(3));
			user.setEmail(rs.getString(4));
			user.setBirth(rs.getDate(5));
			user.setSns(rs.getString(6));
			user.setReg_date(rs.getDate(7));
			
			return user;
		}
		
		return null;
	}
	
	@Test
	public void deleteAll() throws Exception {
		Connection conn = ds.getConnection();

		String sql = "DELETE FROM user_info";

		PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection ����, �������
		pstmt.executeUpdate();
	}

	// ����� ������ user_info ���̺� �����ϴ� �޼���
	public int insertUser(User user) throws Exception {
		Connection conn = ds.getConnection();

//		INSERT INTO user_info(id, pwd, name, email, birth, sns, reg_date)
//		VALUES ('asdf', '1234', 'smith', 'aaa@aaa.com', '2022-06-28', 'instragram', 'now()');

//		String sql = "INSERT INTO user_info VALUES ('asdf', '1234', 'smith', 'aaa@aaa.com', '2022-06-28', 'instragram', 'now()')";
		String sql = "INSERT INTO user_info VALUES (?, ?, ?, ?, ?, ?, now())";

		PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection ����, �������
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getPwd());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
		pstmt.setString(6, user.getSns());

		int rowCnt = pstmt.executeUpdate();

		return rowCnt;
	}

	@Test
	public void springJdbcConnectionTest() throws Exception {
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

		Connection conn = ds.getConnection(); // �����ͺ��̽��� ������ ��´�.

		System.out.println("conn = " + conn);
		assertTrue(conn != null); // ��ȣ ���� ���ǽ��� true��, �׽�Ʈ ����, �ƴϸ� ����
	}

}
