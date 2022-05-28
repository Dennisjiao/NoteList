<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.text.*,java.util.*" %>
<% request.setCharacterEncoding("gb2312"); %>
<script language="javascript">
<!--
	/*弹出订单的打印预览*/
	function showCheck()
	{
		window.open("show-check.jsp");
	}
   /*注册*/
   function configNew()
   {
   		document.location.href="user.jsp";
   }
	/*将白布网加入收藏夹*/
	function addLike()
	{
			if ( window.sidebar && "object" == typeof( window.sidebar ) && "function" == typeof( window.sidebar.addPanel ) )
			{
					//  firefox
					window.sidebar.addPanel( '白布网', 'http://www.buybook.com/', '' );
			}
			else if ( document.all && "object" == typeof( window.external ) )
			{
					//  ie
					window.external.addFavorite( 'http://www.buybook.com/', '白布网' );
			}
	}
	/*判断密码是否含有不是　字母、数字、下划线　的字符*/
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
			window.alert("密码不能含有不是　字母、数字、下划线　的字符！");
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
        		SimpleDateFormat fNow=new SimpleDateFormat("yyyy年MM月dd日 E");
        		String sNow=fNow.format(cNow.getTime());
        		out.print(sNow);
        	}catch(Exception e) { }
        	%>
        	</div></td>
            <td width="220">
			<table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><div align="center"><a onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.buybook.com/');return(false);"  href="index-main.jsp">设白布为首页</a></div></td>
                <td width="2"><img src="img/vLine.gif" width="2" height="25"></td>
                <td><div align="center"><a href="javascript:addLike()">收藏白布</a></div></td>
              </tr>
            </table></td>
          </tr>
        </table>
<div  id="frameThin">
  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="188" height="66" background="img/logo.gif">&nbsp;</td>
            <td valign="bottom">　 <a href="index-main.jsp">首页</a> <a href="forum.jsp">论坛</a> <a href="index-main.jsp?sort=科技">科技</a> <a href="index-main.jsp?sort=生活">生活</a> <a href="index-main.jsp?sort=文艺">文艺</a> <a href="index-main.jsp?sort=管理">管理</a> <a href="index-main.jsp?sort=经济">经济</a> <a href="index-main.jsp?sort=政治">政治</a> <a href="index-main.jsp?sort=军事">军事</a> <a href="index-main.jsp?sort=体育">体育</a> <a href="index-main.jsp?sort=哲学">哲学</a><a href="index-main.jsp?sort=法律">法律</a> <a href="index-main.jsp?sort=历史">历史</a> <a href="index-main.jsp?sort=医学">医学</a> </td>
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
          <td><div align="right">用户名：</div></td>
          <td>
            <div align="left">
              <input name="headUserName" type="text" id="headUserName" size="20">
            </div></td>
        </tr>
        <tr>
          <td><div align="right">密　码：</div></td>
          <td><div align="left"><input name="headUserPwd" type="password" id="headUserPwd" size="20"></div></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>
		  	<div align="left">
				<input name="userLogin" type="button" id="userLogin" value="登录" onClick="checkPwd()">&nbsp;&nbsp;&nbsp;&nbsp;
				<input id="configNew" name="configNew" type="button" value="注册" onClick="configNew()">
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
        			out.print("游客");
        		}
        		else
        		{
        			out.print(session.getAttribute("userFigure").toString()+"  "+session.getAttribute("userName").toString());
        		}
        	%>
           &nbsp;&nbsp;您好！</div></td>
        </tr>
      </table></div></td>
  </tr>
  <tr>
    <td align="center">
      <%
	  String userFigure="";
	  if(session.getAttribute("userFigure")!=null)
		  userFigure=session.getAttribute("userFigure").toString();
      if(userFigure.equals("总管"))
      {
      %>
			<div id="frameThin"><a href="admin-pwd.jsp">修改密码</a>　<a href="admin-user.jsp">管理用户</a>　<a href="admin-book.jsp">管理书本</a></div>

	  <%
      }
	  else if(userFigure.equals("读者"))
	  {
	%>
			<div id="frameThin"><a href="admin-pwd.jsp">修改密码</a>　<a href="user.jsp">修改信息</a>　<a href="forum-add.jsp?action=add-topic">发表新帖</a></div>

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
    	<div id="frameThin"><a href="clearSession.jsp">注销</a>&nbsp;&nbsp;<a href="show-order.jsp"><img src="img/shoppingBag.gif" width="20" height="20">购物车</a>&nbsp;&nbsp;<a href="javascript:showCheck()"><img src="img/order.gif" width="20" height="20">订单</a> </div>
    <%
	 	}
	%>    </td>
  </tr>
</table>
<div  id="blankLine6Px"></div>