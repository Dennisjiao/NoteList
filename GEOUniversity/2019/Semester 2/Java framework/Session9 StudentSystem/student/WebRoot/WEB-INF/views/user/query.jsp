<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>查询用户</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="static/css/styles.css">

</head>

<body>
	<h2>查询用户</h2>
   <hr>
		
	<form:form action="user/query" method="post" modelAttribute="user">
		<table border="0">
			<tr>
				<td>用户名:</td>
				<td><form:input  path="username" /></td>
				<td>学号:</td>
				<td><form:input  path="stuinfo.stuid" /></td>
			</tr>
			<tr>
				<td>姓名:</td>
				<td><form:input  path="stuinfo.stuname" /></td>
				<td>性别:</td>
				<td><form:radiobutton path="stuinfo.gender" value="男" label="男" />
            		<form:radiobutton path="stuinfo.gender" value="女" label="女" /></td>
			</tr>							
			<tr>				
				<td colspan="4">
					<input type="hidden" name="page" value="${pageobj.page }" id="page"/>
					<input type="hidden" name="limit" value="${pageobj.limit }" id="limit"/>
					<input type="submit" value="查询"/>
				</td>
			</tr>		
		</table>	  
	</form:form> 
	<c:if test="${fn:length(users)>0 }">
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
				</tr>
			</c:forEach>
	   </table>
	   <br/>
	   <a href="javascript:void(0);" id="firstpage">首页</a>
	   <c:if test="${ pageobj.hasPreviousPage}">
	   		<a href="javascript:void(0);" id="prevpage">上一页</a>
	   </c:if>
	   <c:if test="${ pageobj.hasNextPage}">
	   		<a href="javascript:void(0);" id="nextpage">下一页</a>
	   </c:if>
	   <a href="javascript:void(0);" id="lastpage">尾页</a>
	</c:if>
	<br/>
	<br/>
	<br/>
	<a href="index">返回首页</a>
	
	<script type="text/javascript" src="static/js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		$("#firstpage").on("click", function() {
			$("#page").val(1);
			$("form").submit();
		});
		$("#prevpage").on("click", function() {
			$("#page").val(${pageobj.prePage});
			$("form").submit();
		});
		$("#nextpage").on("click", function() {
			$("#page").val(${pageobj.nextPage});
			$("form").submit();
		});
		$("#lastpage").on("click", function() {
			$("#page").val(${pageobj.totalPage});
			$("form").submit();
		});
	</script>
</body>
</html>
