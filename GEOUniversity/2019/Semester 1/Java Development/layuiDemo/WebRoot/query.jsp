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

<TITLE>查询好汉</TITLE>
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
			<form class="layui-form" action="queryHero" method="post">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">好汉姓名</label>
						<div class="layui-input-inline" style="width: 140px;">
							<input type="text" name="name" placeholder="请输入好汉姓名"
								autocomplete="off" class="layui-input" value="${queryParam.name }">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">好汉年龄</label>
						<div class="layui-input-inline" style="width: 140px;">
							<input type="text" name="age" placeholder="请输入年龄" lay-verify="age" autocomplete="off" class="layui-input" value="${queryParam.age }">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">好汉性别</label>
						<div class="layui-input-inline" style="width: 220px;">
							<c:if test="${queryParam.sex=='男'}">
								<input type="radio" name="sex" value="" title="保密">
								<input type="radio" name="sex" value="男" title="男" checked>
								<input type="radio" name="sex" value="女" title="女" >
							</c:if>
							<c:if test="${queryParam.sex=='女'}">
								<input type="radio" name="sex" value="" title="保密">
								<input type="radio" name="sex" value="男" title="男" >
								<input type="radio" name="sex" value="女" title="女" checked>
							</c:if>
							<c:if test="${empty queryParam.sex}">
								<input type="radio" name="sex" value="" title="保密" checked>
								<input type="radio" name="sex" value="男" title="男" >
								<input type="radio" name="sex" value="女" title="女">
							</c:if>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">好汉绰号</label>
						<div class="layui-input-inline" style="width: 140px;">
							<input type="text" name="description" placeholder="请输入好汉绰号"
								autocomplete="off" class="layui-input" value="${queryParam.description }">
						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"></label>
					<div class="layui-input-inline">
						<button class="layui-btn" lay-submit lay-filter="formSubmit">查询</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
		<div class="layui-row mt20">
			<table class="layui-table">
				<thead>
					<tr>
						<th width="7%" style="text-align:center;">
							<div class="layui-form" >
				            	<input type="checkbox" title="全选"  lay-skin="primary" lay-filter="selectAll"/>
				        	</div>
				        </th>
						<th width="20%" style="text-align:center;">姓名</th>
						<th width="10%" style="text-align:center;">年龄</th>
						<th width="10%" style="text-align:center;">性别</th>
						<th width="15%" style="text-align:center;">生日</th>						
						<th width="20%" style="text-align:center;">绰号</th>
						<th style="text-align:center;">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${herolist }" var="hero">
					<tr>
						<td>
							<div class="layui-form" >
								<input type="checkbox" name="checkHero" lay-skin="primary" value="${hero.id }">
							</div>
						</td>
						<td>${hero.name }</td>
						<td>${hero.age }</td>
						<td>${hero.sex }</td>
						<td>${hero.birthday }</td>
						<td>${hero.description }</td>
						<td>
							<a href="updateHero?id=${hero.id }" class="layui-btn layui-btn-xs" >修改</a>
							<a href="deleteHero?id=${hero.id }" class="layui-btn layui-btn-danger layui-btn-xs"  onclick="return confirm('确定删除该记录？')">删除</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>			
		</div>
	</div>
</body>
<script type="text/javascript">
	window.notifymsg = "${notifymsg}";
</script>
<script src="layui/layui.js"></script>
<script>
	//注意：导航 依赖 element 模块，否则无法进行功能性操作
	layui.use([ 'element', 'form', 'laydate','table', 'layer' ], function() {
		var element = layui.element,
			form = layui.form,
			table=layui.table,
			laydate = layui.laydate,
			layer=layui.layer,
			$ = layui.$;
		// 绘制日期控件
		laydate.render({
			elem : '#birthday' //指定元素
		});
		//有错误信息输出它
		if (window.notifymsg != undefined && window.notifymsg != "") {
			layer.msg(window.notifymsg);
			window.notifymsg = "";
		}
		//处理表格的全选事件
		form.on('checkbox(selectAll)', function(data){
		  	  
		  	  if(data.elem.checked===true)
		  	  {
		  	  	$('input[name="checkHero"]').prop("checked",true);
		  	  }
		  	  else
		  	  {
		  	  	$('input[name="checkHero"]').prop("checked",false);
		  	  }	  		  	
		  	  console.log($('input[name="checkHero"]'));		  	  
		  	  form.render('checkbox');	
		  	  
		}); 
		//校验年龄字段的值是否合法
		form.verify({
			age:function(value, item){ //value：表单的值、item：表单的DOM对象
				if(value.length>0 && !new RegExp("^[0-9]{2,2}$").test(value)){
					return '年龄只能是2位数字';	
				}										
			}
		});	  
		
	});
</script>
</html>
