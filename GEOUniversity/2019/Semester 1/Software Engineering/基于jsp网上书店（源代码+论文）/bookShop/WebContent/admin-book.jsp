<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="blue.css">
<title>����ͼ�飭����̨����</title>
<script language="javascript">
<!--
/*ҳ����ʾ*/
function showPage()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//��ǰҳ��
	var pageCount=parseInt(document.getElementById("pageCount").value);	//�ܵ�ҳ��
	if(pageCount<1)
	{
		document.getElementById("toFirst").innerHTML="<img src='img/toFirst-2.gif' width='25' height='25'>";
		document.getElementById("toPri").innerHTML="<img src='img/toLeftStep-2.gif' width='25' height='25'>";
		document.getElementById("toNext").innerHTML="<img src='img/toRightStep-2.gif' width='25' height='25'>";
		document.getElementById("toLast").innerHTML="<img src='img/toLast-2.gif' width='25' height='25'>";
		document.getElementById("go").innerHTML="<img src='img/go-2.gif' width='25' height='25'>";
	}
	else 
	{
		if(curPage==1)
		{
			document.getElementById("toFirst").innerHTML="<img src='img/toFirst-2.gif' width='25' height='25'>";
			document.getElementById("toPri").innerHTML="<img src='img/toLeftStep-2.gif' width='25' height='25'>";
			if(pageCount==1)
			{
				document.getElementById("toNext").innerHTML="<img src='img/toRightStep-2.gif' width='25' height='25'>";
				document.getElementById("toLast").innerHTML="<img src='img/toLast-2.gif' width='25' height='25'>";
				document.getElementById("go").innerHTML="<img src='img/go-2.gif' width='25' height='25'>";
			}
		}//if(curPage==1)
		else if(curPage==pageCount)
		{
			document.getElementById("toNext").innerHTML="<img src='img/toRightStep-2.gif' width='25' height='25'>";
			document.getElementById("toLast").innerHTML="<img src='img/toLast-2.gif' width='25' height='25'>";
			if(pageCount==1)
			{
				document.getElementById("toFirst").innerHTML="<img src='img/toFirst-2.gif' width='25' height='25'>";
				document.getElementById("toPri").innerHTML="<img src='img/toLeftStep-2.gif' width='25' height='25'>";
				document.getElementById("go").innerHTML="<img src='img/go-2.gif' width='25' height='25'>";
			}
		}//else if(curPage==pageCount)
	}
}
/*������һҳ*/
function toFirst()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//��ǰҳ��
	var pageCount=parseInt(document.getElementById("pageCount").value);	//�ܵ�ҳ��
	if((pageCount>1)&&(curPage!=1))
		document.location.href="admin-book.jsp?&pgDes=1";
}
/*������һҳ*/
function toPri()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//��ǰҳ��
	var pageCount=parseInt(document.getElementById("pageCount").value);	//�ܵ�ҳ��
	var pageTem=curPage-1;
	if((pageCount>1)&&(curPage!=1))
		document.location.href="admin-book.jsp?&pgDes="+pageTem;
}
/*������һҳ*/
function toNext()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//��ǰҳ��
	var pageCount=parseInt(document.getElementById("pageCount").value);	//�ܵ�ҳ��
	pageTem=curPage+1;
	if((pageCount>1)&&(curPage!=pageCount))
	document.location.href="admin-book.jsp?&pgDes="+pageTem;
}
/*�������һҳ*/
function toLast()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//��ǰҳ��
	var pageCount=parseInt(document.getElementById("pageCount").value);	//�ܵ�ҳ��
	pageTem=pageCount;
	if((pageCount>1)&&(curPage!=pageCount))
		document.location.href="admin-book.jsp?&pgDes="+pageTem;
}
/*����ָ��ҳ*/
function goIndex()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//��ǰҳ��
	var pageCount=parseInt(document.getElementById("pageCount").value);	//�ܵ�ҳ��
	var toPage=parseInt(document.getElementById("goIndex").value);
	if((curPage!=toPage)&&(toPage>=1)&&(toPage<=pageCount))
		document.location.href="admin-book.jsp?&pgDes="+toPage;
}
-->
</script>
</head>
<body onLoad="showPage()">
<jsp:useBean id="con" class="myPk.Conn" scope="page" />
<div id="frameThin">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><jsp:include page="head.jsp" flush="true"></jsp:include></td>
  </tr>
  <tr>
    <td>
