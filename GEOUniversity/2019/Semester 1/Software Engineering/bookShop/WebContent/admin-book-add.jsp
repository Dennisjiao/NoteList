<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>
<html>
<head>
<title>�������</title>
<link href="blue.css" rel="stylesheet" type="text/css">
<script language="javascript">
<!--
/*���ַ�����str���ӡ�Ubb��ת��Ϊ��Html����������*/
function ubb2html(str)
{
		/*����*/
		while(str.indexOf("\n")!=-1)
			str=str.substring(0,str.indexOf("\n"))+"<br>"+str.substring(str.indexOf("\n")+1);
		/*�ո�*/
		while(str.indexOf(" ")!=-1)
			str=str.substring(0,str.indexOf(" "))+"&nbsp"+str.substring(str.indexOf(" ")+1);
 		return str;
}
/*�����ϴ�ͼƬ�Ĵ���*/
function showUpload()
{
	childWindow=window.open("upload-file.jsp",width="400",height="300");
}
/*�ύ��ǰ��ת����Ubb��Ϊ��Html*/
function turnCode()
{
	document.getElementById("bookSummary").value=ubb2html(document.getElementById("bookSummary").value);
	document.getElementById("bookAuthorInformation").value=ubb2html(document.getElementById("bookAuthorInformation").value);
	document.getElementById("bookComment").value=ubb2html(document.getElementById("bookComment").value);
}
-->
</script>
</head>
<body>
<jsp:useBean id="con" class="myPk.Conn" scope="page" />
<%
	int bookId=-1;
	String bookTitle="";
	String bookSort="";
	String bookAuthor="";
	String bookPublish="";
	String bookDate="";
	String bookBC="";
	String bookYC="";
	String bookIsbn="";
	String bookWords="";
	String bookSheet="";
	String bookPrice="";
	String bookValue="";
	String bookSummary="";
	String bookAuthorInformation="";
	String bookComment="";
	String bookPhoto="";
	String bookStorage="";
	String bookCommend="";
	String action=request.getParameter("action");
	if(action.equals("change"))
	{
		bookId=Integer.parseInt(request.getParameter("bookId"));
		try
		{
			Pageable rs=con.getRs("select * from book where bookId="+bookId);
			if(rs.next())
			{
				bookTitle=rs.getString("bookTitle");
				bookSort=rs.getString("bookSort");
				bookAuthor=rs.getString("bookAuthor");
				bookPublish=rs.getString("bookPublish");
				bookDate=rs.getString("bookDate").substring(0,10);
				bookBC=rs.getString("bookBC");
				bookYC=rs.getString("bookYC");
				bookIsbn=rs.getString("bookIsbn");
				bookWords=rs.getString("bookWords");
				bookSheet=rs.getString("bookSheet");
				bookSummary=rs.getString("bookValue");
				bookSummary=rs.getString("bookSummary");
				bookAuthorInformation=rs.getString("bookAuthorInformation");
				bookComment=rs.getString("bookComment");
				bookPhoto=rs.getString("bookPhoto");
				bookPrice=rs.getString("bookPrice");
				bookValue=rs.getString("bookValue");
				bookStorage=rs.getString("bookStorage");
				bookCommend=rs.getString("bookCommend");
			}
		}catch(Exception e) { }
	}
