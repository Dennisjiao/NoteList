<%@ page language="java" contentType="text/html; charset=gb2312" import="java.util.*"%>

<%
	/*��������ѽ����ġ�session��*/
	Enumeration existSession=session.getAttributeNames();
	while(existSession.hasMoreElements())
	{
		session.removeAttribute(existSession.nextElement().toString());
	}
	response.sendRedirect("index-main.jsp");
%>