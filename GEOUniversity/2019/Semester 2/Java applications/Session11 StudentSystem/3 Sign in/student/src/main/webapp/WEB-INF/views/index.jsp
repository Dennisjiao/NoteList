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
<title>学生成绩管理系统</title>
<%@include file="common.jsp"%>
<link rel="stylesheet" href="static/layuiadmin/style/admin.css"	media="all">
<link rel="stylesheet" href="static/css/animate.css"  type="text/css" media="all">
</head>
<body>
	<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
	<!--[if lt IE 9]>
	  <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
	  <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<div id="LAY_app">
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header">
				<!-- 头部区域 -->
				<ul class="layui-nav layui-layout-left">
					<li class="layui-nav-item layadmin-flexible" lay-unselect><a
						href="javascript:;" layadmin-event="flexible" title="侧边伸缩"> <i
							class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
					</a></li>
					<li class="layui-nav-item layui-hide-xs" lay-unselect><a
						href="index" target="_parent" title="首页"> <i
							class="layui-icon layui-icon-website"></i>&nbsp;首页
					</a></li>					
					<li class="layui-nav-item" lay-unselect><a href="javascript:;"
						layadmin-event="refresh" title="刷新"> <i
							class="layui-icon layui-icon-refresh-3"></i>&nbsp;刷新
					</a></li>
					<li class="layui-nav-item layui-hide-xs" lay-unselect><input
						type="text" placeholder="搜索..." autocomplete="off"
						class="layui-input layui-input-search" layadmin-event="serach"
						lay-action="template/search.html?keywords="></li>
				</ul>
				<ul class="layui-nav layui-layout-right"
					lay-filter="layadmin-layout-right">

					<li class="layui-nav-item" lay-unselect><a
						lay-href="user/message/MessageList" layadmin-event="message"
						lay-text="消息中心"> <i class="layui-icon layui-icon-notice"></i>

							<!-- 如果有新消息，则显示小圆点 --> <span class="layui-badge-dot"></span>
					</a></li>
					<li class="layui-nav-item layui-hide-xs" lay-unselect><a
						href="javascript:;" layadmin-event="theme"> <i
							class="layui-icon layui-icon-theme"></i>
					</a></li>

					<li class="layui-nav-item layui-hide-xs" lay-unselect><a
						href="javascript:;" layadmin-event="fullscreen"> <i
							class="layui-icon layui-icon-screen-full"></i>
					</a></li>
					<li class="layui-nav-item" lay-unselect style="margin-right:50px;">
						<a href="javascript:;"> 
			              ${user.username}             
						</a>
						<dl class="layui-nav-child">
							<dd style="text-align: center;">
								<a href="logout">退出</a>
							</dd>
						</dl>
					</li>

					<li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm"
						lay-unselect><a href="javascript:;" layadmin-event="more"><i
							class="layui-icon layui-icon-more-vertical"></i></a></li>
				</ul>
			</div>

			<!-- 侧边菜单 -->
			<div class="layui-side layui-side-menu">
				<div class="layui-side-scroll">
					<div class="layui-logo">
						<a href="index"><span>学生成绩管理系统</span></a>
					</div>

					<ul class="layui-nav layui-nav-tree" lay-shrink="all"
						id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">						
						<li data-name="user" class="layui-nav-item layui-nav-itemed">
							<a href="javascript:;" lay-tips="用户管理" lay-direction="2"><i
								class="layui-icon layui-icon-username"></i><cite>用户管理</cite>
						</a>							
						</li>
						<li data-name="course" class="layui-nav-item layui-nav-itemed">
							<a href="javascript:;" lay-tips="课程管理" lay-direction="2"><i
								class="layui-icon layui-icon-read"></i><cite>课程管理</cite>
						</a>							
						</li>
						<li data-name="coursechoosing" class="layui-nav-item layui-nav-itemed">
							<a href="javascript:;" lay-tips="选课管理" lay-direction="2"><i
								class="layui-icon layui-icon-star"></i><cite>选课管理</cite>
						</a>							
						</li>
						<li data-name="course" class="layui-nav-item layui-nav-itemed">
							<a href="javascript:;" lay-tips="成绩管理" lay-direction="2"><i
								class="layui-icon layui-icon-list"></i><cite>成绩管理</cite>
						</a>							
						</li>
					</ul>
				</div>
			</div>

			<!-- 页面标签 -->
			<div class="layadmin-pagetabs" id="LAY_app_tabs">
				<div class="layui-icon layadmin-tabs-control layui-icon-prev"
					layadmin-event="leftPage"></div>
				<div class="layui-icon layadmin-tabs-control layui-icon-next"
					layadmin-event="rightPage"></div>
				<div class="layui-icon layadmin-tabs-control layui-icon-down">
					<ul class="layui-nav layadmin-tabs-select"
						lay-filter="layadmin-pagetabs-nav">
						<li class="layui-nav-item" lay-unselect><a
							href="javascript:;"></a>
							<dl class="layui-nav-child layui-anim-fadein">
								<dd layadmin-event="closeThisTabs">
									<a href="javascript:;">关闭当前标签页</a>
								</dd>
								<dd layadmin-event="closeOtherTabs">
									<a href="javascript:;">关闭其它标签页</a>
								</dd>
								<dd layadmin-event="closeAllTabs">
									<a href="javascript:;">关闭全部标签页</a>
								</dd>
							</dl></li>
					</ul>
				</div>
				<div class="layui-tab" lay-unauto lay-allowClose="true"
					lay-filter="layadmin-layout-tabs">
					<ul class="layui-tab-title" id="LAY_app_tabsheader">
						<li lay-id="home" lay-attr="home" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
						
					</ul>
				</div>
			</div>


			<!-- 主体内容 -->
			<div class="layui-body" id="LAY_app_body">
				<div class="layadmin-tabsbody-item layui-show">
					<iframe src="home" frameborder="0"
						class="layadmin-iframe"></iframe>
				</div>
			</div>

			<!-- 辅助元素，一般用于移动设备下遮罩 -->
			<div class="layadmin-body-shade" layadmin-event="shade"></div>
		</div>
	</div>
	
	<script src="static/layuiadmin/layui/layui.js" type="text/javascript"></script>
	<script src="static/js/jquery-3.3.1.min.js"></script>	
	<script>
	  layui.config({
	    base: 'static/layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index' //主入口模块
	  }).use('index');
  </script>	
</body>
</html>