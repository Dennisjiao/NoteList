<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>
<jsp:useBean id="con" class="myPk.Conn" scope="page" />
<jsp:useBean id="toMd5" class="myPk.Md5" scope="page"/>
<%! 
//×ª»»×Ö·û¼¯
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
	String sAction=request.getParameter("action");
	if(request.getParameter("action").equals("del"))
	{
		try
		{
			con.exeUpd("delete from user where userId="+Integer.parseInt(request.getParameter("userId")));
			con.closeNoRs();
			response.sendRedirect("tip.jsp?tip=del-user-ok&desPage=admin-user.jsp");
			
		}catch(Exception e) { response.sendRedirect("tip.jsp?tip=del-user-fail&desPage=admin-user.jsp");  }
	}
	else if(sAction.equals("add"))
	{
		String sql="";
		try
		{
			sql+="insert into user(userName,userPwd,userFigure,userSex,userAge,userBirthday,userAddress,userHome,userNumber) values(";
			sql+="'"+toChi(request.getParameter("userName"))+"',";
			sql+="'"+toMd5.getMD5ofStr(toChi(request.getParameter("userName")))+"',";
			sql+="'"+toChi(request.getParameter("userFigure"))+"',";
			sql+="'"+toChi(request.getParameter("userSex"))+"',";
			sql+="'"+Integer.parseInt(toChi(request.getParameter("userAge")))+"',";
			sql+="'"+toChi(request.getParameter("userBirthday"))+"',";
			sql+="'"+toChi(request.getParameter("userAddress"))+"',";
			sql+="'"+toChi(request.getParameter("userHome"))+"',";
			sql+="'"+toChi(request.getParameter("userNumber"))+"')";
			con.exeUpd(sql);
			con.closeNoRs();
			response.sendRedirect("tip.jsp?tip=add-user-ok&desPage=admin-user.jsp");
		}catch(Exception e) {  response.sendRedirect("tip.jsp?tip=add-user-fail&desPage=admin-user.jsp");   }
	}
%>