package kr.or.connect.daoexam.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DBConfig.class})
@ComponentScan(basePackages = { "kr.or.connect.daoexam.dao" })
//basePackages = { } 이용하면 패키지 여러 개 나열 가능
//@Repository 붙어있는 클래스를 Bean으로 등록해주는 것과 같은 역할
public class ApplicationConfig {

}
