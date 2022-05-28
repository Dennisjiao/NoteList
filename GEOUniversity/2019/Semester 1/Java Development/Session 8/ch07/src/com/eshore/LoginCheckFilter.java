package com.eshore;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//@WebFilter(
//		asyncSupported = true,
//		description = "登录过滤器", 
//		filterName = "LoginCheckFilter", 
//		urlPatterns = { "/success.jsp" },
//		initParams = { 
//			@WebInitParam(name = "input", value = "login.jsp")
//		} 
//	)
public class LoginCheckFilter implements Filter {

	private String input="";
	
	public void init(FilterConfig filterConfig) throws ServletException {
		//通过filterConfig获得初始化中编码值
		input = filterConfig.getInitParameter("input");		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean flag=false;
		//分别对请求和响应进行编码设置
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session=req.getSession();
		String session_user=(String)session.getAttribute("user");
		if(session_user==null || session_user.isEmpty())
		{
			RequestDispatcher dispatcher=req.getRequestDispatcher(input);
			dispatcher.forward(req, response);
		}
		Enumeration<?> enumeration=req.getParameterNames();
		while(enumeration.hasMoreElements())
		{
			String name=(String)enumeration.nextElement();
			String value=(String)req.getParameter(name);
			if(name!=null && name.equals("user") && value!=null && value.equals(session_user))
			{
				flag=true;
				break;
			}				
		}
		if(flag){
			//传输给过滤器链过滤
			chain.doFilter(req, response);
		}
		else
		{
			RequestDispatcher dispatcher=req.getRequestDispatcher(input);
			dispatcher.forward(req, response);
		}

	}

}
