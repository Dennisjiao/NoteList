package com.eshore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshore.pojo.User;

@WebServlet(name = "loginServlet", urlPatterns = { "/login.htm" })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("username");// 获取参数username的值
		String passwd = request.getParameter("passwd");// 获取参数passwd的值
		int ret=checkLogin(userName,passwd);
		if(ret==-1)
		{
			request.setAttribute("error", "用户名或密码不能为空！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else if	(ret==-2)
		{
			request.setAttribute("error", "用户名不存在！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else if	(ret==-3)
		{
			request.setAttribute("error", "密码不正确！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else
		{
			request.removeAttribute("error");
			request.getRequestDispatcher("/member.jsp").forward(request, response);
		}		
	}
	//验证登录用户
	public int checkLogin(String username,String passwd){
		User user = User.getInstance();
		String[] arr=null;
		Map<String,String> map = user.getUserMap();
		//用户名或密码为空返回-1
		if(username==null || username.isEmpty() || passwd==null || passwd.isEmpty())
		{
			return -1;
		}		
		//用户不存在返回-2
		if(map.get(username)!=null)
			arr = map.get(username).split("##");//分割Map中的值
		else
			return -2;
		//密码不相等返回-3
		if(arr[0].equals(passwd))
			return 1;
		else
			return -3;
		
	}
}
