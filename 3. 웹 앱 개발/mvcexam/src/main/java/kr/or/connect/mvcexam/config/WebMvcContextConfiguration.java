package kr.or.connect.mvcexam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "kr.or.connect.mvcexam.controller" })
//중괄호 없이 사용하면 패키지 하나만. basePackage는 꼭 지정해 줘야 함.

//이 클래스 자첵가 DispatcherServlet이 실행될 때 읽어들이는 설정 파일임.
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    } //모든 요청을 받아들이기 때문에 /css/**.. 이렇게 시작하는 요청은 애플리케이션 루트 디렉토리 아래에 각각의 디렉토리를 만들어 놓고 거기에 알맞게 사용하게 해줄 것이다.. 라는 설정
	  // 이렇게 들어오는 요청들은 여기에서 찾아요...
	  //이 부분 없으면 모두 컨트롤러가 가진 RequestMapping에서 찾으려 하면서 오류발생시킴
	
	
	// default servlet handler를 사용하게 합니다.
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    } //매핑정보가 없는 URL요청을 Spring의 DefaultServletHttpRequestHandler가 처리하도록
      //DefaultServletHttpRequestHandler -> WAS의 DefaultServlet에게 일을 넘김 -> static한 자원을 읽어서 보여주게 해줌
    
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
    		System.out.println("addViewControllers가 호출됩니다. ");
        registry.addViewController("/").setViewName("main");
        //요청이 "/"로 들어오면 "main"이라는 이름의 뷰로 보여주도록 하는 것
        //view name은 ViewResolver를 이용해서 찾는다. main만 가지고는 찾을 수 없음.
    } //특정 URL에 대한 처리를 컨트롤러 클래스를 작성하지 않고 매핑할 수 있도록 해줌.
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    } //뷰 정보는 getInternalResourceViewResolver메소드에서 설정된 형태로 뷰를 사용하게 됨.
      //setPrefix는 앞에 붙이는 주소, Suffix는 뒤에 붙이는 주소 -> WEB-INF/views/main.jsp 파일을 찾아주는 것
}
