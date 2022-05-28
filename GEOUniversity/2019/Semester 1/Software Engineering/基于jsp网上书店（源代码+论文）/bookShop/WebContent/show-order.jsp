<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="blue.css">
<title>购物车－－网上书店</title>
<script language="javascript">
<!--
function changeNum(n)
{
	var buyNum=window.prompt("输入您要购买的数量：",1);
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
        <td>书名</td>
        <td>作者</td>
        <td>出版社</td>
        <td>库存</td>
        <td>售价</td>
        <td>欲购数量</td>
        <td>&nbsp;</td>
      </tr>
	  <%
	  /*订单(session)　order　形如　-bookId,buyNum-bookId,buyNum-  ，
	  					依次为　　-图书编号，购买数量-图书编号，购买数量-			*/
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
        	<a href='deal-order.jsp?action=del&bookId=<%=orderItem[0] %>' >放弃</a></td>
      </tr>
      
	  <%
	  				dMoney+=Double.parseDouble(bookValue);
	  			}
			 	conNew.closeRs();
			}
		}catch(Exception e) { }
	  %>
      <tr>
        <td colspan="7"><div align="center">合计：<%=dMoney %>元人民币</div></td>
        </tr>
      <tr>
        <td colspan="7">
        <table width="100%"  border="0" cellspacing="5" cellpadding="0">
          <tr>
            <td><div align="center"><a href="deal-order.jsp?action=cancel-all">全部取消</a></div></td>
            <td>
				<input id="bookTotal" name="bookTotal" type="hidden" value='<%=bookTotal %>'>
				<div align="center"><input id="bCheckAll" name="bCheckAl" type="submit" value="确定购买"></div></td>
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
