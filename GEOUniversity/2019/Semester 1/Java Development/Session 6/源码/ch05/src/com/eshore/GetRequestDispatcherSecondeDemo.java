﻿package com.eshore;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "getDispatcherSecondeDemo", 
		urlPatterns = { "/includeSeconde.do" }, 
		loadOnStartup = 0)
public class GetRequestDispatcherSecondeDemo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 返回页面消息
		response.setContentType("text/html;charset=UTF-8");// 设置响应的类型和编码
		PrintWriter out = response.getWriter();// 取得PrintWriter()对象
		out.println("  The seconcde Servlet<br/>");
	}
}
