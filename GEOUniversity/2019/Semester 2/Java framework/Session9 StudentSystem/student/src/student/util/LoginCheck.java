package student.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import student.model.User;
/*
 * 用户登录统一验证
 */
public class LoginCheck extends BaseInterceptor{
	
	//拦截检查，返回true代表要进行拦截，返回false代表不进行拦截。
    @Override
    public boolean runHandler(HttpServletRequest request, HttpServletResponse response,int usertype) {
        
    	try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	HttpSession session=request.getSession();
    	User user=(User) session.getAttribute("user");    	
    	if(user==null || user.getUsername().equals(""))
		{
    		session.setAttribute("notify_msg", "您尚未登录或登录已过期，请先登录！");
    		return true;
		}
    	int session_usertype=user.getUsertype();    	
    	//NOCHECK代表不进行权限检查
    	if(usertype!=UserType.NOCHECK)
    	{
    		if(session_usertype<usertype)
        	{
        		session.setAttribute("notify_msg", "您的权限不足，请用管理员用户重新登录！");
        		return true;
        	}
    	}    	
    	session.setAttribute("notify_msg", "");
    	//false代表不拦截
        return false;
    }

}