%>	
<div align="center">
<table width="788"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><jsp:include page="head.jsp" flush="true"></jsp:include></td>
  </tr>
  <tr>
    <td><form id="addBook" name="addBook" action="save-admin-book.jsp" method="post">
      <table width="100%"  border="0" cellpadding="0" cellspacing="3">
        <tr>
          <td valign="top">
          		<input id="action" name="action" type="hidden" value="<%=action %>">
          		<input id="bookId" name="bookId" type="hidden" value="<%=bookId %>">
          		���顡����
              <input name="bookTitle" type="text" id="bookTitle" value="<%=bookTitle %>"></td>
          <td colspan="-1" valign="top"><input type="button" id="upBookPhoto"  name="upBookPhoto" value="�ϴ�����ͼƬ" onClick="showUpload()"></td>
        </tr>
        <tr>
          <td valign="top">���ࡡ��
              <input name="bookSort" type="text" id="bookSort" value="<%=bookSort %>"></td>
          <td colspan="-1" rowspan="5" valign="top"><div id="bookPhoto"><img src='img\book\<%=bookPhoto %>' width="120" height="170"></div></td>
        </tr>
        <tr>
          <td valign="top">�������ߣ�
              <input name="bookAuthor" type="text" id="bookAuthor" value="<%=bookAuthor %>">
              ���� 
            </td>
        </tr>
        <tr>
          <td valign="top"><div align="left">�������磺
                  <input name="bookPublish" type="text" id="bookPublish" value="<%=bookPublish %>">
                  <br>
          </div></td>
        </tr>
        <tr>
          <td valign="top"><div align="left">�������ڣ�
                  <input name="bookDate" type="text" id="bookDate" value="<%=bookDate %>">
          </div></td>
        </tr>
        <tr>
          <td valign="top"><div align="left">��&nbsp;&nbsp;ISBN��
              <input name="bookIsbn" type="text" id="bookIsbn" value="<%=bookIsbn %>">
          </div>            <p align="left">&nbsp;</p>          <div align="left"></div></td>
        </tr>
        <tr>
          <td valign="top">���桡�Σ�
            <input name="bookBC" type="text" id="bookBC" value="<%=bookBC %>"></td>
          <td colspan="-1" valign="top">��ӡ���Σ�
            <input name="bookYC" type="text" id="bookYC" value="<%=bookYC %>"></td>
        </tr>
        <tr>
          <td valign="top">���֡�����
            <input name="bookWords" type="text" id="bookWords" value="<%=bookWords %>"></td>
          <td colspan="-1" valign="top">��ֽ���ţ�
            <input name="bookSheet" type="text" id="bookSheet" value="<%=bookSheet %>"></td>
        </tr>
        <tr>
          <td valign="top">���⡡�棺
		  	<input name="bookStorage" type="text" id="bookStorage" value="<%=bookStorage %>"></td>
          <td colspan="-1" valign="top">���ơ�����
		  	<input name="bookCommend" type="text" id="bookStorage" value="<%=bookCommend %>" ></td>
        </tr>
        <tr>
          <td valign="top">���ꡡ�ۣ�
            <input name="bookPrice" type="text" id="bookPrice" value="<%=bookPrice %>"></td>
          <td colspan="-1" valign="top">���ۡ��ۣ�
            <input name="bookValue" type="text" id="bookValue" value="<%=bookValue %>"></td>
        </tr>
        <tr>
          <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4"><div id="brokenLine"></div>
            ������Ҫ��</td>
        </tr>
        <tr>
          <td colspan="4"><textarea name="bookSummary" id="bookSummary" cols="90" rows="15" onfocus="getTaPos()" onchange="getTaPos()" value="<%=bookSummary %>"><%=bookSummary %></textarea></td>
        </tr>
        <tr>
          <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4"><div id="brokenLine"></div>
            ���߼�飺 </td>
        </tr>
        <tr>
          <td colspan="4"><textarea name="bookAuthorInformation" cols="90" rows="5" id="bookAuthorInformation" value="<%=bookAuthorInformation %>"><%=bookAuthorInformation %></textarea></td>
        </tr>
        <tr>
          <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="4"><div id="brokenLine"></div>
            ý�����ۣ�</td>
        </tr>
        <tr>
          <td colspan="4"><textarea name="bookComment" cols="90" rows="5" id="bookComment" value="<%=bookComment %>"><%=bookComment %></textarea></td>
        </tr>
        <tr>
          <td colspan="4"><div align="center">
            <input type="submit" name="Submit" value="�ύ" onClick="turnCode()">
          </div></td>
        </tr>
      </table>
    </form></td>
  </tr>
  <tr>
    <td><jsp:include page="bottom.jsp" flush="true"></jsp:include></td>
  </tr>
</table>
</div>
</body>
</html>