<table width="100%"  border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td colspan="10">&nbsp;</td>
  </tr>
  <tr>
    <td>����</td>
    <td>����</td>
    <td>������</td>
    <td>����ʱ��</td>
    <td>���</td>
    <td>ӡ��</td>
    <td>���</td>
    <td>�ۼ�</td>
    <td>���</td>
    <td>����</td>
  </tr>
<%
	int pgDes=0;
	int pgTotal=0;
	try
	{
		Pageable rs=con.getRs("select * from book");
  		rs.setPageSize(5);
		pgTotal=rs.getPageCount();
		if(request.getParameter("pgDes")!=null)
			pgDes=Integer.parseInt(request.getParameter("pgDes"));
		else if(pgTotal>0)
			pgDes=1;
  		rs.gotoPage(pgDes);
  		String bookId=null;
		for(int i=0;i<rs.getPageRowsCount();i++)
		{
			bookId=rs.getString("bookId");
%>
  <tr>
    <td><a href='book-information.jsp?id=<%=bookId %>'><%=rs.getString("bookTitle") %></a></td>
    <td><a href='book-information.jsp?id=<%=bookId %>'><%=rs.getString("bookAuthor") %></a></td>
    <td><a href='book-information.jsp?id=<%=bookId %>'><%=rs.getString("bookPublish") %></a></td>
    <td><a href='book-information.jsp?id=<%=bookId %>'><%=rs.getString("bookDate").substring(0,10) %></a></td>
    <td><a href='book-information.jsp?id=<%=bookId %>'><%=rs.getString("bookBC") %></a></td>
    <td><a href='book-information.jsp?id=<%=bookId %>'><%=rs.getString("bookYC") %></a></td>
    <td><a href='book-information.jsp?id=<%=bookId %>'><%=rs.getString("bookPrice") %></a></td>
    <td><a href='book-information.jsp?id=<%=bookId %>'><%=rs.getString("bookValue") %></a></td>
    <td><a href='book-information.jsp?id=<%=bookId %>'><%=rs.getString("bookStorage") %></a></td>
    <td>
		<a href='admin-book-add.jsp?action=change&bookId=<%=bookId %>'><font color="#FF0000">�޸�</font></a>&nbsp;&nbsp;
		<a href='save-admin-book.jsp?action=del&bookId=<%=bookId %>'><font color="#FF0000">ɾ��</font></a></td>
  </tr>
  <%
 				rs.next();
		}
		con.closeRs();
	}catch(Exception e) { }
%>
  <tr>
    <td colspan="10"><div align="right">
      <table  border="0" cellpadding="0" cellspacing="3">
        <tr>
          <td width="25" height="25">
            <button id="toFirst" name="toFirst" onClick="toFirst()"><img src="img/toFirst.gif"></button></td>
          <td width="25" height="25">
            <button id="toPri" name="toPri" onClick="toPri()"><img src="img/toLeftStep.gif" width="25" height="25"></button></td>
          <td height="25">��<%=pgDes %>ҳ��</td>
          <td height="25">��<%=pgTotal %>ҳ</td>
          <td width="25" height="25">
            <button id="toNext" name="toNext" onClick="toNext()"><img src="img/toRightStep.gif" width="25" height="25"></button></td>
          <td width="25" height="25">
            <button id="toLast" name="toLast" onClick="toLast()"><img src="img/toLast.gif" width="25" height="25"></button></td>
          <td height="25">
            <input id="curPage" name="curPage" type="hidden" value="<%=pgDes %>" >
            <input id="pageCount" name="pageCount" type="hidden" value="<%=pgTotal %>" >
&nbsp;</td>
          <td height="25">
            <input name="goIndex" type="text" id="goIndex" size="5" value="<%=pgDes %>"></td>
          <td width="25" height="25">
            <button id="go" name="go" onClick="goIndex()"><img src="img/go.gif" width="25" height="25"></button></td>
        </tr>
      </table>
    </div></td>
  </tr>
  <tr>
    <td colspan="10"><div align="center"><a href="admin-book-add.jsp?action=add">���</a></div></td>
  </tr>
</table>
	</td>
  </tr>
  <tr>
    <td><jsp:include page="bottom.jsp" flush="true" /></td>
  </tr>
</table>
</div>
</body>
</html>