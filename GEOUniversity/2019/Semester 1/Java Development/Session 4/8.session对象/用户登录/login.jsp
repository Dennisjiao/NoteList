<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
      <center>
       	    <font size="8">用户登录</font>
       	    <hr/>
       	    <form action="validate.jsp" method="post">
       	          用户名称：<input type="text" name="username"/>
       	    <br/>
       	          用户密码：<input type="password" name="password"/>
       	    <br/>
       	    <input type="submit" value="登录"/>
       	    </form>
      </center>
      
  </body>
</html>
