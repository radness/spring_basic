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
//	@RequsetMapping("/register/add") // �ű� ȸ�� ���� ȭ��
	@GetMapping("/add")
	public String register() {
		return "registerForm"; // WEB-INF/views/registerForm.jsp
	}

//	@RequestMapping(value = "/register/save", method = { RequestMethod.POST })
	@PostMapping("/save") // Spring 4.3 �������� ����
	public String save(User user, Model m) throws Exception {
		// 1. ��ȿ�� �˻�
		if (!isValid(user)) {
			String msg = URLEncoder.encode("id�� �߸� �Է� �Ͽ����ϴ�.", "utf-8");
			
			m.addAttribute("msg", msg);		// �𵨿� ��Ƽ� �ѱ�� ���.
			return "redirect://register/add";	// redirect : ���û
//			return "redirect://register/add?msg=" + msg; // URL���ۼ�(rewriting)
		}

		// 2. DB�� �ű�ȸ�� ������ ����
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return false;
	}
}
