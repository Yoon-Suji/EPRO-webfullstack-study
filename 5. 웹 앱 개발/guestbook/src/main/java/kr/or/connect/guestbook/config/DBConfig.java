package kr.or.connect.guestbook.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
//트랜잭션과 관련된 설정을 자동으로 해줌
//단, 사용자간의 트랜잭션 처리를 위한 PlatformTransactionManager를 설정하기 위해서는 TransactionManagementConfigurer을 구현하고
//annotationDrivenTransactionManager메소드를 오버라이딩 해야 함
public class DBConfig implements TransactionManagementConfigurer {

	private String driverClassName = "com.mysql.jdbc.Driver";

	private String url = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8";

	private String username = "connectuser";

	private String password = "connect123!@#";

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	} //데이터베이스 이용학 위한 코드
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger(); //메소드 호출
	} //트랜잭션을 처리할 PlatformTransactionManager객체 반환
	
	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	} //PlatformTransactionManager리턴

}
