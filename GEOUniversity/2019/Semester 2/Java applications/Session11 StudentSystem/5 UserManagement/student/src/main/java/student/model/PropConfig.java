package student.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 用于读取application.properties配置文件中的配置数据
 * @author lhzxx
 *
 */
/*@PropertySource + Environment，通过@PropertySource注解将properties配置文件中的值存储到Spring的 Environment中，
 * Environment接口提供方法去读取配置文件中的值，参数是properties文件中定义的key值。
 * 
 * @PropertySource注解可以配置读取单个或多个配置文件
 * @PropertySource(value = {"classpath:spring/config.properties","classpath:spring/news.properties"})
 * 
 */
@Component
@PropertySource(value = { "classpath:application.properties" })
public class PropConfig {
	
	//数据库连接驱动类名
	@Value("${jdbc.driverClassName}")
	private String driverClassName="com.mysql.jdbc.Driver";
	
	//数据库连接url
	@Value("${jdbc.url}")
	private String url;
	
	//数据库连接用户名
	@Value("${jdbc.username}")
	private String username;
	
	//数据库连接密码
	@Value("${jdbc.password}")
	private String password;
	
	//hibernate方言
	@Value("${hibernate.dialect}")
	private String dialect="org.hibernate.dialect.MySQLDialect";
	
	@Value("${hibernate.show_sql}")
	private boolean show_sql=true;
	
	@Value("${hibernate.format_sql}")
	private boolean format_sql=true;
	
	@Value("${hibernate.current_session_context_class}")
	private String current_session_context_class="org.springframework.orm.hibernate5.SpringSessionContext";
	
	//数据库连接池保留的最大连接数
	@Value("${c3p0.maxPoolSize}")
	private Integer maxPoolSize=16;
	
	//数据库连接池保留的最小连接数
	@Value("${c3p0.minPoolSize}")
	private Integer minPoolSize=2;
	
	//数据库连接池初始化连接数
	@Value("${c3p0.initialPoolSize}")
	private Integer initialPoolSize=2;
	
	//当连接池中的连接耗尽的时候c3p0一次同时获取的连接数
	@Value("${c3p0.acquireIncrement}")
	private Integer acquireIncrement=2;
	
	//设定数据库连接超时时间，以秒为单位。如果连接池中某个数据库连接处于空闲状态且超过timeout秒时，就会从连接池中移除
	@Value("${c3p0.timeout}")
	private Integer timeout=120;
	
	//每60秒检查所有连接池中的空闲连接。Default: 0 不检测
	//C3P0会有一个Task检测pool内的连接是否正常，此参数就是Task运行的频率。默认值为0，表示不进行检测。
	@Value("${c3p0.idleConnectionTestPeriod}")
	private Integer idleConnectionTestPeriod=60;
	
	//是否开启hibernate二级缓存
	@Value("${hibernate.cache.use_second_level_cache}")
	private boolean use_second_level_cache=true;
	
	//是否开启Hibernate查询缓存
	@Value("${hibernate.cache.use_query_cache}")
	private boolean use_query_cache=false;
	
	//二级缓存工厂类，ehcache2.5以上版用要用SingletonEhCacheRegionFactory才行
	@Value("${hibernate.cache.region.factory_class}")
	private String factory_class="org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory";
	
	public PropConfig()
	{
		
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public boolean isShow_sql() {
		return show_sql;
	}

	public void setShow_sql(boolean show_sql) {
		this.show_sql = show_sql;
	}

	public boolean isFormat_sql() {
		return format_sql;
	}

	public void setFormat_sql(boolean format_sql) {
		this.format_sql = format_sql;
	}

	public Integer getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(Integer maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public Integer getMinPoolSize() {
		return minPoolSize;
	}

	public void setMinPoolSize(Integer minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public Integer getInitialPoolSize() {
		return initialPoolSize;
	}

	public void setInitialPoolSize(Integer initialPoolSize) {
		this.initialPoolSize = initialPoolSize;
	}

	public Integer getAcquireIncrement() {
		return acquireIncrement;
	}

	public void setAcquireIncrement(Integer acquireIncrement) {
		this.acquireIncrement = acquireIncrement;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Integer getIdleConnectionTestPeriod() {
		return idleConnectionTestPeriod;
	}

	public void setIdleConnectionTestPeriod(Integer idleConnectionTestPeriod) {
		this.idleConnectionTestPeriod = idleConnectionTestPeriod;
	}

	public boolean isUse_second_level_cache() {
		return use_second_level_cache;
	}

	public void setUse_second_level_cache(boolean use_second_level_cache) {
		this.use_second_level_cache = use_second_level_cache;
	}

	public boolean isUse_query_cache() {
		return use_query_cache;
	}

	public void setUse_query_cache(boolean use_query_cache) {
		this.use_query_cache = use_query_cache;
	}

	public String getFactory_class() {
		return factory_class;
	}

	public void setFactory_class(String factory_class) {
		this.factory_class = factory_class;
	}

	public String getCurrent_session_context_class() {
		return current_session_context_class;
	}

	public void setCurrent_session_context_class(String current_session_context_class) {
		this.current_session_context_class = current_session_context_class;
	}
	

}
