package spittr.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//声明本文件为一个配置文件
@Configuration
// 开启Spring Web mvc支持，被注解的类要实现WebMvcConfigurer接口
@EnableWebMvc
// 开始组件扫描
@ComponentScan("spittr.web")
public class WebConfig implements WebMvcConfigurer {

	//声明视图解析器bean,用于视图解析
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
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

}

/*
  我们都知道Jsp和servlet都需要web容器才能运行。但是实际上呢我们的web应用中可以没有任何servlet
  或者jsp（至少表面上是这样的）只需要一个web.xml，设置在servlet 3.0中，这个也可以省略。但是我们同样可以通过链接来访问应用内
  的资源文件。例如.jpg,.html,.js这类的静态文件。这就是DefaultServlet的作用了。Default Servlet和jsp servlet在tomat的安装目录下
  的conf/web.xml中有定义。该web.xml对于所有tomcat加载的的web application都会应用，会和application本身指定的web.xml进行合并。 

  当Dispatcher Servlet的mapping配置为/的时候，这时候会覆盖Default Servlet的配置(DispathcerServlet会更先匹配到)，
  那么对于.jpg,.png类似这种静态资源就得不到处理（之前是defaultServlet）会处理，所以这类资源就会得到404错误。

 问：/和/*　有什么区别？ 
 答：/：'/' 将会覆盖容器的default servlet而不覆盖jsp servlet, 所以静态资源会拦截，而jsp不会被拦截. 
 所以web.xml没有配置其他特殊路径的servlet, 基本上所有的请求都交由DispatcherServlet处理.
 
     /*：DispatcherServlet将会覆盖所有其他的servlet，包括由servlet容器提供的默认的servlet以及jsp servlet。
      你的任何请求将都会进入到这个servlet中，所以这并不是一个好的配置方式。 
      
    '*.xxx', 以指定后缀结尾的请求都交由DispatcherServlet处理
    
     例如：在webroot下面有一个test.jsp,当DispatcherServlet 配置映射/ 时，浏览器输入：
     http://localhost:8083/test.jsp 这个jsp是可以直接访问的，并且不经过DispatcherServlet ，
     而当DispatcherServlet 配置映射/* 时，这个请求就会被DispatcherServlet 拦截。
*/
