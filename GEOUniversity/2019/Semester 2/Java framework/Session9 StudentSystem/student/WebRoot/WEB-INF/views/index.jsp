<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生成绩管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table border="0" width="50%">
    	<tr>
    		<td colspan="5" align="center"><h2>学生成绩管理系统</h2></td>
    	</tr>
    	<tr>
    		<td colspan="5" align="right">
    			<c:if test="${empty user.username }">
    				<a href="login">请登录</a>
    			</c:if>
    			<c:if test="${not empty user.username }">
    				当前用户：${user.username }
    				<c:if test="${user.usertype==2 }">*</c:if>&nbsp;&nbsp;<a href="logout">登出</a>
    			</c:if>
    		</td>
    	</tr>
    	<tr>
    		<td align="right">用户管理：</td>
    		<td align="left"><a href="user/new">用户注册</a></td>
    		<td align="left"><a href="user/show-delete">删除用户</a></td>
    		<td align="left"><a href="user/show-modify">修改用户</a></td>
    		<td align="left"><a href="user/query">查询用户</a></td>
    	</tr>
    </table>

	<script type="text/javascript">
    	var msg="${notify_msg}";
    	if(msg!=null && msg!="" && msg!=undefined){
    		alert(msg);
    	}
    </script>
    <!-- 清除显示过的提示信息 -->
    <c:set var="notify_msg" value="" scope="session"/>
  </body>
</html>
