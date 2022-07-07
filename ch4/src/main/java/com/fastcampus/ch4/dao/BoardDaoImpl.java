package com.fastcampus.ch4.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;

@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	SqlSession sqlSession;
	String namespace = "com.fastcampus.ch4.dao.BoardMapper.";

	@Override
	public BoardDto select(Integer bno) throws Exception {
		return sqlSession.selectOne(namespace + "select", bno);
	}

	@Override
	public int delete(Integer bno, String writer) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(BoardDto dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BoardDto dto) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int increaseViewCnt(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardDto> selectPage(Map map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardDto> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAll() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int count() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int searchResultCnt(SearchCondition sc) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}