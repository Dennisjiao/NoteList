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
    
    public boolean isMyHandler(Object handler) {
        if (!(handler instanceof HandlerMethod))
            return false;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Interceptor interceptor = handlerMethod.getMethodAnnotation(Interceptor.class);
        if (interceptor == null)
            return false;
        if (!interceptor.name().equals(this.getClass().getSimpleName()) && !interceptor.name().equals(this.getClass().getName()))
            return false;
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
        if (isMyHandler(handler)) {
            return runHandler(request,response, runHandler(request, response,usertype));
        }
        return super.preHandle(request, response, handler);
    }
    
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
    throws Exception {
        // TODO Auto-generated method stub

    }

    
    //继承类必须要实现的函数
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

    @Target({ElementType.METHOD,ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public static @interface Interceptor {
        public String name();
        
        public int usertype() default UserType.STUDENT;
        
        public String success() default "";

        public String failed() default "";
    }
}