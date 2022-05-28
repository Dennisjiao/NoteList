<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style type="text/css">
.stag {
	 font-family: 微软雅黑,宋体;
	FONT-WEIGHT: bold; 
	 BACKGROUND: url(img/search-mode.gif) no-repeat; 
	 WIDTH: 68px; 
	 CURSOR: pointer; 
	 LINE-HEIGHT: 24px; 
	 PADDING-TOP: 2px; 
	 FONT-FAMILY: Arial; 
	 HEIGHT: 24px; 
	 TEXT-ALIGN: center
}
.stag0 {
	 font-family: 微软雅黑,宋体;
	FLOAT: left; 
	 MARGIN-BOTTOM: -4px; 
	 WIDTH: 2px; 
	 LIST-STYLE-TYPE: none; 
	 HEIGHT: 20px
}
.stag1 {
	 font-family: 微软雅黑,宋体;
	WIDTH: 64px; 
	 CURSOR: pointer; 
	 LINE-HEIGHT: 24px; 
	 PADDING-TOP: 2px; 
	 FONT-FAMILY: Arial; 
	 HEIGHT: 24px; 
	 TEXT-ALIGN: center
}
UL {
	 font-family: 微软雅黑,宋体;
	FLOAT: left
}
LI {
	 font-family: 微软雅黑,宋体;
	FLOAT: left; 
	 LIST-STYLE-TYPE: none
}
LI IMG {
	MARGIN-BOTTOM: -4px
}
.search-content {
	 font-family: 微软雅黑,宋体;
	BORDER-RIGHT: #92c3f2 1px solid; 
	 BORDER-TOP: #92c3f2 1px; 
	 BACKGROUND: #cbe1ff; 
	 FLOAT: left; 
	 BORDER-LEFT: #92c3f2 1px solid; 
	 WIDTH: "100%"
	 BORDER-BOTTOM: #92c3f2 1px solid; 
	 HEIGHT: 36px; 
}
.search-button {
	 font-family: 微软雅黑,宋体;
	BORDER-TOP-WIDTH: 0px; 
	 BORDER-LEFT-WIDTH: 0px; 
	 FONT-SIZE: 13px; 
	 BACKGROUND: url(img/search-button.gif) no-repeat; 
	 BORDER-BOTTOM-WIDTH: 0px; 
	 WIDTH: 80px; 
	 CURSOR: pointer; 
	 COLOR: #fff; 
	 LINE-HEIGHT: 25px; 
	 HEIGHT: 25px; 
	 TEXT-ALIGN: center; 
	 BORDER-RIGHT-WIDTH: 0px
}
</style>
<SCRIPT language="javascript">
<!--
var search_mode;
/*改变搜索模式*/
function search_show(n)
{
	var search_key;
	for(var i=0;i<4;i++)
		document.getElementById("vLine-"+i).style.display="";
	for(var j=1;j<4;j++)
		document.getElementById("searchMode-"+j).className="stag1";
	document.getElementById("searchMode-"+n).className = "stag";
	document.getElementById("vLine-"+(n-1)).style.display = "none";
	document.getElementById("vLine-"+n).style.display = "none";
	search_action = "book-list.jsp";
	search_mode=n;
	switch(n){
		case 1://作者
			document.getElementById("search-button").value = "按作者搜索";
			search_key = document.getElementById("hotSearchAuthor").value;
			break;
		case 2://书名
			document.getElementById("search-button").value = "按书名搜索";
			search_key = document.getElementById("hotSearchBook").value;
			break;
		case 3://出版社
			document.getElementById("search-button").value = "按出版社搜索";
			search_key = document.getElementById("hotSearchPublish").value;
			break;
	}
	var tmp = "";
	var search_more = search_key.split(",");
	var search_str = "";
	for (var i=0; i<search_more.length; i++) 
	{
		if (search_more[i].length>0) 
		{
				tmp += search_more[i];
				if( tmp.length > 15 ) break;
				search_str += "<a href=" + search_action + "?searchWord=" + search_more[i] + "&searchMode="+search_mode+" target=_self>" + search_more[i] + "</a> ";
		}
	}
	document.getElementById("hotSearch").innerHTML = search_str;
}

