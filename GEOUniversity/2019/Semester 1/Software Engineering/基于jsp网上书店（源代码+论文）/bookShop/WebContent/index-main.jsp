<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*" %>
<% request.setCharacterEncoding("gb2312"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="blue.css">
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
	String sort=null;
	String pageTitle="首页";		//网页标题
	String bookHot="";		//栏目的标题
	String sql="select * from book";
	Pageable rs=null;
	Pageable rsNew=null;
	Pageable rsHot=null;
	Pageable rsCam=null;
	if(request.getParameter("sort")!=null)
	{
		/*Javascript脚本中页面跳转时传递的参数，
		　以及HTML中链接（<a href="***.jsp?p1=z1">跳转</a>）时传递的参数，
		　即使在发送端用了　request.setCharacterEncoding("GB2312");
		　接收时，仍然要用自定义的函数转换字符集，否则参数中的中文会显示为乱码。
		*/
		sort=toChi(request.getParameter("sort"));
		pageTitle="["+sort+"]";
		bookHot=pageTitle;
		sql=sql+" where bookSort='"+sort+"'";
	}
%>
<title><%=pageTitle %>－－网上书店</title>
</head>
<body>
<div align="center">
<table width="788" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><jsp:include page="head.jsp" flush="true"></jsp:include></td>
  </tr>
  <tr>
    <td><table width="100%"  border="0" cellspacing="5" cellpadding="0">
      <tr>
        <td valign="top">
			<div id="frameThin">
			  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td>
						<div id="titleBg">
							<div id="titleText"><%=bookHot %>推荐</div><br>
						</div>
					</td>
                  </tr>
                  <tr>
                    <td align="left">
				<%
				Conn con=null;
                try
                {
                	String comSql="";
                	if(request.getParameter("sort")!=null)
                		comSql+=sql+" and bookCommend='inSort'";
                	else
                		comSql+=sql+" where bookCommend='inAll'";
					comSql+=" order by bookSold desc";
					con=new Conn();
                	Pageable rsCom=con.getRs(comSql);
                	rsCom.setPageSize(10);
                	rsCom.gotoPage(1);
                	for(int i=0;i<rsCom.getPageRowsCount();i++)
                	{
                		if(sort==null)
                		{
                		%>
                    <a href='index-main.jsp?sort=<%=rsCom.getString("bookSort") %>'>[<%=rsCom.getString("bookSort") %>]</a>
                    <%
                		}
                		%>
                    <a href='book-information.jsp?id=<%=rsCom.getString("bookId") %>'><%=rsCom.getString("bookTitle") %>&nbsp;&nbsp;<%=rsCom.getString("bookAuthor") %>&nbsp;&nbsp;<%=rsCom.getString("bookDate").substring(0,10) %></a><br>
                    <%
                		rsCom.next();
                	}
                	con.closeRs();
                }catch(Exception e) { out.print(e.toString()); }
                %>					</td>
                  </tr>
              </table>
		  </div>		</td>
        <td valign="top">
			<div id="frameThin">
			  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td>
				  	<div id="titleBg">
						<div id="titleText"><%=bookHot %>新书</div>
						<br>
					</div>
				  </td>
                </tr>
                <tr>
                  <td align="left">
				<%
                try
                {
                	String newSql=sql+" order by bookDate desc";
                	con=new Conn();
                	rsNew=con.getRs(newSql);
                	rsNew.setPageSize(10);
                	rsNew.gotoPage(1);
                	for(int i=0;i<rsNew.getPageRowsCount();i++)
                	{
                		if(sort==null)
                		{
                		%>
                    <a href='index-main.jsp?sort=<%=rsNew.getString("bookSort") %>'>[<%=rsNew.getString("bookSort") %>]</a>
                    <%
                		}
                		%>
                    <a href='book-information.jsp?id=<%=rsNew.getString("bookId") %>'><%=rsNew.getString("bookTitle") %>&nbsp;&nbsp;<%=rsNew.getString("bookAuthor") %>&nbsp;&nbsp;<%=rsNew.getString("bookDate").substring(0,10) %></a><br>
                    <%
                		rsNew.next();
                	}
                }catch(Exception e) { }
                %>				</td>
                </tr>
              </table>
		  </div>		</td>
      </tr>
    </table>
	</td>
  </tr>
  <tr>
    <td align="left"><table width="100%"  border="0" cellspacing="5" cellpadding="0">
      <tr>
        <td valign="top">
			<div id="frameThin">
			  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td>
				  	<div id="titleBg">
						<div id="titleText"><%=bookHot %>畅销榜</div><br>
					</div>
				  </td>
                </tr>
                <tr>
                  <td align="left"><%
                try
                {
                	String hotSql=sql+" order by bookSold desc";
                	con=new Conn();
                	rsHot=con.getRs(hotSql);
                	rsHot.setPageSize(10);
                	rsHot.gotoPage(1);
                	for(int i=0;i<rsHot.getPageRowsCount();i++)
                	{
                		if(sort==null)
                		{
                		%>
                    <a href='index-main.jsp?sort=<%=rsHot.getString("bookSort") %>'>[<%=rsHot.getString("bookSort") %>]</a>
                    <%
                		}
                		%>
                    <a href='book-information.jsp?id=<%=rsHot.getString("bookId") %>'><%=rsHot.getString("bookTitle") %>&nbsp;&nbsp;<%=rsHot.getString("bookAuthor") %>&nbsp;&nbsp;<%=rsHot.getString("bookDate").substring(0,10) %></a><br>
                    <%
                		rsHot.next();
                	}
                	con.closeRs();
                }catch(Exception e) { }
                %></td>
                </tr>
              </table>
		  </div>		</td>
        <td align="left" valign="top">
			<div id="frameThin">
			  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td>
				  	<div id="titleBg">
						<div id="titleText">论坛</div>
						<br>
					</div>
				  </td>
                </tr>
                <tr>
                  <td align="left">
                  <%
                  	try
                  	{
                  		Conn conForum=new Conn();
                  		Pageable rsForum=conForum.getRs("select * from topic order by topicDate desc");
                  		rsForum.setPageSize(10);
                  		rsForum.gotoPage(1);
                  		for(int i=0;i<rsForum.getPageRowsCount();i++)
                  		{
                  			%>
                  			<a href='forum-topic.jsp?topicId=<%=rsForum.getString("topicId") %>'><%=rsForum.getString("topicTitle") %>&nbsp;&nbsp;<%=rsForum.getString("userName") %>&nbsp;&nbsp;<%=rsForum.getString("topicDate").substring(0,10) %></a><br>
                  			<%
                  			rsForum.next();
                  		}
	                 }catch(Exception e) { }
                  %>
					</td>
                </tr>
              </table>
		  </div>		</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><jsp:include page="bottom.jsp" flush="true" /></td>
  </tr>
</table>
</div>
</body>
</html>