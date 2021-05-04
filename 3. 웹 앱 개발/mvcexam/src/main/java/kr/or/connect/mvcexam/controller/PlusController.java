package kr.or.connect.mvcexam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //컨트롤러위에 반드시 어노테이션 필요
public class PlusController {
	@GetMapping(path="/plusform")
	public String plusform() {
		return "plusform"; //이 부분이 view name
	} //ModelAndView? 객체를 리턴해도 되고 String으로 이름만 리턴해도 됨.
	
	@PostMapping(path="/plus")
	public String plus(@RequestParam(name = "value1", required = true) int value1,
			@RequestParam(name = "value2", required = true) int value2, ModelMap modelMap) {
		int result = value1 + value2;
		//직접 HttpServletRequest 선언해서 setAttribute할 수도 있지만 그러면 종속적이 되므로
		//스프링에서 제공하는 ModelMap 객체 이용 -> Spring이 알아서 request scope에 매핑
		modelMap.addAttribute("value1", value1);
		modelMap.addAttribute("value2", value2);
		modelMap.addAttribute("result", result);
		return "plusResult";
	}
}
