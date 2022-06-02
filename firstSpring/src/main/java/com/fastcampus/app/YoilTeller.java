package com.fastcampus.app;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// ������� �Է��ϸ� ������ �˷��ִ� ���α׷�
@Controller
public class YoilTeller {
//	public static void main(String[] args) {
	@RequestMapping("/getYoil")
	public static void main(HttpServletRequest request, HttpServletResponse response) throws IOException {	// http://localhost:8080/ch2/getYoil?year=2022&month=6&day=2
		// 1. �Է�
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		// 2. �۾�
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy,  mm - 1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	// 1: �Ͽ���, 2: ������ ...
		char yoil = "�Ͽ�ȭ�������".charAt(dayOfWeek);
		
		// 3. ���
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();	// response ��ü���� �������� ��� ��Ʈ���� ��´�.
		out.println(year + "�� " + month + "�� " + day + "���� ");
		out.println(yoil + "�����Դϴ�.");
	}
}

