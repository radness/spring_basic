package com.fastcampus.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxService {
	// 트랜젝션 서비스
	
	@Autowired
	A1Dao a1Dao;
	
	@Autowired
	B1Dao b1Dao;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insertA1WithTx() throws Exception {
		a1Dao.insert(1, 100);	// 성공
		insertB1WithTx();
		a1Dao.insert(2, 200);	// 성공
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void insertB1WithTx() throws Exception {
		b1Dao.insert(1, 100);	// 성공
		b1Dao.insert(1, 200);	// 실패
	}
	
	public void insertA1WithoutTx() throws Exception {
		a1Dao.insert(1, 100);
		a1Dao.insert(1, 200);
	}
	
	// @Transactional	// RuntimeException, Error 만 rollback 한다.
	// @Transactional(propagation = Propagation.REQUIRED) -> default
	// @Transactional(propagation = Propagation.REQUIRES_NEW)	// 새로운 Tx 생성
	@Transactional(rollbackFor = Exception.class)	// Exception을 rollback한다. 
	public void insertA1WithTxFail() throws Exception {
		a1Dao.insert(1, 100);
		a1Dao.insert(1, 200);
	}
	
	@Transactional
	public void insertA1WithTxSuccess() throws Exception {
		a1Dao.insert(1, 100);
		a1Dao.insert(2, 200);
	}
}
