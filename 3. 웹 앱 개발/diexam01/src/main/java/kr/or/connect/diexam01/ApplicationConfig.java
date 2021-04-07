package kr.or.connect.diexam01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Config파일이라는 것을 알려주는 어노테이션
public class ApplicationConfig {
	@Bean //Bean을 생성하는 어노테이션
	public Car car(Engine e) {
		Car c = new Car();
		c.setEngine(e);
		return c;
	}
	
	@Bean
	public Engine engine() {
		return new Engine();
	}
	/*ApplicationContext는 파라미터를 받아들이지 않는 Bean 생성 메소드를 먼저 실행해서 반환받은 객체 관리
	 *그 후 파라미터에 생성된 객체들과 같은 타입이 있는 객체가 있는 경우 파라미터로 전달해서 객체 생성*/
}
