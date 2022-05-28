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
<title>用户编辑</title>
<%@include file="../common.jsp"%>
<link rel="stylesheet" href="static/layuiadmin/style/admin.css"
	media="all">
<link rel="stylesheet" href="static/css/frontend.css" media="all">
</head>
<body>
	<div class="layui-fluid">
		<form class="layui-form" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">用户名</label>
				<div class="layui-input-block">
					<c:if test="${dialogType=='new'}">
						<input type="text" name="username" id="username"
							value="${user.username}" lay-verify="username"
							placeholder="输入用户名" class="layui-input" />
					</c:if>
					<c:if test="${dialogType=='edit'}">
						<input type="text" name="username" id="username"
							value="${user.username}" readonly="true" lay-verify="username"
							placeholder="输入用户名" class="layui-input" />
					</c:if>
					<input type="hidden" name="usertype" value="1" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-block">
					<input type="password" name="password" lay-verify="password"
						value="${user.password}" placeholder="请输入6位以上的密码"
						class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">确认密码</label>
				<div class="layui-input-block">
					<input type="password" name="rpasswd" lay-verify="rpasswd"
						value="${user.password}" placeholder="请再次输入6位以上的确认密码"
						class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">学号</label>
				<div class="layui-input-block">
					<input type="text" name="stuinfo.stuid" lay-verify="stuid"
						value="${user.stuinfo.stuid}" placeholder="输入学号"
						class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">姓名</label>
				<div class="layui-input-block">
					<input type="text" name="stuinfo.stuname" lay-verify="required"
						value="${user.stuinfo.stuname}" placeholder="输入姓名"
						class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">性别</label>
				<div class="layui-input-block">
					<c:if
						test="${user.stuinfo.gender=='男' || empty user.stuinfo.gender }">
						<input type="radio" name="stuinfo.gender" value="男" title="男"
							checked="checked" />
						<input type="radio" name="stuinfo.gender" value="女" title="女" />
					</c:if>
					<c:if test="${user.stuinfo.gender=='女' }">
						<input type="radio" name="stuinfo.gender" value="男" title="男" />
						<input type="radio" name="stuinfo.gender" value="女" title="女"
							checked="checked" />
					</c:if>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">出生日期</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" id="birthday"
						value="${user.stuinfo.birthday}" name="stuinfo.birthday"
						autocomplete="off" placeholder="yyyy-MM-dd" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">班级</label>
				<div class="layui-input-block">
					<input type="text" name="stuinfo.classname" placeholder="输入班级"
						value="${user.stuinfo.classname}" class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">籍贯</label>
				<div class="layui-input-block">
					<input type="text" name="stuinfo.address" placeholder="输入籍贯"
						value="${user.stuinfo.address}" class="layui-input" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"></label>
				<div class="layui-input-block">
					<button class="layui-btn layui-btn-normal" lay-submit
						lay-filter="user-submit">${title}</button>
				</div>

			</div>

		</form>
	</div>
	<script src="static/layuiadmin/layui/layui.js" type="text/javascript"></script>
	<script type="text/javascript">
	layui.config({
	base : 'static/layuiadmin/' //静态资源所在路径
	}).extend({
		index : 'lib/index' //主入口模块
	}).use([ 'layer', 'form', 'laydate' ], function() {
		var $ = layui.$,
			layer = layui.layer,
			laydate = layui.laydate,
			form = layui.form;
		form.render();
		laydate.render({
			elem : '#birthday'
		});
		
		//登录表单验证
		form.verify({
			username:function(value, item){
				if(${dialogType=='new'})
				{
					if(value==null || value==undefined || value=="" )
						return '用户名不为能空';
					var isexist=false;
					var url = 'checkuser';
					$.ajax({
						url : url,
						cache : false,
						async : false,			//同步校验
						type : "get",		    //数据发送方式
						dataType : "json", 		//接受数据格式
						data : {
							username : value
						},
						success : function(data, status) {
							if (status == "success") {
								if (data.code)
									isexist=true;						
							}
						}
					});
					if(isexist){
						return "该用户名已被注册！";
					}					
				}					
			},		
			password:function(value, item){
				if(value==null || value==undefined || value=="" )
					return '密码不为能空';
				if(value.length<4 || value.length>32)
					return '密码长度应在4和32之间';			
			},
			rpasswd:function(value, item){
				if(value==null || value==undefined || value=="" )
					return '确认密码不为能空';
				if(value.length<4 || value.length>32)
					return '确认密码长度应在4和32之间';	
				if(value!=item.form.password.value)
					return "密码与确认密码不相同";
			},
			stuid:function(value, item){
				if(value==null || value==undefined || value=="" )
					return '学号不为能空';
				var username=$("#username").val();
				var isexist=false;
				var url = 'checkStuid';
				$.ajax({
					url : url,
					cache : false,
					async : false,			//同步校验
					type : "get",		    //数据发送方式
					dataType : "json", 		//接受数据格式
					data : {
						stuid : value,
						username: username
					},
					success : function(data, status) {
						if (status == "success") {
							if (data.code)
								isexist=true;						
						}
					}
				});
				if(isexist)
					return "该学号已存在！";
			},	
			
		});
		//提交用户信息
		form.on('submit(user-submit)', function(data){		
			//layer.msg(JSON.stringify(data.field));				
			var url =  'user/new?dialogType=${dialogType}';
			$.post(
				url,
				data.field,					
				function(data, status, xhr) {
					if (status == "success") {
						if (data.code == -10) {
							//session过期，跳转
							window.top.location.href = data.data;
						} else {
							layer.msg(data.msg);										
						}
					}
				},"json").fail(function() {
				return layer.msg("保存用户信息失败！");
			});
			//因为post已提交过了，就不需要再提交了
			return false;
		});
	});
	</script>
</body>
</html>
