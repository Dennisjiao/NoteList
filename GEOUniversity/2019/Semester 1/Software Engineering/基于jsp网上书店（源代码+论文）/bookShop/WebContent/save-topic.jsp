<%@ page language="java" contentType="text/html; charset=gb2312" import="java.sql.*,myPk.*" %>
<% request.setCharacterEncoding("gb2312"); %>

<%
	String action="";
	if(request.getParameter("action")!=null)
		action=request.getParameter("action");
	out.print(action);
	if(action.equals("add-topic"))
	{
		try
		{
			//�������
			int topicId=1;
			//ȷ�� topicId;
			Conn conMaxId=new Conn();
			Pageable rsMaxId=conMaxId.getRs("select * from topic order by topicId desc");
			if(rsMaxId.next())
			{
				rsMaxId.absolute(-1);
				topicId=Integer.parseInt(rsMaxId.getString("topicId"))+1;
			}
			//�� topic ���в�������
			Conn conTopic=new Conn();
			String sql="insert into topic(topicId,topicDate,topicContent,topicTitle,userName,followTotal,userId) values(";
			sql+=topicId+",";
			sql+="Date(),";
			sql+="'"+request.getParameter("topicContent")+"',";
			sql+="'"+request.getParameter("topicTitle")+"',";
			sql+="'"+session.getAttribute("userName").toString()+"',";
			sql+=0+",";
			sql+=session.getAttribute("userId")+")";
			conTopic.exeUpd(sql);
			conTopic.closeNoRs();
			response.sendRedirect("tip.jsp?tip=add-topic-ok&desPage=forum.jsp");
		}catch(Exception e) { response.sendRedirect("tip.jsp?tip=add-topic-fail&desPage=forum.jsp");  }
	}//if(action.equals("add-topiic"))
	else if(action.equals("add-follow"))
	{
		//��Ӹ���
		int topicId=Integer.parseInt(request.getParameter("topicId"));
		int followId=1;
			//ȷ�� topicId;
		try
		{
			Conn conMaxId=new Conn();
			Pageable rsMaxId=conMaxId.getRs("select * from follow order by followId desc");
			if(rsMaxId.next())
			{
				rsMaxId.absolute(-1);
				followId=Integer.parseInt(rsMaxId.getString("followId"))+1;
			}
			//�� follow ���в�������
			Conn conFollow=new Conn();
			String sql="insert into follow(followId,followDate,followContent,followTitle) values(";
			sql+=followId+",";
			sql+="Date(),";
			sql+="'"+request.getParameter("followContent")+"',";
			sql+="'"+request.getParameter("followTitle")+"')";
			conFollow.exeUpd(sql);
			//�� topic_follow ���в�������
			Conn conTF=new Conn();
			String sqlTF="insert into topic_follow(topicId,followId,followUserId) values(";
			sqlTF+=topicId+",";
			sqlTF+=followId+",";
			sqlTF+=Integer.parseInt(session.getAttribute("userId").toString())+")";
			conTF.exeUpd(sqlTF);
			response.sendRedirect("tip.jsp?tip=add-follow-ok&desPage=forum.jsp");
		}catch(Exception e) { response.sendRedirect("tip.jsp?tip=add-follow-fail&desPage=forum.jsp"); }
	}//else if(action.equals("add-follow"))
%>