<%-- @page language="java" import="java.util.*" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" --%>
<%
	response.setContentType("text/html;charset=UTF-8");
    String str = new String("这是测试例子".getBytes("ISO-8859-1"),"utf-8");	
	//String str = "这是测试例子";
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>page 指令setContentType.jsp</title>
  </head>
  
  <body>
      <p>这里是一段中文字符</p>
      </br>
      <%=str %>
  </body>
</html>
