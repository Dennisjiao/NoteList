<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>会员登录页面</title>
  </head>  
  <body>
       <center>会员登录<br/>
       		<c:if test="${not empty requestScope.error}">
       			${requestScope.error }
       		</c:if>
      		<form action="<%=request.getContextPath()%>/login.htm" method="post">
      			<table border="1">
      			     <tr>
   		             <td>登录名：</td>
   		             <td><input name="username"/></td>
   		         </tr>
   		         <tr>
   		             <td>密码：</td>
   		             <td><input name="passwd" type="password"/></td>
   		         </tr>
   		         <tr align="center">
   		             <td colspan="2"><input type="submit" value="提交"/></td>
   		         </tr>
      			</table>
      		</form>
      		<a href="register.jsp">注册</a>
       </center>       
  </body>
</html>
