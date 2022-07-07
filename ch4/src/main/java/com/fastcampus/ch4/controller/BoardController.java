package com.fastcampus.ch4.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list")
	public String list(int page, int pageSize, Model m, HttpServletRequest request) {
		if (!loginCheck(request)) {
			return "redirect:/login/login?toURL=" + request.getRequestURL(); // �α����� �������� �α��� ȭ������ �̵�
		}
		
		try {
			Map map = new HashMap();
			map.put("offset", (page - 1) * pageSize);
			map.put("pageSize", pageSize);
			
			List<BoardDto> list = boardService.getPage(map);
			m.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "boardList";		// �α����� �� ���¸� �Խ��� ȭ������ �̵�
	}

	private boolean loginCheck(HttpServletRequest request) {
		// 1. ������ ��´�.
		HttpSession session = request.getSession();
		// 2. ���ǿ� id�� �ִ��� Ȯ��. ������ true, ������ false
		return session.getAttribute("id") != null;

	}
}
