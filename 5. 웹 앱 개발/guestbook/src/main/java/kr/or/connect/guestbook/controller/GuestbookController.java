package kr.or.connect.guestbook.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.guestbook.dto.Guestbook;
import kr.or.connect.guestbook.service.GuestbookService;

@Controller
public class GuestbookController {
	@Autowired
	GuestbookService guestbookService;
	
	@GetMapping(path = "/list")
	public String list(@RequestParam(name = "start", required = false, defaultValue = "0") int start, 
			ModelMap model, @CookieValue(value="count",defaultValue="0",required=true) String value, HttpServletResponse response) {
		
		//---쿠키를 이용한 상태유지 실습---
		/* HttpServletRequest request를 이용한 방법
		String value = null;
		boolean find = false;
		Cookie[] cookies = request.getCookies(); //클라이언트로부터 쿠키의 배열 얻어오기
		if(cookies != null) { //쿠키가 없는 경우 null을 리턴
			for(Cookie cookie : cookies) {
				if("count".equals(cookie.getName())) { //쿠키의 name이 count와 같으면
					find = true; //
					value = cookie.getValue();
					break;
				}
			}
		}
      
		if(!find) { //처음 요청이 들어온 경우(쿠키가 없는 경우)->find=false
			value = "1"; //쿠키는 문자열로 된 value를 가질 수 있음
		}else { // 쿠키를 찾았다면. */
			try { //@CookieValue이용하면 이 부분만 있으면 됨.
				int i = Integer.parseInt(value);
				value = Integer.toString(++i); //value++
			}catch(Exception ex) {
				value = "1"; //예외처리
			}
		//}
		
		//변경된 쿠키 value값을 클라이언트쪽에도 적용하게 하려면 반드시 매번 새로 쿠키를 생성해서 보내야 함
		Cookie cookie = new Cookie("count", value); //쿠키 객체 생성
		cookie.setMaxAge(60 * 60 * 24 * 365); // 쿠키 1년 동안 유지. -1을 넣을 경우 브라우저가 닫히면 리셋됨.
		cookie.setPath("/"); // / 경로 이하에 모두 쿠키 적용. 
		response.addCookie(cookie); //응답결과로 쿠키 보내줌.
		//---쿠키를 이용한 상태유지 실습----
		
		
		// start로 시작하는 방명록 목록 구하기
		List<Guestbook> list = guestbookService.getGuestbooks(start);

		// 전체 페이지수 구하기
		int count = guestbookService.getCount();
		int pageCount = count / GuestbookService.LIMIT;
		if (count % GuestbookService.LIMIT > 0)
			pageCount++;

		// 페이지 수만큼 start의 값을 리스트로 저장
		// 예를 들면 페이지수가 3이면
		// 0, 5, 10 이렇게 저장된다.
		// list?start=0 , list?start=5, list?start=10 으로 링크가 걸린다.
		List<Integer> pageStartList = new ArrayList<>();
		for (int i = 0; i < pageCount; i++) {
			pageStartList.add(i * GuestbookService.LIMIT);
		}
		
		//JSP에서 사용하도록 model에 필요한 값 넣기
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("pageStartList", pageStartList);
		model.addAttribute("cookieCount", value); //jsp에서 쿠키 방문횟수 출력하기 위해 추가

		return "list"; //list.jsp
	}

	@PostMapping(path = "/write")
	public String write(@ModelAttribute Guestbook guestbook, HttpServletRequest request) {
		String clientIp = request.getRemoteAddr();
		System.out.println("clientIp : " + clientIp);
		guestbookService.addGuestbook(guestbook, clientIp);
		return "redirect:list";
	}
}
