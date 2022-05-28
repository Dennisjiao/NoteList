package student.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
 * 定义基类拦截器及@Interceptor注解
 */
public abstract class BaseInterceptor extends HandlerInterceptorAdapter {

	//用户类型
	public interface UserType {  
		static final int NOCHECK 	= 0;
		static final int STUDENT	= 1;		
		static final int ADMIN   	= 2;		
    }  
			
    public String success;
    public String failed;
    public int usertype;
    
    /*
     * 判断当前handler是否是需要拦截的handler，是返回真，不是返回假
     */
    public boolean isMyHandler(Object handler) {
    	//HandlerMethod encapsulates information about a handler method consisting of a method and a bean. Provides convenient access to method parameters, the method return value, method annotations, etc.
        if (!(handler instanceof HandlerMethod))
            return false;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //取Interceptor注解
        Interceptor interceptor = handlerMethod.getMethodAnnotation(Interceptor.class);
        if (interceptor == null)
            return false;
        System.out.println(interceptor.name());
        System.out.println(this.getClass().getSimpleName());
        System.out.println(this.getClass().getName());
        if (!interceptor.name().equals(this.getClass().getSimpleName()) && !interceptor.name().equals(this.getClass().getName()))
            return false;
        //取interceptor注解中的参数值
        success = interceptor.success();
        failed = interceptor.failed();
        usertype=interceptor.usertype();
        return true;
    }

    /**
     * 方法preHandle:   顾名思义，该方法将在请求处理之前进行调用。SpringMVC 中的Interceptor 是链式的调用的，
     * 在一个应用中或者说是在一个请求中可以同时存在多个Interceptor 。每个Interceptor 的调用会依据它的声明顺序依次执行，
     * 而且最先执行的都是Interceptor 中的preHandle 方法，所以可以在这个方法中进行一些前置初始化操作或者是对当前请求的
     * 一个预处理，也可以在这个方法中进行一些判断来决定请求是否要继续进行下去。该方法的返回值是布尔值Boolean 类型的，
     * 当它返回为false 时，表示请求结束，后续的Interceptor 和Controller 都不会再执行；当返回值为true 时就会继续调用
     * 下一个Interceptor 的preHandle 方法，如果已经是最后一个Interceptor 的时候就会是调用当前请求的Controller 方法。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //不是我要处理的拦截器时，提交给父类处理
    	if (isMyHandler(handler)) {
            return runHandler(request,response, runHandler(request, response,usertype));
        }
        return super.preHandle(request, response, handler);
    }
    
    //Controller方法调用之后,DispatcherServlet进行视图渲染之前被调用，
    //所以我们可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    //该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。这个方法的主要作用是用于进行资源清理工作的。
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
    throws Exception {
        // TODO Auto-generated method stub

    }

    
    //继承类必须要实现的函数,返回true代表要进行拦截，返回false代表不进行拦截
    public abstract boolean runHandler(HttpServletRequest request, HttpServletResponse response,int usertype);

    /**
     * 运行结果
     * 
     * @param response
     * @param isInterceptor
     *            是否拦截,true拦截,跳转向failed指向页面,false:不拦截,跳转向success指向页面;
     *            success和failed为空时不做任何操作
     * @return
     * @throws Exception
     */
    public boolean runHandler(HttpServletRequest request,HttpServletResponse response, boolean isInterceptor) throws Exception {
        //不拦截且success不为空时，跳转向success指向页面;
    	if (!isInterceptor) {
            if (!success.equals("")) {
            	
                response.sendRedirect(success);
                return false;
            }
        } //拦截且failed不为空时，跳转向failed指向页面
        else 
    	{
            if (!failed.equals("")) {
            	String requestType = request.getHeader("X-Requested-With");//识别ajax的响应头
            	//如果是ajax类型，响应logout给前台
        		if (requestType != null && requestType.equals("XMLHttpRequest")) {
        			String json="{\n\"code\":-10\n,\"msg\":\"session expire\",\n\"data\":\""+failed+"\"\n}"; 
    				response.getWriter().print(json);
    			}
        		else
        			response.sendRedirect(failed);
                return false;
            }
        }
        return true;
    }

    /*
     * @interface用来声明一个注解，其中的每一个方法实际上是声明了一个配置参数。方法的名称就是参数的名称，
     * 返回值类型就是参数的类型（返回值类型只能是基本类型、Class、String、enum）。可以通过default来声
     * 明参数的默认值。
     * 用法：@Interceptor(name = "LoginCheck", usertype=UserType.ADMIN,failed = "/user/login")
     */   

    /*@Target作用：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）。
     *取值(ElementType)有：

　　　　1.CONSTRUCTOR:用于描述构造器
　　　　2.FIELD:用于描述域
　　　　3.LOCAL_VARIABLE:用于描述局部变量
　　　　4.METHOD:用于描述方法
　　　　5.PACKAGE:用于描述包
　　　　6.PARAMETER:用于描述参数
　　　　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
    */
    
    /*@Retention作用：定义了该Annotation被保留的时间长短：某些Annotation仅出现在源代码中，而被编译器丢弃；而另一些却被编译在class文件中；
     * 编译在class文件中的Annotation可能会被虚拟机忽略，而另一些在class被装载时将被读取。@Retention表示需要在什么级别保存该注解信息，
     * 用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
     * 1.SOURCE:在源文件中有效（即源文件保留）
　　　 2.CLASS:在class文件中有效（即class保留）
　　　 3.RUNTIME:在运行时有效（即运行时保留）
     */
    @Target({ElementType.METHOD,ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface Interceptor {
    	
        public String name();
        
        public int usertype() default UserType.STUDENT;
        
        public String success() default "";

        public String failed() default "";
    }
}