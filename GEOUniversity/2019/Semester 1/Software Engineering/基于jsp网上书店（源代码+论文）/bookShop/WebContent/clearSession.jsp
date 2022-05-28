<%@ page language="java" contentType="text/html; charset=gb2312" import="java.util.*"%>

<%
	/*清除所有已建立的　session　*/
	Enumeration existSession=session.getAttributeNames();
	while(existSession.hasMoreElements())
	{
		session.removeAttribute(existSession.nextElement().toString());
	}
	response.sendRedirect("index-main.jsp");
%>