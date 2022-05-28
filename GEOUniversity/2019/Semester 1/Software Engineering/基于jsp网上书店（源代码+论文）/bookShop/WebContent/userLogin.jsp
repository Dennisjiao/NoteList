<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.util.*,java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("GB2312"); %>

<%! 
//转换字符集
public String toChi(String str)
{
	if (str==null)
	{
		return "";
	}
	try
	{
		String temp_p=str;
		byte[] temp_t=temp_p.getBytes("ISO8859-1");
		String temp=new String(temp_t);
		return temp;
	}
	catch(Exception e) { }
	return str;
}
%>
<%
	/*清除所有已建立的　session　*/
	Enumeration existSession=session.getAttributeNames();
	while(existSession.hasMoreElements())
	{
		session.removeAttribute(existSession.nextElement().toString());
	}
%>
<jsp:useBean id="con"	 class="myPk.Conn" scope="page" />
<jsp:useBean id="toMd5" class="myPk.Md5" scope="page"/>
<%
	try
	{
		String userId=null;
		String userPwd=null;
		String userFigure=null;
		Pageable rs=con.getRs("select * from user where userName='"+toChi(request.getParameter("headUserName"))+"'");
		if(rs.next())
		{
			userId=rs.getString("userId");
			userPwd=rs.getString("userPwd");
			userFigure=rs.getString("userFigure");
		}
		if(rs.getRowsCount()<1)
		{
			//用户名错误;
			response.sendRedirect("tip.jsp?tip=login-name&desPage=index-main.jsp");
		}
		else if(toMd5.getMD5ofStr(toChi(request.getParameter("headUserPwd"))).equals(userPwd))
		{
			//用户名、密码均正确
			session.setAttribute("userId",userId);
			session.setAttribute("userName",toChi(request.getParameter("headUserName")));
			session.setAttribute("userFigure",userFigure);
			if(userFigure.equals("总管"))
				response.sendRedirect("tip.jsp?tip=webManager-login-ok&desPage=admin-book.jsp");
			else if(userFigure.equals("读者"))
				response.sendRedirect("tip.jsp?tip=bookReader-login-ok&desPage=user.jsp");
			else
				response.sendRedirect("tip.jsp?tip=login-ok&desPage=index-main.jsp");
		}
		else
		{
			//用户名正确，但密码错误;
			response.sendRedirect("tip.jsp?tip=login-pwd&desPage=index-main.jsp");
		}
	}catch(Exception e) { }
%>	
	