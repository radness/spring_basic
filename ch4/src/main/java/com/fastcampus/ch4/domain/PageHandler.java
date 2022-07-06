package com.fastcampus.ch4.domain;

public class PageHandler {
	int totalCnt;	// 총 게시물 갯수
	int pageSize;	// 한 페이지의 크기
	int naviSize = 10;	// 페이지 네비게이션의 크기
	int totalPage;	// 전체 페이지의 갯수
	int page;		// 현재 페이지
	int beginPage;	// 네비게이션의 첫번째 페이지
	int endPage;	// 네비게이션의 마지막 페이지
	boolean showPrev;	// 이전 페이지로 이동하는 링크 유무
	boolean showNext;	// 다음 페이지로 이동하는 링크 유무
	
	public PageHandler(int totalCnt, int page) {
		this(totalCnt, page, 10);
	}
	
	public PageHandler(int totalCnt, int page, int pageSize) {
		// iv에 저장
		this.totalCnt = totalCnt;
		this.page = page;
		this.pageSize = pageSize;
		
		totalPage = (int) Math.ceil(totalCnt / pageSize);
		beginPage = page / naviSize * naviSize + 1;
		endPage = Math.min(pageSize + naviSize, totalPage);
		
		showPrev = beginPage != 1;
		showNext = endPage != totalPage;
	}
	
	void print() {
		System.out.println("page = " + page);
		System.out.println(showPrev ? "[PREV] " : "");
		for (int i = beginPage; i < endPage; i++) {
			System.out.println(i + " ");
		}
		System.out.println(showNext ? "[NEXT] " : "");
	}

	@Override
	public String toString() {
		return "PageHandler [totalCnt=" + totalCnt + ", pageSize=" + pageSize + ", naviSize=" + naviSize
				+ ", totalPage=" + totalPage + ", page=" + page + ", beginPage=" + beginPage + ", endPage=" + endPage
				+ ", showPrev=" + showPrev + ", showNext=" + showNext + "]";
	}
	
	
}
