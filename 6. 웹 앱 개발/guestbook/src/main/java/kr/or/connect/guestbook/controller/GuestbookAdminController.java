package kr.or.connect.guestbook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GuestbookAdminController {
	@GetMapping(path="/loginform")
	public String loginform() {
		return "loginform";
	}
	
	@PostMapping(path="/login")
	//loginform으로부터 암호를 전달받아서 암호가 일치할 경우 세션에 로그인정보 저장
	public String login(@RequestParam(name="passwd", required=true) String passwd,
			HttpSession session, RedirectAttributes redirectAttr) {
		
		if("1234".equals(passwd)) {
			session.setAttribute("isAdmin", true);
		} else {
			redirectAttr.addFlashAttribute("errorMessage", "암호가틀렸습니다.");
			return "redirect:/loginform";
			//redirectAttr은 dispatcherServlet이 관리하는 FlashMap에 값 저장
			//redirect할 때 딱 한 번만 값을 유지할 목적으로 사용
		}
		
		return "redirect:/list";
	}
	
	@GetMapping(path="/logout")
	public String login(HttpSession session) {
		session.removeAttribute("isAdmin"); //세션에서 값 삭제
		return "redirect:/list";
	}
	
}
