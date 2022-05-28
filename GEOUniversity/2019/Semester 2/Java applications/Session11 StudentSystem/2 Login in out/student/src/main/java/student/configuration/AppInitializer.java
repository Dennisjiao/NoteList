package student.configuration;

import java.nio.charset.StandardCharsets;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

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
