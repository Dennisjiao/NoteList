<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
request.setCharacterEncoding("utf-8");//解决中文乱码
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title></title>

  </head>
  
   <body>
      <form action="index.jsp" method="post">
      		产品编号：<input name="product_id"/><br>
      		产品名称：<input name="product_name"/><br>
      		产品价格：<input name="price"/><br>
      		产品信息：<textarea rows="" cols="" name="info"></textarea><br/>
      		<input type="submit" value="添加">&nbsp;&nbsp;
      		<input type="reset" value="重置">
      </form>
  </body>
</html>
