<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="blue.css">
<title>���ﳵ�����������</title>
<script language="javascript">
<!--
function changeNum(n)
{
	var buyNum=window.prompt("������Ҫ�����������",1);
	if((buyNum!=null)&&(buyNum>0))
	{
		document.location.href="deal-order.jsp?action=changeNum&bookId="+n+"&buyNum="+buyNum;
	}
}
-->
</script>
</head>

<body>
<div align="center">
<table width="788"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="2"><jsp:include page="head.jsp" flush="true"></jsp:include></td>
  </tr>
  <tr>
    <td colspan="2">
	<div id="frameThin">
	<form id="dealOrder" name="dealOrder" action="deal-order.jsp?action=check-all" method="post">
	<table width="100%"  border="0" cellspacing="5" cellpadding="0">
      <tr bgcolor="#CCCCCC">
        <td>����</td>
        <td>����</td>
        <td>������</td>
        <td>���</td>
        <td>�ۼ�</td>
        <td>��������</td>
        <td>&nbsp;</td>
      </tr>
	  <%
	  /*����(session)��order�����硡-bookId,buyNum-bookId,buyNum-  ��
	  					����Ϊ����-ͼ���ţ���������-ͼ���ţ���������-			*/
	  	Double dMoney=new Double(0);
	  	int bookTotal=0;
		try
		{
			String sOrder=session.getAttribute("order").toString();
			String[] orderBook=sOrder.split("-");
			bookTotal=orderBook.length;
			for(int i=0;i<orderBook.length;i++)
			{
				String[] orderItem=orderBook[i].split(",");
				%>
				<%
				Conn conNew=new Conn();
				Pageable rs=conNew.getRs("select * from book where bookId="+Integer.parseInt(orderItem[0]));
				if(rs.next())
				{
					String bookValue=rs.getString("bookValue");
		%>
      <tr>
        <td>
			<input id='bookId-<%=i %>' name='bookId-<%=i %>' type="hidden" value='<%=orderItem[0] %>'>
			<div id='bookTitle-<%=i %>' ><%=rs.getString("bookTitle")  %></div></td>
        <td>
        	<div id='bookAuthor-<%=i %>' ><%=rs.getString("bookAuthor")  %></div></td>
        <td>
        	<div id='bookPublish-<%=i %>' ><%=rs.getString("bookPublish")  %></div></td>
        <td>
        	<div id='bookStorage-<%=i %>' ><%=rs.getString("bookStorage")  %></div></td>
        <td>
			<input id='bookValue-<%=i %>' name='bookValue-<%=i %>' type="hidden" value='<%=bookValue %>'>
        	<%=bookValue  %></td>
        <td>
			<input id='buyNum-<%=i %>' name='buyNum-<%=i %>' type="hidden" value='<%=orderItem[1] %>'>
       	 	<input id='bBuyNum-<%=i %>' name='bBuyNum-<%=i %>' type="button" value='<%=orderItem[1] %>'  onClick="changeNum(<%=Integer.parseInt(orderItem[0]) %>)"></td>
        <td>
        	<a href='deal-order.jsp?action=del&bookId=<%=orderItem[0] %>' >����</a></td>
      </tr>
      
	  <%
	  				dMoney+=Double.parseDouble(bookValue);
	  			}
			 	conNew.closeRs();
			}
		}catch(Exception e) { }
	  %>
      <tr>
        <td colspan="7"><div align="center">�ϼƣ�<%=dMoney %>Ԫ�����</div></td>
        </tr>
      <tr>
        <td colspan="7">
        <table width="100%"  border="0" cellspacing="5" cellpadding="0">
          <tr>
            <td><div align="center"><a href="deal-order.jsp?action=cancel-all">ȫ��ȡ��</a></div></td>
            <td>
				<input id="bookTotal" name="bookTotal" type="hidden" value='<%=bookTotal %>'>
				<div align="center"><input id="bCheckAll" name="bCheckAl" type="submit" value="ȷ������"></div></td>
          </tr>
        </table></td>
      </tr>
    </table></form>
	</div></td>
  </tr>
  
  
  <tr>
    <td colspan="2"><jsp:include page="bottom.jsp" flush="true"></jsp:include></td>
    </tr>
</table>
</div>
</body>
</html>
