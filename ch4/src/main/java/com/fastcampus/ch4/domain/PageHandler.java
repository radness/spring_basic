package com.fastcampus.ch4.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class PageHandler {
	private SearchCondition sc;
//    private int pageSize = 10; // �� �������� �Խù� ����
//    private int page; // ���� ������
//    private String  option;
//    private String  keyword;
	public final int NAV_SIZE = 10; // page navigation size
	private int totalCnt; // �Խù��� �� ����
	private int totalPage; // ��ü �������� ����
	private int beginPage; // ȭ�鿡 ������ ù ������
	private int endPage; // ȭ�鿡 ������ ������ ������
	private boolean showNext = false; // ���ĸ� ���������� ����. endPage==totalPage�̸�, showNext�� false
	private boolean showPrev = false; // ������ ���������� ����. beginPage==1�� �ƴϸ� showPrev�� false

	public PageHandler(int totalCnt, Integer page) {
		this(totalCnt, new SearchCondition(page, 10));
	}

	public PageHandler(int totalCnt, Integer page, Integer pageSize) {
		this(totalCnt, new SearchCondition(page, pageSize));
	}

	public PageHandler(int totalCnt, SearchCondition sc) {
		this.totalCnt = totalCnt;
		this.sc = sc;

		doPaging(totalCnt, sc);
	}

	private void doPaging(int totalCnt, SearchCondition sc) {
		this.totalPage = totalCnt / sc.getPageSize() + (totalCnt % sc.getPageSize() == 0 ? 0 : 1);
		this.sc.setPage(Math.min(sc.getPage(), totalPage)); // page�� totalPage���� ũ�� �ʰ�
		this.beginPage = (this.sc.getPage() - 1) / NAV_SIZE * NAV_SIZE + 1; // 11 -> 11, 10 -> 1, 15->11. ���� ����� �׽�Ʈ
		this.endPage = Math.min(beginPage + NAV_SIZE - 1, totalPage);
		this.showPrev = beginPage != 1;
		this.showNext = endPage != totalPage;
	}

	public String getQueryString() {
		return getQueryString(this.sc.getPage());
	}

	public String getQueryString(Integer page) {
		// ?page=10&pageSize=10&option=A&keyword=title
		return UriComponentsBuilder.newInstance().queryParam("page", page).queryParam("pageSize", sc.getPageSize())
				.queryParam("option", sc.getOption()).queryParam("keyword", sc.getKeyword()).build().toString();
	}

	void print() {
		System.out.println("page=" + sc.getPage());
		System.out.print(showPrev ? "PREV " : "");

		for (int i = beginPage; i <= endPage; i++) {
			System.out.print(i + " ");
		}
		System.out.println(showNext ? " NEXT" : "");
	}

	public SearchCondition getSc() {
		return sc;
	}

	public void setSc(SearchCondition sc) {
		this.sc = sc;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public boolean isShowNext() {
		return showNext;
	}

	public void setShowNext(boolean showNext) {
		this.showNext = showNext;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getNAV_SIZE() {
		return NAV_SIZE;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isShowPrev() {
		return showPrev;
	}

	public void setShowPrev(boolean showPrev) {
		this.showPrev = showPrev;
	}

	@Override
	public String toString() {
		return "PageHandler{" + "sc=" + sc + ", totalCnt=" + totalCnt + ", showNext=" + showNext + ", beginPage="
				+ beginPage + ", NAV_SIZE=" + NAV_SIZE + ", totalPage=" + totalPage + ", endPage=" + endPage
				+ ", showPrev=" + showPrev + '}';
	}
}
