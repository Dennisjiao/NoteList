<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh">
<head>
	<base href="<%=basePath%>">
	<title>异常信息</title>
	<%@include file="common.jsp" %>	
	<link href="static/css/style.css" rel="stylesheet" type="text/css" >
</head>
<body>
	
	<div class="layui-container">
		<div class="layadmin-tips">
			 
			<div class="layui-text" style="font-size: 25px;margin:100px 0px 50px 0px;">
				<i class="layui-icon layui-icon-face-surprised" style="font-size: 70px; color: #1E9FFF;"></i>哎呀，好像出错了呢！
			</div>
			<div class="layui-text" style="font-size: 18px;margin:20px 0px;">出错地址：${url}</div>			
			<div class="layui-text" style="font-size: 18px;margin:20px 0px;">错误信息：${exception.getMessage()}</div>			
			<div class="layui-text" style="font-size: 18px;margin:20px 0px 0px 0px;">详细信息：<c:if test="${exception.getCause()!=null }">${exception.getCause().toString() }</c:if></div>
			<div class="layui-text" style="font-size: 18px;margin:20px 0px 0px 0px;">堆栈信息：${stacktrace}</div>								
		    
		</div>
	</div>	
	
	
</body>
</html>