<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.text.*,java.util.*" %>
<% request.setCharacterEncoding("gb2312"); %>
<script language="javascript">
<!--
	/*���������Ĵ�ӡԤ��*/
	function showCheck()
	{
		window.open("show-check.jsp");
	}
   /*ע��*/
   function configNew()
   {
   		document.location.href="user.jsp";
   }
	/*���ײ��������ղؼ�*/
	function addLike()
	{
			if ( window.sidebar && "object" == typeof( window.sidebar ) && "function" == typeof( window.sidebar.addPanel ) )
			{
					//  firefox
					window.sidebar.addPanel( '�ײ���', 'http://www.buybook.com/', '' );
			}
			else if ( document.all && "object" == typeof( window.external ) )
			{
					//  ie
					window.external.addFavorite( 'http://www.buybook.com/', '�ײ���' );
			}
	}
	/*�ж������Ƿ��в��ǡ���ĸ�����֡��»��ߡ����ַ�*/
	function checkPwd()
	{
		var sPwd;
		var pwdTem;
			sPwd=document.getElementById("headUserPwd").value;
			pwdTem=sPwd;
			re=/([\W]+?)([\w]+?)([\W]+?)/gi;
			sPwd=sPwd.replace(re,"$2");
		if(pwdTem==sPwd)
		{
			document.location.href="userLogin.jsp?headUserName="+document.getElementById("headUserName").value+"&headUserPwd="+document.getElementById("headUserPwd").value;
		}
		else
		{
			window.alert("���벻�ܺ��в��ǡ���ĸ�����֡��»��ߡ����ַ���");
			document.getElementById("headUserPwd").value="";
		}
	}
-->
</script>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td>
            <div align="left">
            <%
        	try
        	{
        		Calendar cNow=Calendar.getInstance();
        		SimpleDateFormat fNow=new SimpleDateFormat("yyyy��MM��dd�� E");
        		String sNow=fNow.format(cNow.getTime());
        		out.print(sNow);
        	}catch(Exception e) { }
        	%>
        	</div></td>
            <td width="220">
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="center"><a onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.buybook.com/');return(false);"  href="index-main.jsp">��ײ�Ϊ��ҳ</a></div></td>
                <td width="2"><img src="img/vLine.gif" width="2" height="25"></td>
                <td><div align="center"><a href="javascript:addLike()">�ղذײ�</a></div></td>
              </tr>
            </table></td>
          </tr>
        </table>
<div  id="frameThin">
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="188" height="66" background="img/logo.gif">&nbsp;</td>
            <td valign="bottom">�� <a href="index-main.jsp">��ҳ</a> <a href="forum.jsp">��̳</a> <a href="index-main.jsp?sort=�Ƽ�">�Ƽ�</a> <a href="index-main.jsp?sort=����">����</a> <a href="index-main.jsp?sort=����">����</a> <a href="index-main.jsp?sort=����">����</a> <a href="index-main.jsp?sort=����">����</a> <a href="index-main.jsp?sort=����">����</a> <a href="index-main.jsp?sort=����">����</a> <a href="index-main.jsp?sort=����">����</a> <a href="index-main.jsp?sort=��ѧ">��ѧ</a><a href="index-main.jsp?sort=����">����</a> <a href="index-main.jsp?sort=��ʷ">��ʷ</a> <a href="index-main.jsp?sort=ҽѧ">ҽѧ</a> </td>
          </tr>
  </table>
</div>
<div id="blankLine3Px"></div>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top"><jsp:include page="search.jsp" flush="true"></jsp:include></td>
    <td width="220" valign="top">
      <%
			if(session.getAttribute("userName")==null)
			{
		%>
	  <div id="frameThin">
      <table width="100%"  border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td><div align="right">�û�����</div></td>
          <td>
            <div align="left">
              <input name="headUserName" type="text" id="headUserName" size="20">
            </div></td>
        </tr>
        <tr>
          <td><div align="right">�ܡ��룺</div></td>
          <td><div align="left"><input name="headUserPwd" type="password" id="headUserPwd" size="20"></div></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>
		  	<div align="left">
				<input name="userLogin" type="button" id="userLogin" value="��¼" onClick="checkPwd()">&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="configNew" name="configNew" type="button" value="ע��" onClick="configNew()">
				</div></td>
        </tr>
      </table>
      </div>
      <%
			}
		%> 
	  <div id="frameThin">
      <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><div align="center">
              <%
        		if((session.getAttribute("userName")==null))
        		{
        			out.print("�ο�");
        		}
        		else
        		{
        			out.print(session.getAttribute("userFigure").toString()+"  "+session.getAttribute("userName").toString());
        		}
        	%>
           &nbsp;&nbsp;���ã�</div></td>
        </tr>
      </table></div></td>
  </tr>
  <tr>
    <td align="center">
      <%
	  String userFigure="";
	  if(session.getAttribute("userFigure")!=null)
		  userFigure=session.getAttribute("userFigure").toString();
      if(userFigure.equals("�ܹ�"))
      {
      %>
			<div id="frameThin"><a href="admin-pwd.jsp">�޸�����</a>��<a href="admin-user.jsp">�����û�</a>��<a href="admin-book.jsp">�����鱾</a></div>

	  <%
      }
	  else if(userFigure.equals("����"))
	  {
	%>
			<div id="frameThin"><a href="admin-pwd.jsp">�޸�����</a>��<a href="user.jsp">�޸���Ϣ</a>��<a href="forum-add.jsp?action=add-topic">��������</a></div>

	  <%
      }
	  %>
	</td>
    <td width="220">
	<%
      	if(session.getAttribute("userName")!=null)
      	{
    %>	 
    	<OBJECT id="WebBrowser" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"  VIEWASTEXT> </OBJECT>
    	<div id="frameThin"><a href="clearSession.jsp">ע��</a>&nbsp;&nbsp;<a href="show-order.jsp"><img src="img/shoppingBag.gif" width="20" height="20">���ﳵ</a>&nbsp;&nbsp;<a href="javascript:showCheck()"><img src="img/order.gif" width="20" height="20">����</a> </div>
    <%
	 	}
	%>    </td>
  </tr>
</table>
<div  id="blankLine6Px"></div>