package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextExam02 {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Car car = (Car)ac.getBean("c");
		//DI = 어떤 객체에게 객체를 주입하는 것
		car.run();
		//사용자는 사용할 Car 클래스만 알고 있으면 된다.
	}
}
