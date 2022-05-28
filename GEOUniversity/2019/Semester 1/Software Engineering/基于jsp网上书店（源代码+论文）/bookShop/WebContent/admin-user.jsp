<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="blue.css">
<title>管理用户－－后台管理</title>
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

/*添加用户*/
function addUser()
{
	var desUrl="";
	desUrl+="save-admin-user.jsp?action=add";
	desUrl+="&userName="+document.getElementById("userName").value;
	desUrl+="&userFigure="+document.getElementById("userFigure").value;
	desUrl+="&userSex="+document.getElementById("userSex").value;
	desUrl+="&userAge="+document.getElementById("userAge").value;
	desUrl+="&userBirthday="+document.getElementById("userBirthday").value;
	desUrl+="&userAddress="+document.getElementById("userAddress").value;
	desUrl+="&userHome="+document.getElementById("userHome").value;
	desUrl+="&userNumber="+document.getElementById("userNumber").value;
	document.location.href=desUrl;
}
-->
</script>
</head>
<body onload="javascript:showPage()">
<jsp:useBean id="con" class="myPk.Conn" scope="page" />
<%
	int pgDes=0;
	int pgTotal=0;
	Pageable rs=null;
	try
	{
	rs=con.getRs("select * from user");
	rs.setPageSize(1);
	pgTotal=rs.getPageCount();
	if(request.getParameter("pgDes")!=null)
		pgDes=Integer.parseInt(request.getParameter("pgDes"));
	else if(pgTotal>0)
		pgDes=1;
	rs.gotoPage(pgDes);
	}catch(Exception e) { }
%>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><jsp:include page="head.jsp" flush="true" /></td>
  </tr>
  <tr>
    <td><table width="100%"  border="0" cellspacing="5" cellpadding="0">
      <tr>
        <td colspan="9">&nbsp;</td>
      </tr>
      <tr>
        <td>用户名</td>
        <td>级别</td>
        <td>性别</td>
        <td>年龄</td>
        <td>出生日期</td>
        <td>现居住地</td>
        <td>籍贯</td>
        <td>身份证号</td>
        <td>操作</td>
      </tr>
        <%
        try
        {
			for(int i=0;i<rs.getPageRowsCount();i++)
			{
		%>
        <tr>
          <td><%=rs.getString("userName") %></td>
          <td><%=rs.getString("userFigure") %></td>
          <td><%=rs.getString("userSex") %></td>
          <td><%=rs.getString("userAge") %></td>
          <td><%=rs.getString("userBirthday").substring(0,10) %></td>
          <td><%=rs.getString("userAddress") %></td>
          <td><%=rs.getString("userHome") %></td>
          <td><%=rs.getString("userNumber") %></td>
          <td><font color="#FF0000"><a href='save-admin-user.jsp?action=del&userId=<%=rs.getString("userId") %>' >删除</a></font></td>
        </tr>
        <%
				rs.next();
			}
		%><tr>
        <td><input name="userName" type="text" id="userName" size="10"></td>
        <td><input name="userFigure" type="text" id="userFigure" size="10"></td>
        <td><input name="userSex" type="text" id="userSex" size="4"></td>
        <td><input name="userAge" type="text" id="userAge" size="6"></td>
        <td><input name="userBirthday" type="text" id="userBirthday" size="10"></td>
        <td><input name="userAddress" type="text" id="userAddress" size="10"></td>
        <td><input name="userHome" type="text" id="userHome" size="10"></td>
        <td><input name="userNumber" type="text" id="userNumber" size="20"></td>
        <td>
        <input id="bAdd" name="bAdd" type="button" value="添加" onClick="addUser()"></td>
      </tr>
		<tr>
			<td colspan="9" align="right"><div id="brokenLine"></div><table  border="0" cellpadding="0" cellspacing="3">
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
				  <input id="pageCount" name="pageCount" type="hidden" value="<%=pgTotal %>" >  &nbsp;</td>
                  <td height="25">
       		      <input name="goIndex" type="text" id="goIndex" size="5" value="<%=pgDes %>"></td>
                  <td width="25" height="25">
				  		<button id="go" name="go" onClick="goIndex()"><img src="img/go.gif" width="25" height="25"></button></td>
                </tr>
                          </table></td>
			</tr>
		<%
			con.closeRs();
		}catch(Exception e) { }
        %>
      
    </table></td>
  </tr>
  <tr>
    <td><jsp:include page="bottom.jsp" flush="true" /></td>
  </tr>
</table>
</body>
</html>