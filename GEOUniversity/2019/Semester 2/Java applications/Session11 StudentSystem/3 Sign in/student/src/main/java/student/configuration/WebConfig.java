package student.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import student.util.LoginCheck;


@Configuration
@EnableWebMvc
//开启缓冲
//@EnableCaching
@ComponentScan(basePackages = "student")
public class WebConfig implements WebMvcConfigurer {
	
	//声明视图解析器bean,用于视图解析
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
	//声明一个bean，用于读取message.properties文件的内容
	@Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("resources/messages");
	    return messageSource;
	}
	/*
	 * Configures a request handler for serving static resources by forwarding the request to the Servlet 
	 * container's "default" Servlet. This is intended to be used when the Spring MVC DispatcherServlet is 
	 * mapped to "/" thus overriding the Servlet container's default handling of static resources.
	 *
	 * 此时会注册一个默认的Handler：DefaultServletHttpRequestHandler，这个Handler也是用来处理静态文件的，
	 * 它会尝试映射/*。当DispatcherServelt映射/时，并且没有找到合适的Handler来处理请求，
	 * 就会交给DefaultServletHttpRequestHandler 来处理。注意：这里的静态资源是放置在web根目录下，而非WEB-INF 下。
	 *
	 *例如：在webroot目录下有一个图片：1.png 我们知道Servelt规范中web根目录（webroot）下的文件可以直接访问的，
	 *但是由于DispatcherServlet配置了映射路径是：/ ，它几乎把所有的请求都拦截了，从而导致1.png 访问不到，
	 *这时注册一个DefaultServletHttpRequestHandler 就可以解决这个问题。其实可以理解为DispatcherServlet破坏了
	 *Servlet的一个特性（根目录下的文件可以直接访问），DefaultServletHttpRequestHandler是帮助回归这个特性的。
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	/*此方法用来专门注册一个Handler，来处理静态资源的,例如：图片，js，css等
	 * 
	 * 例如：registry.addResourceHandler("/resource/**").addResourceLocations("/WEB-INF/static/");
	 * 当你请求http://localhost:8083/resource/1.png时，会把/WEB-INF/static/1.png返回。
	 * 注意：这里的静态资源是放置在WEB-INF目录下的。
	 *
	 *  /* 是拦截所有的文件夹，不包含子文件夹
	 *	/** 是拦截所有的文件夹及里面的子文件夹
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
	
	//添加拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginCheck())
		.addPathPatterns("/**")
		.excludePathPatterns("/login");
		
	} 
//	/*
//	 * CacheManager是Ehcache框架的核心类和入口，它负责管理一个或多个Cache对象。
//	 * 要使用Ehcache框架，必须要先创建 CacheManager 对象
//	 */
//	@Bean
//	 public EhCacheCacheManager getCacheManager(){
//	   return  new EhCacheCacheManager(getEhCacheFactory().getObject());
//	 }
//	/*EhCacheManagerFactoryBean是Spring内置的一个可以产生Ehcache的CacheManager对象的FactoryBean。
//	 * 其可以通过属性configLocation指定用于创建CacheManager的Ehcache配置文件的路径，通常是ehcache.xml文件的路径。
//	 * 如果没有指定configLocation，则将使用默认位置的配置文件创建CacheManager，这是属于Ehcache自身的逻辑，
//	 * 即如果在classpath根路径下存在ehcache.xml文件，则直接使用该文件作为Ehcache的配置文件，否则将使用ehcache-xxx.jar中
//	 * 的ehcache-failsafe.xml文件作为配置文件来创建Ehcache的CacheManager。
//	 * 
//	 */
//	@Bean
//	public EhCacheManagerFactoryBean getEhCacheFactory()
//	{
//		EhCacheManagerFactoryBean factoryBean =new EhCacheManagerFactoryBean();		
//		factoryBean .setConfigLocation(new ClassPathResource("resources/ehcache.xml"));
//		//由于hibernate也使用了Ehcache, 保证双方都使用同一个缓存管理器
//		factoryBean .setShared(true);
//        return factoryBean ;
//	}
}

