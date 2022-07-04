package com.fastcampus.ch3;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class A1DaoTest {
	@Autowired
	A1Dao a1Dao;
	
	@Autowired
	DataSource ds;
	
	@Autowired
	DataSourceTransactionManager tm;
	
	@Test
	public void insertTest() throws Exception {
		// TxMaager ����
//		PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
		
		// Tx ����
		try {
			a1Dao.deleteAll();	// ��ü ����
			
			a1Dao.insert(1, 100);
			a1Dao.insert(1, 200);
		} catch (Exception e) {
			e.printStackTrace();
			tm.rollback(status);
		} finally {
			
		}
	}

}
