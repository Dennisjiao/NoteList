<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link href="static/css/style.css" rel="stylesheet" type="text/css" >
<link href="static/layuiadmin/style/login.css" rel="stylesheet" type="text/css">
</head>
<body>
	
	<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;background:url(static/images/register.jpg); background-repeat: no-repeat;background-size: 100% 100%;">
		<div class="layadmin-user-login-main">
			<div class="layadmin-user-login-box layadmin-user-login-header">
				<a href="index"><span>学生成绩管理系统</span></a>
				<p>学生注册</p>
			</div>
			<div class="layadmin-user-login-box layadmin-user-login-body layui-form">
				<form class="layui-form" action="register" method="post" >
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-username"	for="username"></label>
						<input type="text" name="username"  lay-verify="username" placeholder="输入用户名" class="layui-input"/>
						<input type="hidden" name="usertype" value="1"/>
					</div>					
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-password"	for="password"></label>
						<input type="password"	name="password"  lay-verify="password"	placeholder="请输入6位以上的密码" class="layui-input"/>
					</div>
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="repass"></label>
						<input type="password"	name="rpasswd"  lay-verify="rpasswd" placeholder="请再次输入6位以上的确认密码" class="layui-input"/>
					</div>
						
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-menu-fill"	for="stuinfo.stuid"></label>
						<input type="text" name="stuinfo.stuid"  lay-verify="stuid" placeholder="输入学号" class="layui-input"/>
					</div>
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-about"	for="stuinfo.stuname"></label>
						<input type="text" name="stuinfo.stuname"  lay-verify="required" placeholder="输入姓名" class="layui-input"/>
					</div>	
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-male" for="stuinfo.gender"></label>
						<div class="layui-input-block" style="margin-left:40px;">						
							<input type="radio"  name="stuinfo.gender"  value="男"  title="男"  checked="checked"/>
							<input type="radio"  name="stuinfo.gender"  value="女"  title="女" />
						</div>
					</div>
					<div class="layui-form-item">
						<button class="layui-btn layui-btn-fluid" lay-submit id="registMember" lay-filter="registMember">注册学生</button>
					</div>
					<div class="layui-form-item" >
						<a href="index" class="layadmin-user-jump-change layadmin-link">返回首页</a>
					</div>						
				</form>
			</div>
		</div>
	</div>
	
	<!-- 设置错误输出信息 -->
	<script type="text/javascript">
		window.notifymsg="${notify_msg}";
	</script>
	<script src="static/layuiadmin/layui/layui.js" type="text/javascript"></script>
	<script src="static/js/register.js" type="text/javascript"></script>	
</body>
</html>