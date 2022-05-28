<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<TITLE>添加好汉</TITLE>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="layui/css/layui.css" media="all">
<link href="static/css/mycss.css" rel="stylesheet">
</head>

<body>
	<div class="layui-container">
		<%@ include file="header.jsp"%>
		<div class="layui-row mt20">
			<div class="layui-col-lg5">
				<form class="layui-form" action="addHero" method="post" >
					<div class="layui-form-item">
						<label class="layui-form-label">姓名</label>
						<div class="layui-input-block">
							<input type="text" name="name"  lay-verify="required"
								placeholder="请输入好汉姓名" autocomplete="off" class="layui-input" >							
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">年龄</label>
						<div class="layui-input-block">
							<input type="text" name="age" lay-verify="required|number"
								placeholder="请输入年龄" autocomplete="off" class="layui-input" >
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">性别</label>
						<div class="layui-input-block">
							<input type="radio" name="sex" value="男" title="男" checked>
							<input type="radio" name="sex" value="女" title="女" >
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">出生日期</label>
						<div class="layui-input-block">
							<input type="text" class="layui-input" name="birthday" autocomplete="off" lay-verify="required|date"
								 id="birthday">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">绰号</label>
						<div class="layui-input-block">
							<input type="text" name="description" lay-verify="required" placeholder="请输入好汉绰号" 
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label"></label>
						<div class="layui-input-block">
							<button class="layui-btn" lay-submit lay-filter="formSubmit">立即提交</button>
							<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</div>

		</div>
	</div>
</body>
<script type="text/javascript">
		window.notifymsg="${notifymsg}";					
</script>
<script src="layui/layui.js"></script>
<script>
	//注意：导航 依赖 element 模块，否则无法进行功能性操作
	layui.use([ 'element', 'form', 'laydate', 'layer' ], function() {
		var element = layui.element,
			form = layui.form,
			laydate = layui.laydate;
		// 绘制日期控件
		laydate.render({
			elem : '#birthday' //指定元素
		});
		//有错误信息输出它
		if(window.notifymsg!=undefined && window.notifymsg!=""){
			layer.msg(window.notifymsg);
			window.notifymsg="";
		}
	});
</script>
</html>