/*显示搜索结果*/
function goSearch()
{
	document.location.href("book-list.jsp?searchWord="+document.getElementById("searchWord").value+"&searchMode="+search_mode);
}
-->
</SCRIPT>
<title>搜索图书－－网上书店</title>
</head>
<body onload="javascript:search_show(2)">
<jsp:useBean id="conBook" class="myPk.Conn" scope="page" />
<jsp:useBean id="conAuthor" class="myPk.Conn" scope="page" />
<jsp:useBean id="conPublish" class="myPk.Conn" scope="page" />
<% 
	String sHotSearchAuthor="";
	String sHotSearchBook="";
	String sHotSearchPublish="";
	try
	{
		/*提取热门搜索的书名*/
		Pageable rsBook=conBook.getRs("select * from hotSearch where searchMode='书名'");
		for(int i=0;i<rsBook.getRowsCount()-1;i++)
		{
			if(rsBook.next())
				sHotSearchBook+=rsBook.getString("searchWord")+",";
		}
		if(rsBook.next())
			sHotSearchBook+=rsBook.getString("searchWord");
		/*提取热门搜索的作者*/
		Pageable rsAuthor=conAuthor.getRs("select * from hotSearch where searchMode='作者'");
		for(int i=0;i<rsAuthor.getRowsCount()-1;i++)
		{
			if(rsAuthor.next())
				sHotSearchAuthor+=rsAuthor.getString("searchWord")+",";
		}
		if(rsAuthor.next())
			sHotSearchAuthor+=rsAuthor.getString("searchWord");
		/*提取热门搜索的出版社*/
		Pageable rsPublish=conPublish.getRs("select * from hotSearch where searchMode='出版社'");
		for(int i=0;i<rsPublish.getRowsCount()-1;i++)
		{
			if(rsPublish.next())
				sHotSearchPublish+=rsPublish.getString("searchWord")+",";
		}
		if(rsPublish.next())
			sHotSearchPublish+=rsPublish.getString("searchWord");
	}catch(Exception e) { }
%>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="5" height="26" background="img/search-left.gif">&nbsp;</td>
      <td height="26" background="img/search-bg.gif">
        <ul>
          <li id="vLine-0" class="stag0"></li>
          <li id="searchMode-1" class="stag1" onClick="search_show(1)">作者</li>
          <li id="vLine-1"><img class="search-img" src="img/vLine.gif"></li>
          <li id="searchMode-2" class="stag" onClick="search_show(2)">书名</li>
          <li id="vLine-2"><img class="search-img" src="img/vLine.gif"></li>
          <li id="searchMode-3" class="stag1" onClick="search_show(3)">出版社</li>
          <li id="vLine-3" class="stag0"></li>
      </ul></td>
      <td width="5" height="26" background="img/search-right.gif">&nbsp;</td>
    </tr>
    <tr class="search-content">
      <td>&nbsp;</td>
      <td valign="top">
      <div align="left">
        <table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="left">
			<input id="searchWord" name="searchWord" type="text" onFocus="this.select()" maxlength="50" size="50">
            <input id="search-button" name="search-button" type="button" value="按书名搜索" class="search-button" onClick="goSearch()">
            <input name="hotSearchAuthor" type="hidden" id="hotSearchAuthor" value='<%=sHotSearchAuthor %>' >	
            <input name="hotSearchBook" type="hidden" id="hotSearchBook" value='<%=sHotSearchBook %>' >	
            <input name="hotSearchPublish" type="hidden" id="hotSearchPublish" value='<%=sHotSearchPublish %>' >	
            <div id="hotSearch"></div></td>
          </tr>
        </table>
      	</div>
      </td>
      <td>&nbsp;</td>
    </tr>
  </table>
</body>
</html>