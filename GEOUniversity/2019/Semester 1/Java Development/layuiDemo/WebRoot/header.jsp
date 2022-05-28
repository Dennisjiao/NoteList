<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="uri" value="<%=request.getRequestURI() %>"/>

<div class="layui-row">
   <div class="layui-col-md12 title_center"><h2>梁山好汉管理系统</h2></div>	      
</div>
<div class="layui-row" style="margin-top:20px;">
	<ul class="layui-nav" lay-filter="">
		<c:if test="${fn:endsWith(uri,'index.jsp') || fn:endsWith(uri,'/layuiDemo/')}">		
		 <li class="layui-nav-item layui-this"><a href="#">首页</a></li>
		 <li class="layui-nav-item"><a href="new.jsp">添加好汉</a></li>		 
		 <li class="layui-nav-item"><a href="query.jsp">管理好汉</a></li>
		</c:if>
		<c:if test="${fn:endsWith(uri,'new.jsp')}">
		 <li class="layui-nav-item "><a href="#">首页</a></li>
		 <li class="layui-nav-item layui-this"><a href="new.jsp">添加好汉</a></li>		 
		 <li class="layui-nav-item"><a href="query.jsp">查询好汉</a></li>
		</c:if>		
		<c:if test="${fn:endsWith(uri,'query.jsp')}">
		 <li class="layui-nav-item "><a href="#">首页</a></li>
		 <li class="layui-nav-item "><a href="new.jsp">添加好汉</a></li>		 
		 <li class="layui-nav-item layui-this"><a href="query.jsp">管理好汉</a></li>
		</c:if>		
	</ul>	
</div>
	