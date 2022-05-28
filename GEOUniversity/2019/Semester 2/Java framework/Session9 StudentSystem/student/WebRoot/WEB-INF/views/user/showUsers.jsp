<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>编辑删除用户</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="static/css/styles.css">

</head>

<body>
	<h2>编辑删除用户</h2>
   <hr>
   <table border="1">
   	<tr>
   		<td>用户名</td>
   		<td>密码</td>
   		<td>学号</td>
   		<td>姓名</td>
   		<td>性别</td>
   		<td>出生日期</td>
   		<td>班级</td>
   		<td>籍贯</td>
   		<td>操作</td>
   		
   	</tr>
   	<c:forEach items="${users}" var="user">
		<tr>
			<td>${user.username}</td>
			<td>${user.password}</td>
			<td>${user.stuinfo.stuid}</td>
			<td>${user.stuinfo.stuname}</td>
			<td>${user.stuinfo.gender}</td>
			<td>${user.stuinfo.birthday}</td>
			<td>${user.stuinfo.classname}</td>
			<td>${user.stuinfo.address}</td>
			<td>
				<a href="<c:url value='user/edit-${user.stuinfo.stuid}-user' />">编辑</a>&nbsp;&nbsp;
				<a href="<c:url value='user/delete-${user.stuinfo.stuid}-user' />">删除</a>
			</td>		
		</tr>
	</c:forEach>
   </table>
   <br/>
   <a href="index">返回首页</a>
   
   <script type="text/javascript">
		var msg="${notify_msg}";
		if(msg!=null && msg!="")
			alert(msg);
	</script>
	
</body>
</html>
