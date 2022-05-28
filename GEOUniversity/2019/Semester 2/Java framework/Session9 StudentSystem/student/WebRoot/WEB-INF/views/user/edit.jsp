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

<title>修改用户</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="static/css/styles.css">

</head>

<body>
	<h2>修改用户</h2>
   <hr>
		
	<form:form action="user/update" method="post" modelAttribute="user">
		<table border="0">
			<tr>
				<td><label for="username">用户名:</label></td>
				<td>
					<form:input type="hidden" path="usertype" value="1"/>
					<form:input  path="username" id="username" readonly="true"/>					
				</td>
				<td><form:errors path="username" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label for="password">密　码:</label></td>
				<td><form:input  type="password" path="password" /></td>
				<td><form:errors path="password" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label for="stuinfo.stuid">学号:</label></td>
				<td><form:input  path="stuinfo.stuid" readonly="true"/></td>
				<td><form:errors path="stuinfo.stuid" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label for="stuinfo.stuname">姓名:</label></td>
				<td><form:input  path="stuinfo.stuname" /></td>
				<td><form:errors path="stuinfo.stuname" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label for="stuinfo.gender">性别:</label></td>
				<td><form:radiobutton path="stuinfo.gender" value="男" label="男"  checked="checked"/>
            		<form:radiobutton path="stuinfo.gender" value="女" label="女" /></td>
				<td><form:errors path="stuinfo.gender" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label for="stuinfo.birthday">出生日期:</label></td>
				<td><form:input  path="stuinfo.birthday" placeholder="yyyy-mm-dd"/></td>
				<td><form:errors path="stuinfo.birthday" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label for="stuinfo.classname">班级:</label></td>
				<td><form:input  path="stuinfo.classname" /></td>
				<td><form:errors path="stuinfo.classname" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label for="stuinfo.address">籍贯:</label></td>
				<td><form:input  path="stuinfo.address" /></td>
				<td><form:errors path="stuinfo.address" cssClass="error"/></td>
			</tr>					
			<tr>				
				<td colspan="3"><input type="submit" value="修改"/></td>
			</tr>		
		</table>	  
	</form:form> 
	<br/>
	<a href="index">返回首页</a>
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
