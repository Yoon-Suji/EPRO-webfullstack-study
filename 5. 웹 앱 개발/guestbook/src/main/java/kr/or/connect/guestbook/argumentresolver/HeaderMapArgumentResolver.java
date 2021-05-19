package kr.or.connect.guestbook.argumentresolver;

import java.util.Iterator;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class HeaderMapArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	//Controller 메소드의 인자 개수 만큼 supportsParameters메소드 호출됨
	//인자의 정보를 파라미터로 전달, 해당 파라미터의 정보에 원하는 정보가 있다면 true 반환
	//true를 리턴할 경우에만 resolveArgument 메소드 호출됨
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType() == HeaderInfo.class;
		//파라미터 타입이 HeaderInfo 클래스 타입일 경우에 true 리턴
	}

	@Override
	//resulveArgument가 리턴한 값은 Controller 메소드의 인자로 전달됨
	
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		HeaderInfo headerInfo = new HeaderInfo();
		//요청으로부터 넘어온 모든 정보를 HeaderInfo 객체에 담아서 리턴
		
		Iterator<String> headerNames = webRequest.getHeaderNames(); //Header의 Name을 Iterator형태로 꺼내옴
		while (headerNames.hasNext()) {
			String headerName = headerNames.next();
			String headerValue = webRequest.getHeader(headerName);
			
			System.out.println(headerName + " , " + headerValue);
			
			headerInfo.put(headerName, headerValue); //헤더 이름과 value를 얻어내서 headerinfo에 put
		}

		return headerInfo;
	}

}
