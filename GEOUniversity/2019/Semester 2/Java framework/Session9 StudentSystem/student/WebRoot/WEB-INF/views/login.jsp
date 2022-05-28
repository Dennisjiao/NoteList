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
    
    <title>用户登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="static/css/styles.css">

  </head>
  
  <body>
    <h2>用户登录</h2>
    <hr/>
    <span class="error">${error}</span>
    <form:form  action="login" method="post" modelAttribute="user">
    	<table border="0">
    		<tr>
    			<td><label for="username">用户名：</label></td>
    			<td><form:input path="username" /></td>
    			<td><form:errors path="username" cssClass="error"></form:errors></td>
    		</tr>
    		<tr>
    			<td><label for="password">密  码：</label></td>
    			<td><form:input type="password" path="password" /></td>
    			<td><form:errors path="password" cssClass="error"></form:errors></td>
    		</tr>
    		<tr>
    			<td><label for="securityCode">验证码：</label></td>
    			<td><form:input  path="securityCode" autocomplete="off"/></td>
    			<td><form:errors path="securityCode" cssClass="error"></form:errors></td>
    		</tr>
    		<tr>
    			<td></td>
    			<td colspan="2"><img src="createImage" onclick="this.src='createImage?'+Math.random()" title="点击图片刷新验证码"/></td>    			
    		</tr>
    		<tr>    			
    			<td colspan="3"><input type="submit" value="登录"/></td>    			
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
