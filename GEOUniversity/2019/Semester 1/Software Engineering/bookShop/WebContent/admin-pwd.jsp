<%@ page language="java" contentType="text/html; charset=gb2312"%>
<% request.setCharacterEncoding("gb2312"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="blue.css">
<title>修改密码－－后台管理</title>
<script language="javascript">
<!--
	/*判断密码是否含有不是　字母、数字、下划线　的字符*/
	function checkPwd()
	{
		var sPwd;
		var pwdTem;
			sPwd=document.getElementById("newPwd").value;
			pwdTem=sPwd;
			re=/([\W]+?)([\w]+?)([\W]+?)/gi;
			sPwd=sPwd.replace(re,"$2");
		if(pwdTem!=sPwd)
		{
			document.getElementById("pwd").action="";
			window.alert("密码不能含有不是　字母、数字、下划线　的字符！");
			document.getElementById("newPwd").value="";
			document.getElementById("pwd").action="save-pwd.jsp";
		}
	}
-->
</script>
</head>
<body>
<div align="center">
  <table width="788"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><jsp:include page="head.jsp" flush="true"></jsp:include></td>
    </tr>
    <tr>
      <td><form id="pwd" name="pwd" action="save-pwd.jsp" method="post">
        <table width="100%"  border="0" cellspacing="5" cellpadding="0">
          <tr>
            <td><div align="right">新密码：</div></td>
            <td><input name="newPwd" type="password" id="newPwd">
                <div id="tipNewPwd"></div></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><input type="submit" name="changePwd" id="changePwd" value="提交" onClick="checkPwd()"></td>
          </tr>
        </table>
      </form></td>
    </tr>
    <tr>
      <td><jsp:include page="bottom.jsp" flush="true" /></td>
    </tr>
  </table>
</div>
</body>
</html>