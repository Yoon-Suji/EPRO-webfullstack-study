package kr.or.connect.diexam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextExam03 {
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		//Car car = (Car)ac.getBean("car");
		Car car = (Car)ac.getBean(Car.class); //요청하는 클래스 타입으로 지정 가능
		//DI = 어떤 객체에게 객체를 주입하는 것
		car.run();
		//사용자는 사용할 Car 클래스만 알고 있으면 된다.
	}
}
