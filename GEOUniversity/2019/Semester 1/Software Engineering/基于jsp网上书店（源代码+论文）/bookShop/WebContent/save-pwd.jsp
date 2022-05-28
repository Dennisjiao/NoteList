<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>
<jsp:useBean id="con" class="myPk.Conn" scope="page" />
<jsp:useBean id="toMd5" class="myPk.Md5" scope="page"/>
<%
	try
	{		
		con.exeUpd("update user set userPwd='"+toMd5.getMD5ofStr(request.getParameter("newPwd"))+"' where userId="+session.getAttribute("userId"));
		con.closeNoRs();
		response.sendRedirect("tip.jsp?tip=change-pwd-ok&desPage=admin-pwd.jsp");
	}catch(Exception e) { response.sendRedirect("tip.jsp?tip=change-pwd-fail&desPage=admin-pwd.jsp"); }
%>
