<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh">
<head>
<base href="<%=basePath%>">
<title>用户管理</title>
<%@include file="../common.jsp"%>
<link rel="stylesheet" href="static/layuiadmin/style/admin.css"
	media="all">
<link rel="stylesheet" href="static/css/frontend.css" media="all">
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-card-body">
				<form class="layui-form" action="">
					<div class="layui-inline">
						<label class="layui-form-label" style="width:50px;">用户名</label>
						<div class="layui-input-inline" style="width: 150px;">
							<input type="text" name="username" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label" style="width:50px;">学号</label>
						<div class="layui-input-inline" style="width: 150px;">
							<input type="text" name="stuinfo.stuid" id="stuid" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label" style="width:50px;">姓名</label>
						<div class="layui-input-inline" style="width: 150px;">
							<input type="text" name="stuinfo.stuname" id="stuname" autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label" style="width:50px;">性别</label>
						<div class="layui-input-inline" style="width: 150px;">
							<input type="radio"  name="stuinfo.gender"  value="男"  title="男"  checked="checked"/>
							<input type="radio"  name="stuinfo.gender"  value="女"  title="女" />
						</div>
					</div>
					<div class="layui-input-inline">	                				                			
		                <button type="button" class="layui-btn" id="search"  >查询</button>
		                <button type="button" class="layui-btn layui-btn-primary" id="reset" >清空</button>				
		            </div>
				</form>
				<div class="mtb20"></div>
				<table class="layui-hide" id="student_table" lay-filter="table-operate"></table>
				<script type="text/html" id="table_operate">							
              		<a class="layui-btn layui-btn-normal layui-btn-sm" lay-event="edit">编辑</a>
					<a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="delete">删除</a>						
            	</script>
            	<script type="text/html" id="toolbar">
		         	<div class="layui-btn-container">
		             	<button class="layui-btn layui-btn-sm" lay-event="new">新增学生</button>
						<button class="layui-btn layui-btn-sm" lay-event="batchdelete">批量删除</button>
		         	</div>
		    	</script>
			</div>
		</div>
	</div>
	<%@include file="../footer.jsp"%>
	<script src="static/layuiadmin/layui/layui.js" type="text/javascript"></script>
	<script src="static/js/user.js" type="text/javascript"></script>
</body>
</html>