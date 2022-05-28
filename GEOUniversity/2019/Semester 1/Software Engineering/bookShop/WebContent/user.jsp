<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>
<%
	if(session.getAttribute("userFigure")==null)
		out.print("用户注册");
	else
		out.print("用户信息");
%>
－－网上书店
</title>
</head>
<body>
<jsp:useBean id="con" class="myPk.Conn" scope="page" />
<%
	String action="";
	String userName="";
	String userSex="";
	String userAge="";
	String userBirthday="";
	String userHome="";
	String userNumber="";
	String userFigure="";
	if(session.getAttribute("userFigure")!=null)
		userFigure=session.getAttribute("userFigure").toString();
	if(userFigure.equals("读者"))
	{
		action="change";
		String sql="select * from user where userId="+Integer.parseInt(session.getAttribute("userId").toString());
		Pageable rs=con.getRs(sql);
		if(rs.next())
		{
			userName=rs.getString("userName");
			userSex=rs.getString("userSex");
			userAge=rs.getString("userAge");
			userBirthday=rs.getString("userBirthday").substring(0,10);
			userHome=rs.getString("userHome");
			userNumber=rs.getString("userNumber");
		}//if(rs.next())
	}//if(session.getAttribute("userFigure").toString.equals("读者"))
	else
	{
		action="add";
	}
%>		
<div align="center">
  <table width="788"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><jsp:include page="head.jsp" flush="true"></jsp:include></td>
    </tr>
    <tr>
      <td>
	  <div id="frameThin">
	  <form id="user" action="save-user.jsp" method="post">
	  <table width="100%"  border="0" cellspacing="10" cellpadding="0">
        <tr>
          <td align="right"><input id="action" name="action" type="hidden" value="<%=action %>"><div align="right">用户名：</div></td>
          <td align="left"><input id="userName" type="text" name="userName" value="<%=userName %>"></td>
        </tr>
        <%
        	if(action.equals("add"))
			{
        %>
        <tr>
        	<td align="right"><div align="right">密　码：</div></td>
        	<td align="left"><input id="userPwd" name="userPwd" type="password"></td></tr>
        <%
			}
        %>
        <tr>
          <td align="right">性　别：</td>
          <td align="left"><input id="userSex" type="text" name="userSex" value="<%=userSex %>"></td>
        </tr>
        <tr>
          <td align="right">年　龄：</td>
          <td align="left"><input id="userAge" type="text" name="userAge" value="<%=userAge %>"></td>
        </tr>
        <tr>
          <td align="right">出生日期：</td>
          <td align="left"><input id="userBirthday" type="text" name="userBirthday" value="<%=userBirthday %>"></td>
        </tr>
        <tr>
          <td align="right">籍　贯：</td>
          <td align="left"><input id="userHome" type="text" name="userHome" value="<%=userHome %>"></td>
        </tr>
        <tr>
          <td align="right">身份证号码：</td>
          <td align="left"><input id="userNumber" type="text" name="userNumber" value="<%=userNumber %>"></td>
        </tr>
        <tr>
          <td colspan="2"><div align="center">
            <input type="submit" name="Submit" value="提交">
          </div></td>
          </tr>
      </table>
	  </form>
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