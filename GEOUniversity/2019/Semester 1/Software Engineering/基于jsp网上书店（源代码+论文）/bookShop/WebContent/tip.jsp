<%@ page language="java" contentType="text/html; charset=gb2312"%>
<% request.setCharacterEncoding("gb2312"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="blue.css">
<title>��Ϣ��ʾ�����������</title>
<script language="javascript">
<!--
/*���������ָ��ҳ��*/
var leftSecond=3;
function toDes()
{
		document.location.href=document.getElementById("desPage").value;
}
function goDes()
{
	leftSecond--;
	document.getElementById("leftTime").innerText=leftSecond;
	if(leftSecond<=0)
		toDes();
}
function runTimer()
{
	document.getElementById("leftTime").innerText=leftSecond;
	setInterval("goDes();",1000); 
}
-->
</script>
</head>
<body onLoad="runTimer()">
<div align="center">
<table width="788"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><jsp:include page="head.jsp" flush="true"></jsp:include></td>
  </tr>
  <tr>
  	<td>
  		<div id="frameThin">
  		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
		  <tr>
		    <td>
		    	<div id="showTip" align="center">
		    	<%
		    		String errorTip="";
					if(request.getParameter("tip").equals("add-user-fail"))
						errorTip="����û�ʧ�ܣ�";
					else if(request.getParameter("tip").equals("add-user-ok"))
						errorTip="����û��ɹ���";
					else if(request.getParameter("tip").equals("del-user-fail"))
						errorTip="ɾ���û�ʧ�ܣ�";
					else if(request.getParameter("tip").equals("del-user-ok"))
						errorTip="ɾ���û��ɹ���";
					if(request.getParameter("tip").equals("change-pwd-fail"))
						errorTip="�޸�����ʧ�ܣ�";
					else if(request.getParameter("tip").equals("change-pwd-ok"))
						errorTip="�޸�����ɹ���";
					else if(request.getParameter("tip").equals("login-name"))
						errorTip="�û�������";
					else if(request.getParameter("tip").equals("webManager-login-ok"))
						errorTip="��վ����Ա��¼�ɹ���";
					else if(request.getParameter("tip").equals("bookReader-login-ok"))
						errorTip="���ߵ�¼�ɹ���";
					else if(request.getParameter("tip").equals("login-ok"))
						errorTip="��¼�ɹ���";
					else if(request.getParameter("tip").equals("login-pwd"))
						errorTip="�������";
					else if(request.getParameter("tip").equals("del-book-ok"))
						errorTip="ɾ��ͼ��ɹ���";
					else if(request.getParameter("tip").equals("del-book-fail"))
						errorTip="ɾ��ͼ��ʧ�ܣ�";
					else if(request.getParameter("tip").equals("add-book-ok"))
						errorTip="���ͼ��ɹ���";
					else if(request.getParameter("tip").equals("add-book-fail"))
						errorTip="���ͼ��ʧ�ܣ�";
					else if(request.getParameter("tip").equals("use-bag-fail"))
						errorTip="ʹ�ù��ﳵǰ�����ȵ�¼��";
					else if(request.getParameter("tip").equals("change-book-ok"))
						errorTip="�޸�ͼ����Ϣ�ɹ���";
					else if(request.getParameter("tip").equals("change-book-fail"))
						errorTip="�޸�ͼ����Ϣʧ�ܣ�";
					else if(request.getParameter("tip").equals("change-user-ok"))
						errorTip="�޸���Ϣ�ɹ���";
					else if(request.getParameter("tip").equals("change-user-fail"))
						errorTip="�޸���Ϣʧ�ܣ�";
					else if(request.getParameter("tip").equals("user-config-ok"))
						errorTip="ע��ɹ���";
					else if(request.getParameter("tip").equals("user-config-fail"))
						errorTip="ע��ʧ�ܣ�";
					else if(request.getParameter("tip").equals("check-all-ok"))
						errorTip="������¼�룡";
					else if(request.getParameter("tip").equals("add-topic-ok"))
						errorTip="���������ɹ���";
					else if(request.getParameter("tip").equals("add-topic-fail"))
						errorTip="��������ʧ�ܣ�";
					else if(request.getParameter("tip").equals("add-follow-ok"))
						errorTip="����ظ��ɹ���";
					else if(request.getParameter("tip").equals("add-follow-fail"))
						errorTip="����ظ�ʧ�ܣ�������ظ�֮ǰ�����ȵ�¼����";

					out.print("<font color='#FF0000'>"+errorTip+"</font>");
		    	%>
		    	</div>
				<input id="desPage" name="desPage" type="hidden" value='<%=request.getParameter("desPage") %>'></td>
		  </tr>
		  <tr>
		    <td><div align="center">
			  	<div id="leftTime"></div>���<br>
		        <input type="button" name="bShowTime" id="bShowTime" value="����" onClick="toDes()">
		    </div></td>
		  </tr>
		 </table>
		 </div>
		</td>
	</tr>
  <tr>
    <td><jsp:include page="bottom.jsp" flush="true" /></td>
  </tr>
</table>
</div>
</body>
</html>