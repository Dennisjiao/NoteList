<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh">
<head>
<base href="<%=basePath%>">
<title>学生成绩管理系统</title>
<%@include file="common.jsp"%>
<link rel="stylesheet" href="static/layuiadmin/style/admin.css"	media="all">
</head>
<body>
    
</body>
</html>
