package spittr.config;

import java.nio.charset.StandardCharsets;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import spittr.web.WebConfig;

/*
 * 在Spring MVC中是可以创建出多个DispatcherServlet的（只要创建多个继承自AbstractAnnotationConfigDispatcherServletInitializer的类即可）。
 * 而每个DispatcherServlet有自己的应用上下文（WebApplicationContext），这个应用上下文只针对这个DispatcherServlet有用。
 * 这也就是getServletConfigClasses的作用是获取这个DispatcherServlet的应用上下文的配置类。
 * 
 * 而除了每个DispatcherServlet配置类的应用上下文之外，还有一个根应用上下文，这个应用上下文的作用是为了在多个
 * DispatcherServlet之间共享Bean，比如数据源Bean，这就是getRootConfigClasses的作用，用于返回根应用上下文的配置类。
 * Spring框架的机制会保证如果在当前DispatcherServlet的应用上下文中没有找到想要的bean时，会去根应用上下文中去找。
 * 
 * 当应用中只有一个DispatcherServlet中时，可以将所有的bean配置均写在根应用上下文中。 DispatcherServlet获取想要
 * 的bean时，如果没有在自己的应用上下文中找到，则会自动到根应用上下文中去找。
 */
public class SpitterWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// 指定根配置类,RootConfig配置类加载的是驱动应用后端的中间层和数据层组件，是父上下文。
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	// 指定配置类,WebConfig配置类中主要是内容是启用组件扫描，配置视图解析器，配置静态资源的处理。
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	// 将DispatcherServlet映射到"/"
	/*
	 * 问：/和/* 有什么区别？ 答：/：'/' 将会覆盖容器的default servlet,
	 * 凡是在web.xml文件中找不到匹配的URL，它们的访问请求都将交给该Servlet处理 (静态资源也将会拦截).
	 * 所以web.xml没有配置其他特殊路径的servlet, 基本上所有的请求都交由DispatcherServlet处理.
	 * 
	 * /*：DispatcherServlet将会覆盖所有其他的servlet，包括由servlet容器提供的默认的servlet以及jsp
	 * servlet。 你的任何请求将都会进入到这个servlet中，所以这并不是一个好的配置方式。
	 * 
	 * '*.xxx', 以指定后缀结尾的请求都交由DispatcherServlet处理
	 * 
	 * 例如：在webroot下面有一个test.jsp,当DispatcherServlet 配置映射/ 时，浏览器输入：
	 * http://localhost:8083/test.jsp 这个jsp是可以直接访问的，并且不经过DispatcherServlet ，
	 * 而当DispatcherServlet 配置映射/* 时，这个请求就会被DispatcherServlet 拦截。
	 */

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	// 配置其他的 servlet 和 filter
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		super.onStartup(container);
		
		// 字符集过滤器
		FilterRegistration.Dynamic encodingFilter = container.addFilter("encodingFilter",
				CharacterEncodingFilter.class);
		encodingFilter.setInitParameter("encoding", String.valueOf(StandardCharsets.UTF_8));
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, false, "/*");
		
	}
}