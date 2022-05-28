package com.eshore;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "responseRedirectSecodDemo", 
		urlPatterns = { "/redirSeconde.do" })
public class ResponseRedirectSecondeDemo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");//第一句，设置服务器端编码
		response.setContentType("text/html;charset=utf-8");//第二句，设置浏览器端解码
		PrintWriter out = response.getWriter();// 取得PrintWriter()对象
		out.println(" Redirect跳转页面的第二个页面<br/>");
	}
}
