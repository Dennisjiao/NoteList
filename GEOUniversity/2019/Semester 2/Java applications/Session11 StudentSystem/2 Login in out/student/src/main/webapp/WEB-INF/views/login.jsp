<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh">
<head>
	<base href="<%=basePath%>">
	<title>学生成绩管理系统</title>
	<%@include file="common.jsp" %>	
	<link href="static/layuiadmin/style/login.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
	<!--[if lt IE 9]>
	  <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
	  <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<div class="layadmin-user-login layadmin-user-display-show"	
			id="LAY-user-login" style="display: none; background:url(static/images/login.jpg); background-repeat: no-repeat;background-size: 100% 100%;">

		<div class="layadmin-user-login-main">
			<div class="layadmin-user-login-box layadmin-user-login-header">
				<a href="index"><span>学生成绩管理系统</span></a>
				<p>登录</p>
			</div>			
			<div class="layadmin-user-login-box layadmin-user-login-body layui-form">								
				<form:form class="layui-form" action="login" method="post" modelAttribute="user">					
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-username"	for="username"></label>
						<form:input type="text" path="username"   placeholder="请输入用户名" class="layui-input" lay-verify="required"/>
						<span style="color:#FF5722;"><form:errors path="username" /></span>
					</div>
					<div class="layui-form-item">
						<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="password"></label>
						<form:input type="password" path="password"  placeholder="请输入密码" autocomplete="off" class="layui-input" lay-verify="password"/>						
						<span style="color:#FF5722;"><form:errors path="password" /></span>						
					</div>
					
					<div class="layui-form-item">
						<div class="layui-row">
							<div id="slider_drag" class="hk_drag" >
								<div class="hk_bg"></div>
						        <div class="hk_text" onselectstart="return false;">拖动滑块验证</div>
						        <div class="hk_btn">&gt;&gt;</div>
							</div>							
						</div>
						<input type="checkbox" name="remeberme" title="记住我" lay-skin="primary">
					</div>
					<div class="layui-form-item">
						<button class="layui-btn layui-btn-fluid mt20" lay-submit lay-filter="login">登 入</button>						
					</div>
					<div class="layui-form-item" style="margin-bottom: 10px;">	
						<a href="register" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">注册帐号</a>
						<a href="forgetpwd" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;margin-right:5px;">忘记密码？</a>						
					</div>									
				</form:form>
			</div>
		</div>
	</div>	
	<!-- 设置错误输出信息 -->
	<script type="text/javascript">
		window.notifymsg="${notify_msg}";	
	</script>
	<script src="static/layuiadmin/layui/layui.js" type="text/javascript"></script>
	<script src="static/js/jquery-3.3.1.min.js"></script>	
	<script src="static/js/jquery.cookie.js" type="text/javascript"></script>
	<script src="static/js/jquery.base64.js" type="text/javascript"></script>
	<script src="static/js/login.js" type="text/javascript"></script>		
	<!-- 清除显示过的提示信息 -->
	<c:set var="notify_msg" value="" scope="session"/>	
</body>
</html>