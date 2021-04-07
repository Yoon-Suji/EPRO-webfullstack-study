package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//프로그램을 시작할 시작점
public class ApplicationContextExam01 {
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//Spring이 가진 공장을 만드는 코드
		//ApplicationConext는 인터페이스, 구현하는 다양한 컨테이너 존재, ClassPathXml~~ 인터페이스를 구현하는 객체 중 xml파일을 classpath에서 읽어들여서 사용하는 객체
		//classpath를 통해 빈 정보를 알려줘야 함
		System.out.println("초기화 완료!!");
		
		UserBean userBean = (UserBean)ac.getBean("userBean"); //object로 리턴하므로 형변환 필요
		//xml파일에서 id==userBean을 찾고 해당 class를 생성해서 리턴
		userBean.setName("yoon");
		System.out.println(userBean.getName());
		
		UserBean userBean2 = (UserBean)ac.getBean("userBean");
		
		if (userBean == userBean2)
			System.out.println("같은 인스턴스입니다.");
		//Spring ApplicationCoontext는 객체를 생성할 때 싱글턴 패턴 이용하므로 여러번 getBean()요청해도
		//객체를 계쏙 만들어내는 게 아니라 하나 만든 bean을 이용하는 것
	}
}
