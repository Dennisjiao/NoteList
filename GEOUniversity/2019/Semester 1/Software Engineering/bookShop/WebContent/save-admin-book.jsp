<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.io.*,java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>

<jsp:useBean id="con" class="myPk.Conn" scope="page" />
<%
	String action =request.getParameter("action");
	if(action.equals("del"))
	{
		try
		{
			con.exeUpd("delete from book where bookId="+Integer.parseInt(request.getParameter("bookId")));
			response.sendRedirect("tip.jsp?tip=del-book-ok&desPage=admin-book.jsp");
		}catch(Exception e) { response.sendRedirect("tip.jsp?tip=del-book-fail&desPage=admin-book.jsp"); }
	}//if(action.equals("del"))
	else if(action.equals("add"))
	{
		String sql="";
		try
		{
			sql+="insert into book(";
			sql+="bookComment,bookAuthorInformation,bookSummary,bookPhoto,bookAuthor,bookDate,bookBC,";
			sql+="bookWords,bookPrice,bookPublish,bookIsbn,bookYC,bookSheet,bookValue,bookTitle,bookStorage,bookCommend,bookSort";
			sql+=") values(";
			sql+="'"+request.getParameter("bookComment")+"',";
			sql+="'"+request.getParameter("bookAuthorInformation")+"',";
			sql+="'"+request.getParameter("bookSummary")+"',";
			sql+="'"+session.getAttribute("bookPhoto").toString()+"',";
			sql+="'"+request.getParameter("bookAuthor")+"',";
			sql+="'"+request.getParameter("bookDate")+"',";
			sql+="'"+request.getParameter("bookBC")+"',";
			sql+="'"+request.getParameter("bookWords")+"',";
			sql+="'"+request.getParameter("bookPrice")+"',";
			sql+="'"+request.getParameter("bookPublish")+"',";
			sql+="'"+request.getParameter("bookIsbn")+"',";
			sql+="'"+request.getParameter("bookYC")+"',";
			sql+="'"+request.getParameter("bookSheet")+"',";
			sql+="'"+request.getParameter("bookValue")+"',";
			sql+="'"+request.getParameter("bookTitle")+"',";
			sql+="'"+request.getParameter("bookStorage")+"',";
			sql+="'"+request.getParameter("bookCommend")+"',";
			sql+="'"+request.getParameter("bookSort")+"'";
			sql+=")";
			con.exeUpd(sql);
			con.closeNoRs();
			if(session.getAttribute("bookPhoto")!=null)
			{
				/*重命名图片文件，使其形如　bookId.jpg */		
				String sCurPath=request.getRealPath("/");		//当前路径
				String newPhoto="";				//图片的新文件名，形如 bookId.jpg
				String bookId="";
				for(int i=0;i<6;i++)
				{
					File fCurFile=new File(sCurPath);
					sCurPath=fCurFile.getParent();
				}
				sCurPath+="\\bookShop\\WebContent\\img\\book";		//图书封面图片的路径
				String sTem=session.getAttribute("bookPhoto").toString();
				String oldFileName=sCurPath+"\\"+sTem;			//图片原路径和文件名
				Pageable rs=con.getRs("select * from book where bookPhoto='"+sTem+"'");
				if(rs.next())
					bookId+=rs.getString("bookId");
				newPhoto+=bookId;
				con.closeRs();
				newPhoto+=sTem.substring(sTem.lastIndexOf("."));
				String newFileName=sCurPath+"\\"+newPhoto;		//图片新路径和文件名
				File fOld=new File(oldFileName);
				File fNew=new File(newFileName);
				fOld.renameTo(fNew);
				/*修改数据库中该书的 bookPhoto 值*/
				con.exeUpd("update book set bookPhoto='"+newPhoto+"' where bookId="+Integer.parseInt(bookId));
				con.closeNoRs();
			}//if(session.getAttribute("bookPhoto")!=null)
			response.sendRedirect("tip.jsp?tip=add-book-ok&desPage=admin-book.jsp");
		}catch(Exception e) { response.sendRedirect("tip.jsp?tip=add-book-fail&desPage=admin-book.jsp");  }
	}//else if(action.equals("add"))
	else if(action.equals("change"))
	{
		//修改图书信息
		String sql="";
		try
		{
			sql+="update book ";
			sql+="set bookComment='"+request.getParameter("bookComment")+"',";
			sql+="bookAuthorInformation='"+request.getParameter("bookAuthorInformation")+"',";
			sql+="bookSummary='"+request.getParameter("bookSummary")+"',";
			sql+="bookAuthor='"+request.getParameter("bookAuthor")+"',";
			sql+="bookBC='"+request.getParameter("bookBC")+"',";
			sql+="bookWords='"+request.getParameter("bookWords")+"',";
			sql+="bookPrice='"+request.getParameter("bookPrice")+"',";
			sql+="bookPublish='"+request.getParameter("bookPublish")+"',";
			sql+="bookIsbn='"+request.getParameter("bookIsbn")+"',";
			sql+="bookYC='"+request.getParameter("bookYC")+"',";
			sql+="bookSheet='"+request.getParameter("bookSheet")+"',";
			sql+="bookValue='"+request.getParameter("bookValue")+"',";
			sql+="bookTitle='"+request.getParameter("bookTitle")+"',";
			sql+="bookDate='"+request.getParameter("bookDate")+"',";
			sql+="bookStorage='"+request.getParameter("bookStorage")+"',";
			sql+="bookCommend='"+request.getParameter("bookCommend")+"',";
			if(session.getAttribute("bookPhoto")!=null)
				sql+="bookPhoto='"+session.getAttribute("bookPhoto").toString()+"',";
			sql+="bookSort='"+request.getParameter("bookSort")+"'";
			sql+=" where bookId="+Integer.parseInt(request.getParameter("bookId"));
			out.print(sql+"<br>");
			con.exeUpd(sql);
			con.closeNoRs();
			if(session.getAttribute("bookPhoto")!=null)
			{
				/*重命名图片文件，使其形如　bookId.jpg */		
				String sCurPath=request.getRealPath("/");		//当前路径
				String newPhoto="";				//图片的新文件名，形如 bookId.jpg
				String bookId="";
				for(int i=0;i<6;i++)
				{
					File fCurFile=new File(sCurPath);
					sCurPath=fCurFile.getParent();
				}
				sCurPath+="\\bookShop\\WebContent\\img\\book";		//图书封面图片的路径
				String sTem=session.getAttribute("bookPhoto").toString();
				String oldFileName=sCurPath+"\\"+sTem;			//图片原路径和文件名
				Pageable rs=con.getRs("select * from book where bookPhoto='"+sTem+"'");
				if(rs.next())
					bookId+=rs.getString("bookId");
				newPhoto+=bookId;
				con.closeRs();
				newPhoto+=sTem.substring(sTem.lastIndexOf("."));
				String newFileName=sCurPath+"\\"+newPhoto;		//图片新路径和文件名
				File fOld=new File(oldFileName);
				File fNew=new File(newFileName);
				fOld.renameTo(fNew);
				/*修改数据库中该书的 bookPhoto 值*/
				con.exeUpd("update book set bookPhoto='"+newPhoto+"' where bookId="+Integer.parseInt(bookId));
				con.closeNoRs();
				session.removeAttribute("bookPhoto");
			}//if(session.getAttribute("bookPhoto")!=null)
			response.sendRedirect("tip.jsp?tip=change-book-ok&desPage=admin-book.jsp");
		}catch(Exception e) { out.print(e.toString()); /*  response.sendRedirect("tip.jsp?tip=change-book-fail&desPage=admin-book.jsp"); */ }
	}
%>