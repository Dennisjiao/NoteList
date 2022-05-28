<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="blue.css">
<script language="javascript">
<!--
/*将此书放入购物车*/
function stayIt(n)
{
	var buyNum=window.prompt("输入您要购买的数量：",1);
	var curUrl=document.location.href;
	document.location.href="deal-order.jsp?action=add&bookId="+n+"&buyNum="+buyNum+"&curUrl="+curUrl;
}
-->
</script>
<jsp:useBean id="con" class="myPk.Conn" scope="page" />
<%
	int bookId=Integer.parseInt(request.getParameter("id"));
	Pageable rs=null;
	Pageable rsBuy=null;
	Pageable rsHot=null;
	Pageable rsAH=null;
	String bookComment="";
	String bookAuthorInformation="";
	String bookSummary="";
	String bookPhoto="";
	String bookAuthor="";
	String bookDate="";
	String bookBC="";
	String bookWords="";
	String bookPrice="";
	String bookPublish="";
	String bookIsbn="";
	String bookYC="";
	String bookSheet="";
	String bookValue="";
	String bookTitle="";
	String bookSort="";
	String bookCommend="";
	String bookStorage="";
	try
	{
		rs=con.getRs("select * from book where bookId="+bookId);
		if(rs.next())
		{
		bookComment=rs.getString("bookComment");
		bookAuthorInformation=rs.getString("bookAuthorInformation");
		bookSummary=rs.getString("bookSummary");
		bookPhoto="./img\\book\\"+rs.getString("bookPhoto");
		bookAuthor=rs.getString("bookAuthor");
		bookDate=rs.getString("bookDate");
		bookBC=rs.getString("bookBC");
		bookWords=rs.getString("bookWords");
		bookPrice=rs.getString("bookPrice");
		bookPublish=rs.getString("bookPublish");
		bookIsbn=rs.getString("bookIsbn");
		bookYC=rs.getString("bookYC");
		bookSheet=rs.getString("bookSheet");
		bookValue=rs.getString("bookValue");
		bookTitle=rs.getString("bookTitle");
		bookSort=rs.getString("bookSort");
		bookCommend=rs.getString("bookCommend");
		bookStorage=rs.getString("bookStorage");
		}
		con.closeRs();
	}catch(Exception e) { }
%>
<title>《<%=bookTitle %>》－－网上书店</title>
</head>

