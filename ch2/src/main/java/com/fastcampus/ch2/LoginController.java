package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}

	@PostMapping("/login")
	public String login(String id, String pwd, boolean rememberId, HttpServletResponse response) throws Exception {
		// 1. id와 pwd를 확인
		if (!loginCheck(id, pwd)) {
			// 2-1. id와 pwd가 일치하면 홈으로 아니면 loginForm 으로 이동
			String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");

			return "redirect:/login/login?msg=" + msg;
		}

		// 2-2. id와 pwd가 일치하면
		Cookie cookie = new Cookie("id", id);
		
		if (rememberId) {
			// 쿠키 생성
			response.addCookie(cookie);
		} else {
			// 쿠키를 삭제
			cookie.setMaxAge(0);
		}

		// 3. 홈으로 이동
		return "redirect:/";
	}

	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd);
	}
}
