package student.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
/*
 * 统一异常处理
 */
@ControllerAdvice
class GlobalDefaultExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "exception";

    private Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

  //@ExceptionHandler统一处理某一类异常，从而能够减少代码重复率和复杂度
    @ExceptionHandler(value = SQLException.class)
    public ModelAndView DataAccessExceptionHandler(HttpServletRequest req, SQLException e) throws Exception {
    	String errmsg=e.getMessage()+"\n";
    	if(e.getCause()!=null)
    		errmsg+=e.getCause().toString()+"\n";
    	errmsg+=getStackTrace(e);
    	logger.warn("GlobalDefaultExceptionHandler handing a Exception: " + errmsg); 	
    	// 如果异常使用了ResponseStatus注解，那么重新抛出该异常，Spring框架会处理该异常。 
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;

        // 否则创建ModleAndView，处理该异常。
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("stacktrace",getStackTrace(e));
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
    
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
    	String errmsg=e.getMessage()+"\n";
    	if(e.getCause()!=null)
    		errmsg+=e.getCause().toString()+"\n";
    	errmsg+=getStackTrace(e);
    	logger.warn("GlobalDefaultExceptionHandler handing a Exception: " + errmsg);
    	// 如果异常使用了ResponseStatus注解，那么重新抛出该异常，Spring框架会处理该异常。 
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;

        // 否则创建ModleAndView，处理该异常。
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);       
        mav.addObject("url", req.getRequestURL());
        mav.addObject("stacktrace",getStackTrace(e));
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
    /*
     * 获取异常的堆栈信息
     *     
     */
    private static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.close();
        return sw.toString();
    }
}