<body>
<div align="center">
  <table width="788" border="0" cellpadding="0" cellspacing="0">
  	<tr>
  		<td>
  			<jsp:include page="head.jsp" flush="true" /></td></tr>
    <tr>
      <td>
		  <table width="100%"  border="0" cellspacing="1" cellpadding="0">
              <tr>
                <td width="188" valign="top">
					<div id="frameThin">
					  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td>
								<div id="titleBg"><div id="titleText">[<%=bookSort %>]类畅销榜</div><br></div>
							</td>
                          </tr>
                          <tr>
                            <td>
								<div align="left">
								    <%
                try
                {
                	Conn conHot=new Conn();
                	rsHot=conHot.getRs("select * from book where bookSort='"+bookSort+"' order by bookSold desc");
                	rsHot.setPageSize(10);
                	rsHot.gotoPage(1);
                	for(int i=0;i<rsHot.getPageRowsCount();i++)
                	{
                		%>
                                    <a href='book-information.jsp?id=<%=rsHot.getString("bookId") %>'><%=rsHot.getString("bookTitle") %>&nbsp;&nbsp;<%=rsHot.getString("bookAuthor") %></a><br>
                                    <%
                        rsHot.next();
                	}
                	conHot.closeRs();
                }catch(Exception e) { out.print(e.toString()); }
                %>
							      </div></td>
                          </tr>
                      </table>
				</div>	
				<div  id="blankLine3Px"></div>
				<div id="frameThin">
				  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td>
					  	<div id="titleBg"><div id="titleText">作者<%=bookAuthor %>的畅销榜</div><br></div>
					  </td>
                    </tr>
                    <tr>
                      <td>
                        <div align="left">
                          <%
                try
                {
                	Conn conAH=new Conn();
                	rsAH=conAH.getRs("select * from book where bookAuthor='"+bookAuthor+"' order by bookSold desc");
                	rsAH.setPageSize(10);
                	rsAH.gotoPage(1);
                	for(int i=0;i<rsAH.getPageRowsCount();i++)
                	{
                		%>
                            <a href='book-information.jsp?id=<%=rsAH.getString("bookId") %>'><%=rsAH.getString("bookTitle") %>&nbsp;&nbsp;<%=rsAH.getString("bookDate").substring(0,7) %></a><br>
                        <%
                        rsAH.next();
                	}
                	conAH.closeRs();
                }catch(Exception e) { }
                %>
                      </div></td>
                    </tr>
                  </table>
				</div>				</td>
                <td valign="top">				<div id="frameThin"><table width="100%"  border="0" cellpadding="0" cellspacing="3">
                  <tr>
                    <td width="122" rowspan="7" valign="top">
                      <div align="center"><img src='<%=bookPhoto %>' width="120" height="170" alt='<%=bookTitle %>' /> </div></td>
                    <td><div id="titleBg"><div id="titleText">《<%=bookTitle %>》</div><br></div></td>
                  </tr>
                  <tr>
                    <td><div align="left">作者：<%=bookAuthor %><br>
                    </div></td>
                  </tr>
                  <tr>
                    <td><div align="left">出版社：<%=bookPublish %></div></td>
                  </tr>
                  <tr>
                    <td><div align="left">出版日期：<%=bookDate.substring(0,10) %>
				    </div></td>
                  </tr>
                  <tr>
                    <td><div align="left">版次：<%=bookBC %></div></td>
                  </tr>
                  <tr>
                    <td><div align="left">印次：<%=bookYC %> </div></td>
                  </tr>
                  <tr>
                    <td><div align="left">ISBN：<%=bookIsbn %></div></td>
                  </tr>
                  <tr>
                    <td><div align="left">库存：<%=bookStorage %></div></td>
                    <td><div align="left">推荐:<%=bookCommend %></div></td>
                  </tr>
                  <tr>
                    <td><div align="left">字数：<%=bookWords %></div></td>
                    <td><div align="left">纸张：<%=bookSheet %></div></td>
                  </tr>
                  <tr>
                    <td><div align="left">标价：<%=bookPrice %></div></td>
                    <td><div align="left">售价：<%=bookValue %></div></td>
                  </tr>
                  <tr>
                    <td colspan="3"><div align="center">
                        <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td>                           
                              <div align="center">
                                <input id="bStay" name="bStay" type="button" value="放入购物车" onClick="stayIt(<%=bookId %>)">
                            </div></td>
                          </tr>
                        </table>
                    </div></td>
                  </tr>
                  <tr>
                    <td colspan="3"><div id="brokenLine"></div></td>
                  </tr>
                  <tr>
                    <td colspan="3">内容提要：</td>
                  </tr>
                  <tr>
                    <td colspan="3"><div align="left"><%=bookSummary %></div></td>
                  </tr>
                  <tr>
                    <td colspan="3"><div id="brokenLine"></div></td>
                  </tr>
                  <tr>
                    <td colspan="3">作者简介：</td>
                  </tr>
                  <tr>
                    <td colspan="3"><div align="left"><%=bookAuthorInformation %></div></td>
                  </tr>
                  <tr>
                    <td colspan="3"><div id="brokenLine"></div></td>
                  </tr>
                  <tr>
                    <td colspan="3">媒体评论：</td>
                  </tr>
                  <tr>
                    <td colspan="3"><div align="left"><%=bookComment %></div></td>
                  </tr>
                  <tr>
                    <td colspan="3">&nbsp;</td>
                </table>
				</div></td>
                <td width="188" valign="top">
					<div id="frameThin">
					  <div align="left">
					    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td>
						  	  <div id="titleBg"><div id="titleText">买此书的顾客还买了：</div><br></div>
						    </td>
                          </tr>
                          <tr>
                            <td>                      
                  <%
               	 	try
					{
               	 		String sqlSame="select * from book where bookId in";
						sqlSame+="(select bookId from orderItem where orderId in";
						sqlSame+="(select orderId from orderItem where bookId="+bookId+"))";
						sqlSame+=" and bookId<>"+bookId;
						Conn conSame=new Conn();
						Pageable rsSame=conSame.getRs(sqlSame);
               	 		
						//Pageable rsSame=conSame.getRs(sqlSame);
						rsSame.setPageSize(10);
						rsSame.gotoPage(1);
						for(int i=0;i<rsSame.getPageRowsCount();i++)
						{
							if(rsSame.next())
							{
								Conn conSB=new Conn();
								Pageable rsSB=conSB.getRs("select * from book where bookId="+Integer.parseInt(rsSame.getString("bookId")));
								if(rsSB.next())
								{
						%>
                        <a href='book-information.jsp?id=<%=rsSB.getString("bookId") %>'><%=rsSB.getString("bookTitle") %>&nbsp;&nbsp;<%=rsSB.getString("bookAuthor") %></a><br>
                        <%
								}//if(rsSB.next())
							}//if(rsSame.next())
						}//for(int i=0;i<rsSame.getPageRowsCount();i++)
                  		conSame.closeRs();
						}catch(Exception e) { out.print(e.toString()); }
				%>
							  </td>
                          </tr>
                        </table>
				      </div>
				  </div>				</td>
              </tr>
        </table></td>
    </tr>
    <tr>
    	<td>
    		<jsp:include page="bottom.jsp" flush="true" /></td></tr>
  </table>
</div>
</body>
</html>
