package demo.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "demo.configuration" })
@PropertySource(value = { "classpath:resources/application.properties" })
public class RootConfig {

	// 依赖注入环境变量，用于读取资源文件中的属性
	@Autowired
	private Environment environment;

	// 声明一个数据源bean，用于操作数据库中的数据
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory()
	{
		LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[]{"demo.model"});
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	//读hibernate配置的bean
	@Bean
	public Properties hibernateProperties() {
		Properties properties = new Properties();		
		properties.put("hibernate.dialect",environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql",environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql",environment.getRequiredProperty("hibernate.format_sql"));
		return properties;
	}
		
	// 声明一个事务bean，用于声明式事务处理
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s)
	{
		HibernateTransactionManager txManager=new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
}
