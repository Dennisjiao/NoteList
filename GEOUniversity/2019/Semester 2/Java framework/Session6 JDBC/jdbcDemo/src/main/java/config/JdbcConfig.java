package config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;


@Configuration
//开启声明式事务处理
@EnableTransactionManagement
@ComponentScan(basePackages={"dao"})
//读取外部配置文件
@PropertySource(value = { "classpath:application.properties" })
public class JdbcConfig {

	//把配置文件中的配置信息读入环境中
	@Autowired
	private Environment environment;

	/* 
	 * jdbc数据源，生产环境
	 */
	@Profile("prod")	
	//@Primary
	@Bean
    public DataSource dataSource_Prod() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
	/* 
	 * druid数据源，生产环境
	 */
	@Profile("prod")
	@Primary
	@Bean	
    public DataSource dataSource_Druid() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		datasource.setUrl(environment.getRequiredProperty("jdbc.url"));
		datasource.setUsername(environment.getRequiredProperty("jdbc.username"));
		datasource.setPassword(environment.getRequiredProperty("jdbc.password"));
		/** 配置初始化大小、最小、最大 */
        datasource.setInitialSize(1);
        datasource.setMaxActive(2);
        datasource.setMinIdle(1);
        /** 配置获取连接等待超时的时间 */
        datasource.setMaxWait(60000);
        /** 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
        datasource.setTimeBetweenEvictionRunsMillis(30000);
        /** 配置一个连接在池中最小、最大生存的时间，单位是毫秒 */
        datasource.setMinEvictableIdleTimeMillis(30000);
        datasource.setMaxEvictableIdleTimeMillis(90000);
        /** 保持连接,防止连接长时间不用被关闭 */
        datasource.setKeepAlive(true);
        /**
         * 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。
         */
        datasource.setValidationQuery("SELECT 1 FROM DUAL");
        /** 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。 */
        datasource.setTestWhileIdle(true);
        /** 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
        datasource.setTestOnBorrow(false);
        /** 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
        datasource.setTestOnReturn(false);        
        return datasource;
    }
	
	/* 
	 * H2数据源，开发环境
	 */
	@Profile("dev")
	@Bean
	public DataSource dataSource_Dev() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScripts("classpath:schema.sql", "classpath:test-data.sql").build();
	}
	
	/* 
	 * 建立根据数据源建立JdbcTemplate Bean
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}	

	/* 
	 * 声明事务处理bean
	 * DataSourceTransactionManager是PlatformTransactionManager的实现类
	 */
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
