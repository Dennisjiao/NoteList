<%@ page language="java" contentType="text/html; charset=gb2312" pageEncoding="gb2312" 
	import="java.text.*,java.util.*,myPk.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="blue.css">
<title>���������������</title>
<script language="javascript">
<!--
function init()
{
	document.all.WebBrowser.ExecWB(7,1);
}
-->
</script>
</head>
<%
	if(session.getAttribute("order")!=null)
	{
%>
<body onLoad="init()">
<table width="100%"  border="0" cellspacing="1" cellpadding="0">
  <tr>
    <td><div align="center"><img src="img/logo.gif" width="188" height="66"></div></td>
  </tr>
  <tr>
    <td><div align="center"><strong>�ײ�������궩��</strong></div></td>
  </tr>
  <tr>
    <td><table width="100%"  border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td>������ţ�<%=session.getAttribute("orderId").toString() %></td>
          <td>�ͻ���ţ�<%=session.getAttribute("userId").toString() %></td>
          <td>�û�����<%=session.getAttribute("userName").toString() %> </td>
          <td>���ڣ�
            <%
        	try
        	{
        		Calendar cNow=Calendar.getInstance();
        		SimpleDateFormat fNow=new SimpleDateFormat("yyyy��MM��dd��");
        		String sNow=fNow.format(cNow.getTime());
        		out.print(sNow);
        	}catch(Exception e) { }
			%>
	      </td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td><table width="100%"  border="0" cellspacing="3" cellpadding="0">
        <tr bgcolor="#CCCCCC">
          <td>ͼ����</td>
          <td>����</td>
          <td>����</td>
          <td>������</td>
          <td>ISBN��</td>
          <td>����</td>
          <td>����</td>
          <td>�ϼ� </td>
        </tr>
		<%
			String rowColor="#CCFFFF";
			Pageable rsBook=null;
			Pageable rsItem=null;
			int buyNum=0;
			Double bookValue=Double.parseDouble("0");
			try
			{
				int orderId=Integer.parseInt(session.getAttribute("orderId").toString());
				Conn conItem=new Conn();
				rsItem=conItem.getRs("select * from orderItem where orderId="+orderId);
				for(int i=0;i<rsItem.getRowsCount();i++)
				{
					if(rowColor=="#FFFFCC")
						rowColor="#CCFFFF";
					else
						rowColor="#FFFFCC";
					Conn conBook=new Conn();
					if(rsItem.next())
					{
						buyNum=Integer.parseInt(rsItem.getString("buyNum"));
						bookValue=Double.parseDouble(rsItem.getString("bookValue"));
						rsBook=conBook.getRs("select * from book where bookId="+Integer.parseInt(rsItem.getString("bookId")));
					}
					if(rsBook.next())
					{
		%>
        <tr bgcolor="<%=rowColor%>">
          <td><%=rsBook.getString("bookId") %></td>
          <td><%=rsBook.getString("bookTitle") %></td>
          <td><%=rsBook.getString("bookAuthor") %></td>
          <td><%=rsBook.getString("bookPublish") %></td>
          <td><%=rsBook.getString("bookIsbn") %></td>
          <td><%=bookValue %></td>
          <td><%=buyNum  %></td>
          <td><%=bookValue*buyNum %></td>
        </tr>
		<%
					}//if(rsBook.next())
				}//for
			}catch(Exception e) { }
		%>
      </table></td>
  </tr>
  <tr>
    <td>
  	    <OBJECT id="WebBrowser" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"  VIEWASTEXT> 
	    </OBJECT>
    </td>
  </tr>
</table>
</body>
<%
	}
%>
</html>