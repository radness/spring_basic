package com.fastcampus.ch4.domain;

public class PageHandler {
	int totalCnt;	// �� �Խù� ����
	int pageSize;	// �� �������� ũ��
	int naviSize = 10;	// ������ �׺���̼��� ũ��
	int totalPage;	// ��ü �������� ����
	int page;		// ���� ������
	int beginPage;	// �׺���̼��� ù��° ������
	int endPage;	// �׺���̼��� ������ ������
	boolean showPrev;	// ���� �������� �̵��ϴ� ��ũ ����
	boolean showNext;	// ���� �������� �̵��ϴ� ��ũ ����
	
	public PageHandler(int totalCnt, int page) {
		this(totalCnt, page, 10);
	}
	
	public PageHandler(int totalCnt, int page, int pageSize) {
		// iv�� ����
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
