<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
		<meta charset="utf-8" />
		<TITLE>梁山好汉首页</TITLE>
		<meta name="renderer" content="webkit">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
	    <link rel="stylesheet" href="layui/css/layui.css" media="all">		
		<link href="static/css/mycss.css" rel="stylesheet">
  </head>
  
  <body>
    <div class="layui-container">
	   <%@ include file="header.jsp" %>
	   <div class="background" >
	   
	   </div>
    </div>
  </body>
  <script src="layui/layui.js"></script> 
  <script>
	//注意：导航 依赖 element 模块，否则无法进行功能性操作
	layui.use('element', function(){
	  var element = layui.element;	  
	 
	});
  </script>
</html>
