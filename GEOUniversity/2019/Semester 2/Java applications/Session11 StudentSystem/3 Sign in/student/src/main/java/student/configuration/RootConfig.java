package student.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import student.model.PropConfig;


@Configuration
@ComponentScan(basePackages = "student")
@EnableTransactionManagement
public class RootConfig {

	@Autowired
	private PropConfig propconfig;

	//用于生成sessionFactory对象
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "student.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
	// 声明一个数据源bean，用于操作数据库中的数据
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(propconfig.getDriverClassName());
//		dataSource.setUrl(propconfig.getUrl());
//		dataSource.setUsername(propconfig.getUsername());
//		dataSource.setPassword(propconfig.getPassword());
//		return dataSource;
//	}
	//用于生成数据源对象，这里使用数据源连接池
	@Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
			dataSource.setDriverClass(propconfig.getDriverClassName());
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataSource.setJdbcUrl(propconfig.getUrl());
		dataSource.setUser(propconfig.getUsername());
		dataSource.setPassword(propconfig.getPassword());
		dataSource.setInitialPoolSize(propconfig.getInitialPoolSize());
		dataSource.setMinPoolSize(propconfig.getMinPoolSize());
		dataSource.setMaxPoolSize(propconfig.getMaxPoolSize());
		dataSource.setAcquireIncrement(propconfig.getAcquireIncrement());
		dataSource.setUnreturnedConnectionTimeout(propconfig.getTimeout());
		dataSource.setIdleConnectionTestPeriod(propconfig.getIdleConnectionTestPeriod());
        
        return dataSource;
    }
    //加载hibernate的属性
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", propconfig.getDialect());
        properties.put("hibernate.show_sql", propconfig.isShow_sql());
        properties.put("hibernate.format_sql", propconfig.isFormat_sql());
        properties.put("current_session_context_class",propconfig.getCurrent_session_context_class());
        //properties.put("hibernate.cache.use_second_level_cache", propconfig.isUse_second_level_cache());
        //properties.put("hibernate.cache.use_query_cache", propconfig.isUse_query_cache());
        //properties.put("hibernate.cache.region.factory_class", propconfig.getFactory_class());
        
        return properties;        
    }
    //声明式事务处理
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}
