package test;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import main.db.SpitterRepository;
import main.db.SpittleRepository;
import main.db.JdbcSpitterRepository;
import main.db.JdbcSpittleRepository;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:resources/application.properties" })
public class JdbcConfig {

	@Autowired
	private Environment environment;

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
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public SpitterRepository spitterRepository(JdbcTemplate jdbcTemplate,DataSource dataSource) {
		return new JdbcSpitterRepository(jdbcTemplate,dataSource);
	}

	@Bean
	public SpittleRepository spittleRepository(JdbcTemplate jdbcTemplate) {
		return new JdbcSpittleRepository(jdbcTemplate);
	}
	//DataSourceTransactionManager是PlatformTransactionManager的实现类
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
