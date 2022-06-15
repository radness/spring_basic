package com.fastcampus.ch2;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
//	@RequestMapping(value = "/register/save", method = { RequestMethod.GET, RequestMethod.POST })
//	@RequsetMapping("/register/add") // 신규 회원 가입 화면
	@GetMapping("/add")
	public String register() {
		return "registerForm"; // WEB-INF/views/registerForm.jsp
	}

//	@RequestMapping(value = "/register/save", method = { RequestMethod.POST })
	@PostMapping("/save") // Spring 4.3 버전부터 적용
	public String save(User user, Model m) throws Exception {
		// 1. 유효성 검사
		if (!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘못 입력 하였습니다.", "utf-8");
			
			m.addAttribute("msg", msg);		// 모델에 담아서 넘기는 방법.
			return "redirect://register/add";	// redirect : 재요청
//			return "redirect://register/add?msg=" + msg; // URL재작성(rewriting)
		}

		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return false;
	}
}
