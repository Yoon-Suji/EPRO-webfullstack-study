package kr.or.connect.mvcexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.connect.mvcexam.dto.User;

@Controller
public class userController {
	@RequestMapping(path="/userform", method=RequestMethod.GET)
	//@GetMapping이용해도 됨
	public String userform() {
		return "userform";
	}
	
	@RequestMapping(path="/regist", method=RequestMethod.POST)
	public String regist(@ModelAttribute User user) {
		//User = DTO역할
		//@ModelAttribute 로 선언해주면 Spring MVC가 알아서 넘겨진 값들을 꺼내서 객체에 넣어줌.(이름이 같아야함)
		System.out.println("사용자가 입력한 user 정보입니다. 해당 정보를 이용하는 코드가 와야합니다.");
		System.out.println(user);
		return "regist";
	}
}
