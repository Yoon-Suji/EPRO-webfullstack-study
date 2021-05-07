<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.sendRedirect("list");
	//처음 프로그램 run 하면 "guestbook/"까지 요청되는데 이렇게 컨트롤러 없이
	//"/"라는 요청이 들어오면 indix라는 view name으로 실행하라고 WebMvcContext..에 설정해놨음
	//그래서 이 파일이 실행되는데 이 코드로 인해 URL이 "guestbook/list"로 바뀌는 것
%>
    