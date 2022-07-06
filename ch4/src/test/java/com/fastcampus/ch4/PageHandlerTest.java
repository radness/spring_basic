package com.fastcampus.ch4;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fastcampus.ch4.domain.PageHandler;

public class PageHandlerTest {
	@Test
	public void test() {
		PageHandler ph = new PageHandler(250, 1);
		ph.print();
		
		assertTrue(ph.getBeginPage() == 1);
		assertTrue(ph.getEndPage() == 10);
	}
}
