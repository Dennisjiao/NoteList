<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta content="text/html;charset=UTF-8" http-equiv="Content-Type">
    <title>JSP include指令演示</title>
  </head>
  <body>
	<jsp:include page="john.html"/>
    <br>
	<div align="center">JSP include指令演示</div>
	<jsp:include page="copyRight.jsp" flush="true"/>
  </body>
</html>
