<%@ page language="java" contentType="text/html; charset=gb2312"%>
<% request.setCharacterEncoding("gb2312"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="blue.css">
<title>信息提示－－网上书店</title>
<script language="javascript">
<!--
/*５秒后跳至指定页面*/
var leftSecond=3;
function toDes()
{
		document.location.href=document.getElementById("desPage").value;
}
function goDes()
{
	leftSecond--;
	document.getElementById("leftTime").innerText=leftSecond;
	if(leftSecond<=0)
		toDes();
}
function runTimer()
{
	document.getElementById("leftTime").innerText=leftSecond;
	setInterval("goDes();",1000); 
}
-->
</script>
</head>
<body onLoad="runTimer()">
<div align="center">
<table width="788"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><jsp:include page="head.jsp" flush="true"></jsp:include></td>
  </tr>
  <tr>
  	<td>
  		<div id="frameThin">
  		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td>
		    	<div id="showTip" align="center">
		    	<%
		    		String errorTip="";
					if(request.getParameter("tip").equals("add-user-fail"))
						errorTip="添加用户失败！";
					else if(request.getParameter("tip").equals("add-user-ok"))
						errorTip="添加用户成功！";
					else if(request.getParameter("tip").equals("del-user-fail"))
						errorTip="删除用户失败！";
					else if(request.getParameter("tip").equals("del-user-ok"))
						errorTip="删除用户成功！";
					if(request.getParameter("tip").equals("change-pwd-fail"))
						errorTip="修改密码失败！";
					else if(request.getParameter("tip").equals("change-pwd-ok"))
						errorTip="修改密码成功！";
					else if(request.getParameter("tip").equals("login-name"))
						errorTip="用户名错误！";
					else if(request.getParameter("tip").equals("webManager-login-ok"))
						errorTip="网站管理员登录成功！";
					else if(request.getParameter("tip").equals("bookReader-login-ok"))
						errorTip="读者登录成功！";
					else if(request.getParameter("tip").equals("login-ok"))
						errorTip="登录成功！";
					else if(request.getParameter("tip").equals("login-pwd"))
						errorTip="密码错误！";
					else if(request.getParameter("tip").equals("del-book-ok"))
						errorTip="删除图书成功！";
					else if(request.getParameter("tip").equals("del-book-fail"))
						errorTip="删除图书失败！";
					else if(request.getParameter("tip").equals("add-book-ok"))
						errorTip="添加图书成功！";
					else if(request.getParameter("tip").equals("add-book-fail"))
						errorTip="添加图书失败！";
					else if(request.getParameter("tip").equals("use-bag-fail"))
						errorTip="使用购物车前，请先登录！";
					else if(request.getParameter("tip").equals("change-book-ok"))
						errorTip="修改图书信息成功！";
					else if(request.getParameter("tip").equals("change-book-fail"))
						errorTip="修改图书信息失败！";
					else if(request.getParameter("tip").equals("change-user-ok"))
						errorTip="修改信息成功！";
					else if(request.getParameter("tip").equals("change-user-fail"))
						errorTip="修改信息失败！";
					else if(request.getParameter("tip").equals("user-config-ok"))
						errorTip="注册成功！";
					else if(request.getParameter("tip").equals("user-config-fail"))
						errorTip="注册失败！";
					else if(request.getParameter("tip").equals("check-all-ok"))
						errorTip="订单已录入！";
					else if(request.getParameter("tip").equals("add-topic-ok"))
						errorTip="发表新帖成功！";
					else if(request.getParameter("tip").equals("add-topic-fail"))
						errorTip="发表新帖失败！";
					else if(request.getParameter("tip").equals("add-follow-ok"))
						errorTip="发表回复成功！";
					else if(request.getParameter("tip").equals("add-follow-fail"))
						errorTip="发表回复失败！（发表回复之前，请先登录！）";

					out.print("<font color='#FF0000'>"+errorTip+"</font>");
		    	%>
		    	</div>
				<input id="desPage" name="desPage" type="hidden" value='<%=request.getParameter("desPage") %>'></td>
		  </tr>
		  <tr>
		    <td><div align="center">
			  	<div id="leftTime"></div>秒后<br>
		        <input type="button" name="bShowTime" id="bShowTime" value="返回" onClick="toDes()">
		    </div></td>
		  </tr>
		 </table>
		 </div>
		</td>
	</tr>
  <tr>
    <td><jsp:include page="bottom.jsp" flush="true" /></td>
  </tr>
</table>
</div>
</body>
</html>