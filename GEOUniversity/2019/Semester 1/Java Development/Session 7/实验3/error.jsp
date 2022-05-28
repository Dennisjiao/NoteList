<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta content="text/html;charset=UTF-8">
    <title>新增会员失败</title>
  </head>
  
  <body>
  		<h2>新增会员失败</h2>
       <%
	       	//遍历错误信息
			List<String> list = (List<String>)request.getAttribute("errors");
			for(String str:list){
				out.println(str+"<br>");
			}
       %>
       <a href="register.jsp">返回注册首页</a>
  </body>
</html>
