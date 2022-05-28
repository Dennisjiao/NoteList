<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.util.regex.*,java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="blue.css">
<title>����ͼ�飭���������</title>
</head>

<body>
<div align="center">
<jsp:useBean id="con" class="myPk.Conn" scope="page" />
<%! 
//ת���ַ���
public String toChi(String str)
{
	if (str==null)
	{
		return "";
	}
	try
	{
		String temp_p=str;
		byte[] temp_t=temp_p.getBytes("ISO8859-1");
		String temp=new String(temp_t);
		return temp;
	}
	catch(Exception e) { }
	return str;
}
%>
<%
	String searchMode;
	String searchWord;
	int searchResult=0;
	String bookId=null;
	Pageable rsResult=null;
	String sMode=null;
	searchMode=toChi(request.getParameter("searchMode"));
	searchWord=toChi(request.getParameter("searchWord"));
	if(searchMode.equals("1"))
	{
		sMode="bookAuthor";
		searchMode="����";
	}
	else if(searchMode.equals("2"))
	{
		sMode="bookTitle";
		searchMode="����";
	}
	else if(searchMode.equals("3"))
	{
		sMode="bookPublish";
		searchMode="������";
	}
	searchWord=searchWord.replace("��"," ");		/*��Ӣ�����뷨�µĿո��滻һЩ�������뷨���缫Ʒ��ʣ��µĿո�*/
	searchWord=searchWord.trim();
	if(!searchWord.equals(""))
	{
		Pattern pat=Pattern.compile("[��|\\s]+");	/*���еĿո��Ǽ�Ʒ����µĿո�����ͬ���������뷨�µĿո񣬲�����\\s��ʾ*/
		String[] sw=pat.split(searchWord);
		try
		{
			String sql="select * from book where "+sMode+" like '%";
			int i;
			for(i=0;i<sw.length-1;i++)
				sql+=sw[i]+"%' or "+sMode+" like '%";
			sql+=sw[i]+"%' ";
			rsResult=con.getRs(sql);
			searchResult=rsResult.getRowsCount();
		}catch(Exception e) { }
	}
%>
  <table width="788"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td>
	  	<jsp:include page="head.jsp" flush="true" />
	  </td>
    </tr>
    <tr>
      <td>
	  <div id="frameThin">
	  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td colspan="9"><div id="titleBg">
              <div id="titleText">����&nbsp;&nbsp;<%=searchMode %>&nbsp;&nbsp;<font color="#FF0000">��<%=searchWord %></font>��&nbsp;&nbsp;���ҵ�<B>&nbsp;&nbsp;<%=searchResult %>&nbsp;&nbsp;</B>���飺</div>
              <br>
          </div></td>
        </tr>
        <%
        	if(searchResult>0)
        	{
        %>
        <tr bgcolor="#CCCCCC">
          <td>����</td>
          <td>����</td>
          <td>������</td>
          <td>����ʱ��</td>
          <td>���</td>
          <td>ӡ��</td>
          <td>���</td>
          <td>�ۼ�</td>
          <td>���</td>
        </tr>
        <%
        	}
        	else
        	{
        %>
        <tr><td colspan="9" align="center"><font color="#FF0000">�Ҳ�������鼮��</font></td></tr>
        <%
        	}
        %>
        <%
			int pgDes=0;
			int pgTotal=0;
		  	try
			{
				while(rsResult.next())
				{
					bookId=rsResult.getString("bookId");
		%>
        <tr>
          <td><div align="left"><a href='book-information.jsp?id=<%=bookId %>'><%=rsResult.getString("bookTitle") %></a></div></td>
          <td><div align="center"><a href='book-information.jsp?id=<%=bookId %>'><%=rsResult.getString("bookAuthor") %></a></div></td>
          <td><div align="right"><a href='book-information.jsp?id=<%=bookId %>'><%=rsResult.getString("bookPublish") %></a></div></td>
          <td><div align="center"><a href='book-information.jsp?id=<%=bookId %>'><%=rsResult.getString("bookDate").substring(0,10) %></a></div></td>
          <td><div align="right"><a href='book-information.jsp?id=<%=bookId %>'><%=rsResult.getString("bookBC") %></a></div></td>
          <td><div align="right"><a href='book-information.jsp?id=<%=bookId %>'><%=rsResult.getString("bookYC") %></a></div></td>
          <td><div align="right"><a href='book-information.jsp?id=<%=bookId %>'><%=rsResult.getString("bookPrice") %></a></div></td>
          <td><div align="right"><a href='book-information.jsp?id=<%=bookId %>'><%=rsResult.getString("bookValue") %></a></div></td>
          <td><div align="right"><a href='book-information.jsp?id=<%=bookId %>'><%=rsResult.getString("bookStorage") %></a></div></td>
        </tr>
       <%
				}
				con.closeRs();
			}catch(Exception e) { }
	   %>
      </table>
	  </div></td>
    </tr>
    <tr>
      <td>
	  	<jsp:include page="bottom.jsp" flush="true" />
	  </td>
    </tr>
  </table>
</div>
</body>
</html>
