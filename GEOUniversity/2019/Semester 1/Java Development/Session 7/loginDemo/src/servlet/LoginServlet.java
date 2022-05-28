package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.StringUtils;

import Enum.UserEnum;
import model.User;

@WebServlet(name="loginServlet",urlPatterns={"/login.htm"})
public class LoginServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		request.setCharacterEncoding("utf-8");
		String username=request.getParameter("username");
		String password=request.getParameter("passwd");
		int ret=checkLogin(username,password);
		if(ret==UserEnum.USERNAME_PASSWORD_NULL.getCode()){
			request.setAttribute("error", UserEnum.USERNAME_PASSWORD_NULL.getDesc());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else if(ret==UserEnum.USE_NOT_EXIST.getCode()){
			request.setAttribute("error", UserEnum.USE_NOT_EXIST.getDesc());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else if(ret==UserEnum.PASSWORD_NOT_MATCH.getCode()){
			request.setAttribute("error", UserEnum.PASSWORD_NOT_MATCH.getDesc());
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else{
			request.removeAttribute("error");
			request.getRequestDispatcher("/member.jsp").forward(request, response);
		}
		
	}
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException
	{
		
	}
	/*
	 * 验证用户登录 
	 */
	private int checkLogin(String username,String password)
	{
		User user=User.getInstance();
		String[] arr=null;
		Map<String,String> map=user.getUserMap();
		//判断用户名和密码是否为空==null, ==""
		if(StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)){
			return UserEnum.USERNAME_PASSWORD_NULL.getCode();
		}
		//用户不存在返回2
		if(map.get(username)!=null){
			arr=map.get(username).split("##");
			
		}
		else{
			return UserEnum.USE_NOT_EXIST.getCode();
		}
		//密码不相等返回3
		if(arr[0].equals(password))
		{
			return UserEnum.SUCCESS.getCode();
		}
		else{
			return UserEnum.PASSWORD_NOT_MATCH.getCode();
		}
	}
	
}
