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
    
    <title>用户注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="static/css/styles.css">
	

  </head>
  
  <body>
    <h2>用户注册</h2>
    <form:form action="user/new" method="post" modelAttribute="user">
    	<table border="0">
    		<tr>
    			<td><label for="username">用户名：</label></td>
    			<td>
    				<form:input type="hidden" path="usertype" value="1"/>
    				<form:input  path="username" id="username"/>
    				<input type="button" id="checkUser" value="用户名检测"/>
    				<span class="error" id="msg"></span>
    			</td>
    			<td><form:errors path="username" cssClass="error"></form:errors></td>
    		</tr>
    		<tr>
    			<td><label for="password">密  码：</label></td>
    			<td><form:input  type="password" path="password" /></td>
    			<td><form:errors path="password" cssClass="error"></form:errors></td>
    		</tr>
    		<tr>
    			<td><label for="stuinfo.stuid">学号：</label></td>
    			<td><form:input  path="stuinfo.stuid" /></td>
    			<td><form:errors path="stuinfo.stuid" cssClass="error"></form:errors></td>
    		</tr>
    		<tr>
    			<td><label for="stuinfo.stuname">姓名：</label></td>
    			<td><form:input  path="stuinfo.stuname" /></td>
    			<td><form:errors path="stuinfo.stuname" cssClass="error"></form:errors></td>
    		</tr>
    		<tr>
    			<td><label for="stuinfo.gender">性别：</label></td>
    			<td>
    				<form:radiobutton  path="stuinfo.gender" value="男" label="男" checked="checked"/>
    				<form:radiobutton  path="stuinfo.gender" value="女" label="女" />
    			</td>
    			<td><form:errors path="stuinfo.gender" cssClass="error"></form:errors></td>
    		</tr>
    		<tr>
    			<td><label for="stuinfo.birthday">出生日期：</label></td>
    			<td><form:input  path="stuinfo.birthday" placeholder="yyyy-mm-dd"/></td>
    			<td><form:errors path="stuinfo.birthday" cssClass="error"></form:errors></td>
    		</tr>
    		<tr>
    			<td><label for="stuinfo.classname">班级：</label></td>
    			<td><form:input  path="stuinfo.classname" /></td>
    			<td><form:errors path="stuinfo.classname" cssClass="error"></form:errors></td>
    		</tr>
    		<tr>
    			<td><label for="stuinfo.address">籍贯：</label></td>
    			<td><form:input  path="stuinfo.address" /></td>
    			<td><form:errors path="stuinfo.address" cssClass="error"></form:errors></td>
    		</tr>
    		<tr>
    			<td colspan="3"><input type="submit" value="用户注册"/></td>
    		</tr>
    	</table>
    </form:form>
    <br/>
    <a href="index">返回首页</a>
    
  </body>
  
  <script type="text/javascript" src="static/js/jquery-3.3.1.min.js"></script>
  <script type="text/javascript">
  	var msg="${notify_msg}";
  	if(msg!=null && msg!="" && msg!=undefined){
  		alert(msg);
  	}
  	
  	//监听检测用户名按钮的click事件
  	$("#checkUser").on("click",function(){
  		var username=$("#username").val();
  		if(username==null || username=="" || username==undefined){
  			alert("请先输入用户名！")
  			return
  		}
  		var url="user/checkuser";
  		$.ajax({
  			url:url,
  			cache:false,
  			async:true,
  			type:"get",
  			dataType:"json",
  			data:{username:username},
  			success:function(data,status){
  				if(status=="success"){
  					$("#msg").text(data.msg);
  				}
  			},
  			error:function(XMLHttpRequest,textStatus,errorThrown){
  				alert("发送用户名检测请求失败！")
  			}
  		});  		
  	});
  </script>
</html>
