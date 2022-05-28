<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>

<jsp:useBean id="con" class="myPk.Conn" scope="page" />
<jsp:useBean id="toMd5" class="myPk.Md5" scope="page" />
<%
	String action="";
	if(request.getParameter("action")!=null)
		action=request.getParameter("action");
	if(action.equals("change"))
	{
		String sql="";
		try
		{
			sql+="update user set ";
			sql+="userName='"+request.getParameter("userName")+"',";
			sql+="userSex='"+request.getParameter("userSex")+"',";
			sql+="userAge="+Integer.parseInt(request.getParameter("userAge"))+",";
			sql+="userBirthday="+request.getParameter("userBirthday")+",";
			sql+="userAddress='"+request.getParameter("userAddress")+"',";
			sql+="userHome='"+request.getParameter("userHome")+"',";
			sql+="userNumber='"+request.getParameter("userNumber")+"'";
			sql+=" where userId="+session.getAttribute("userId");
			con.exeUpd(sql);
			con.closeNoRs();
			response.sendRedirect("tip.jsp?tip=change-user-ok&desPage=user.jsp");
		}catch(Exception e) { out.print(e.toString()); /* response.sendRedirect("tip.jsp?tip=change-user-fail&desPage=user.jsp"); */  }
	}//if(action.equals("change"))
	else if(action.equals("add"))
	{
		String sql="";
		try
		{
			Conn conMax=new Conn();
			Pageable rsMax=conMax.getRs("select * from user order by userId desc");
			if(rsMax.next())
				rsMax.absolute(-1);
			int maxId=Integer.parseInt(rsMax.getString("userId"))+1;
			conMax.closeRs();
			sql+="insert into user(userId,userName,userPwd,userFigure,userSex,userAge,userBirthday,userAddress,userHome,userNumber) values(";
			sql+=maxId+",";
			sql+="'"+request.getParameter("userName")+"',";
			sql+="'"+toMd5.getMD5ofStr(request.getParameter("userPwd"))+"',";
			sql+="'¶ÁÕß',";
			sql+="'"+request.getParameter("userSex")+"',";
			sql+="'"+Integer.parseInt(request.getParameter("userAge"))+"',";
			sql+="'"+request.getParameter("userBirthday")+"',";
			sql+="'"+request.getParameter("userAddress")+"',";
			sql+="'"+request.getParameter("userHome")+"',";
			sql+="'"+request.getParameter("userNumber")+"')";
			con.exeUpd(sql);
			con.closeNoRs();
			session.setAttribute("userId",maxId);
			session.setAttribute("userName",request.getParameter("userName"));
			session.setAttribute("userFigure","¶ÁÕß");
			response.sendRedirect("tip.jsp?tip=user-config-ok&desPage=user.jsp");
		}catch(Exception e) {  response.sendRedirect("tip.jsp?tip=user-config-fail&desPage=user.jsp");   }
	}//else if(action.equals("add"))
%>	