<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>论坛－－网上书店</title>
<link href="blue.css" rel="stylesheet" type="text/css">
<script language="javascript">
<!--
/*页码显示*/
function showPage()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//当前页码
	var pageCount=parseInt(document.getElementById("pageCount").value);	//总的页数
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
/*跳到第一页*/
function toFirst()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//当前页码
	var pageCount=parseInt(document.getElementById("pageCount").value);	//总的页数
	if((pageCount>1)&&(curPage!=1))
		document.location.href="admin-user.jsp?&pgDes=1";
}
/*跳到上一页*/
function toPri()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//当前页码
	var pageCount=parseInt(document.getElementById("pageCount").value);	//总的页数
	var pageTem=curPage-1;
	if((pageCount>1)&&(curPage!=1))
		document.location.href="admin-user.jsp?&pgDes="+pageTem;
}
/*跳到下一页*/
function toNext()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//当前页码
	var pageCount=parseInt(document.getElementById("pageCount").value);	//总的页数
	pageTem=curPage+1;
	if((pageCount>1)&&(curPage!=pageCount))
	document.location.href="admin-user.jsp?&pgDes="+pageTem;
}
/*跳到最后一页*/
function toLast()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//当前页码
	var pageCount=parseInt(document.getElementById("pageCount").value);	//总的页数
	pageTem=pageCount;
	if((pageCount>1)&&(curPage!=pageCount))
		document.location.href="admin-user.jsp?&pgDes="+pageTem;
}
/*跳到指定页*/
function goIndex()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//当前页码
	var pageCount=parseInt(document.getElementById("pageCount").value);	//总的页数
	var toPage=parseInt(document.getElementById("goIndex").value);
	if((curPage!=toPage)&&(toPage>=1)&&(toPage<=pageCount))
		document.location.href="admin-book.jsp?&pgDes="+toPage;
}

-->
</script>
</head>

<body onLoad="showPage()">
<jsp:useBean id="con" class="myPk.Conn" scope="page" />
<div align="center">
  <table width="788"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><jsp:include page="head.jsp" flush="true"></jsp:include></td>
    </tr>
    <tr>
      <td>
	  <div id="frameThin">
	  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr bgcolor="#CCCCCC">
          <td width="40"><div align="center"><strong>时间</strong></div></td>
          <td><div align="center"><strong>标题</strong></div></td>
          <td width="40"><div align="center"><strong>作者</strong></div></td>
          <td width="40"><div align="center"><strong>回复</strong></div></td>
        </tr>
		<%
			int pgDes=1;
			int pgTotal=1;
			String topicId="";
			if(request.getParameter("pgDes")!=null)
				pgDes=Integer.parseInt(request.getParameter("pgDes"));
			try
			{
				Pageable rs=con.getRs("select * from topic order by topicDate desc");
				rs.setPageSize(10);
				rs.gotoPage(pgDes);
				pgTotal=rs.getPageCount();
				for(int i=0;i<rs.getPageRowsCount();i++)
				{
					topicId=rs.getString("topicId");
		%>
        <tr>
          <td align="left"><a href='forum-topic.jsp?topicId=<%=topicId %>'><%=rs.getString("topicDate").substring(0,10) %></a></td>
          <td align="left"><a href='forum-topic.jsp?topicId=<%=topicId %>'><%=rs.getString("topicTitle") %></a></td>
          <td align="center"><a href='forum-topic.jsp?topicId=<%=topicId %>'><%=rs.getString("userName") %></a></td>
          <td align="right"><a href='forum-topic.jsp?topicId=<%=topicId %>'><%=rs.getString("followTotal") %></a></td>
        </tr>
          <%
          			rs.next();
				}//for(int i=0;i<rs.getPageRowsCount();i++)
			}catch(Exception e) { out.print(e.toString()); }
          %>
        <tr>
          <td colspan="4" align="left"><div align="right">
            <table  border="0" cellpadding="0" cellspacing="3">
              <tr>
                <td width="25" height="25">
                  <button id="toFirst" name="toFirst" onClick="toFirst()"><img src="img/toFirst.gif"></button></td>
                <td width="25" height="25">
                  <button id="toPri" name="toPri" onClick="toPri()"><img src="img/toLeftStep.gif" width="25" height="25"></button></td>
                <td height="25">第<%=pgDes %>页，</td>
                <td height="25">共<%=pgTotal %>页</td>
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
      </table>
	  </div>
	  </td>
    </tr>
    <tr>
      <td><jsp:include page="bottom.jsp" flush="true"></jsp:include></td>
    </tr>
  </table>
</div>
</body>
</html>
