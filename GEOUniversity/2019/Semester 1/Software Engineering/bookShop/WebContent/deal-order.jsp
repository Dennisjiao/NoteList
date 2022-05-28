<%@ page language="java" contentType="text/html; charset=gb2312"  import="java.sql.*,myPk.*" %>
<% request.setCharacterEncoding("gb2312"); %>

<%!
	/*　如果字符串　str　的长度小于　n　，则在其左边填充足够的　*　号，使其长度为　n　*/
	public String appString(String str,int n)
	{
		int strLen=str.length();
		if(strLen>=n)
			return str;
		String sTem="";
		String sBlank="*";
		for(int i=0;i<(n-strLen);i++)
			sTem+=sBlank;
		sTem+=str;
		return sTem;
	}
	/*　将　str　以　-　为标志分段，于各小段中再以　,　分为两段（记为段a和段b），在每个段a的左边填充　*　号，使每个段a的长度均为　n　*/
	public String appLongStr(String str,int n)
	{
		String[] sTem=str.split("-");
		String sRet="";
		for(int i=0;i<sTem.length;i++)
		{
			String[] splitByDot=sTem[i].split(",");
			splitByDot[0]=appString(splitByDot[0],n);
			sTem[i]=splitByDot[0]+","+splitByDot[1];
		}
		if(sTem.length==1)
			return sTem[0];
		for(int  i=0;i<(sTem.length-1);i++)
			sRet+=sTem[i]+"-";
		sRet+=sTem[sTem.length-1];
		return sRet;
	}
%>
<%	
	if(session.getAttribute("userId")==null)
		response.sendRedirect("tip.jsp?tip=use-bag-fail&desPage="+request.getParameter("curUrl"));			//用户未登录
	try
	{
		String act="";
		if(request.getParameter("action")!=null)
			act=request.getParameter("action");
		String sTem="";
		if(act.equals("cancel-all"))
		{
			//清空购物车
			if(session.getAttribute("order")!=null)
				session.removeAttribute("order");
		}// if(act.equals("cancel-all"))
		else if(act.equals("check-all"))
		{
				String sOrder=session.getAttribute("order").toString();
				String[] orderBook=sOrder.split("-");
				String sqlGetMaxId="select * from orderTotal order by orderId desc;";
				Conn conMaxId=new Conn();
				int maxOrderId;
				Pageable rs=conMaxId.getRs(sqlGetMaxId);
				rs.absolute(-1);		//到最后一行，即　orderId　最大的一行
				if(rs.next())
					maxOrderId=Integer.parseInt(rs.getString("orderId"))+1;
				else
					maxOrderId=1;
				//写入订单细表 orderItem
				String sqlOrderItem=null;
				int bookTotal=Integer.parseInt(request.getParameter("bookTotal"));
				Double totalValue=Double.parseDouble("0");		//总价
				for(int i=0;i<bookTotal;i++)
				{
					//　access　一次只能插入一行数据
					sqlOrderItem="insert into orderItem(orderId,bookId,buyNum,bookSta,bookValue) values";
					sqlOrderItem+="("+maxOrderId+",";
					sqlOrderItem+=Integer.parseInt(request.getParameter("bookId-"+Integer.toString(i)))+",";
					sqlOrderItem+=Integer.parseInt(request.getParameter("buyNum-"+Integer.toString(i)))+",";
					sqlOrderItem+="'待审',";
					sqlOrderItem+=Double.parseDouble(request.getParameter("bookValue-"+Integer.toString(i)))+");";
					Conn conOrderItem=new Conn();
					conOrderItem.exeUpd(sqlOrderItem);
					conOrderItem.closeNoRs();
					//在 book 有中修改 书 售出数量
					Conn conBook=new Conn();
					conBook.exeUpd("update book set bookSold=bookSold+1 where bookId="+Integer.parseInt(request.getParameter("bookId-"+Integer.toString(i))));
					conBook.closeNoRs();
					totalValue+=Integer.parseInt(request.getParameter("bookId-"+Integer.toString(i)))*Double.parseDouble(request.getParameter("bookValue-"+Integer.toString(i)));
				}
				//写入订单总表 bookTotal
				String sqlOrderTotal="insert into orderTotal(orderId,userId,orderDate,checkDate,totalValue) values(";
				sqlOrderTotal+=maxOrderId+",";
				sqlOrderTotal+=Integer.parseInt(session.getAttribute("userId").toString())+",";
				sqlOrderTotal+="Date(),0,";
				sqlOrderTotal+=totalValue+");";
				out.print(sqlOrderTotal+"<br>");
				Conn conOrderTotal=new Conn();
				conOrderTotal.exeUpd(sqlOrderTotal);
				conOrderTotal.closeNoRs();
				session.setAttribute("orderId",maxOrderId);
				response.sendRedirect("tip.jsp?tip=check-all-ok&desPage=index-main.jsp");
		}//else if(act.equals("check-all"))
		else
		{
			String sOrderSession=null;
			String buyNum=null;
			int iBuyNumTem=0;
			String sSessionLeft=null;
			String sSessionRight=null;
			String bookId=appString(request.getParameter("bookId"),10);
			if(request.getParameter("buyNum")!=null)
			{
				buyNum=request.getParameter("buyNum");
				iBuyNumTem=Integer.parseInt(buyNum);
			}
			if(session.getAttribute("order")!=null)
				sOrderSession=appLongStr(session.getAttribute("order").toString(),10);
			else if(act.equals("add"))
				session.setAttribute("order","");
			if(act.equals("del"))
			{
				//将某书从购物车中移除
				if(sOrderSession.indexOf("-")==-1)
					sTem="";		//删除的是 session  order  中仅有的一个记录项
				else
				{
					sSessionLeft=sOrderSession.substring(0,sOrderSession.indexOf(bookId+","));
					sSessionRight=sOrderSession.substring(sOrderSession.indexOf(bookId+","));
					if((sSessionLeft.indexOf("-")==-1)&&(sSessionRight.indexOf("-")!=-1))
						sTem=sSessionRight.substring(sSessionRight.indexOf("-")+1);		//删除的是 session  order  中最左的记录项。<－左，右－>
					else if((sSessionLeft.indexOf("-")!=-1)&&(sSessionRight.indexOf("-")==-1))
						sTem=sSessionLeft.substring(0,sSessionLeft.lastIndexOf("-"));	//删除的是 session  order  中最右的记录项
					else
						sTem=sSessionLeft.substring(0,sSessionLeft.lastIndexOf("-"))+sSessionRight.substring(sSessionRight.indexOf("-"));	//删除的是 session  order  中间位置的记录项
				}
			}//if(act.equals("del")
			else if(act.equals("add"))
			{
				//将某书放入购物车		
				if(sOrderSession==null)
					sTem=bookId+","+buyNum;	//购物车之前为空
				else if(sOrderSession.indexOf(bookId+",")==-1)
					sTem=sOrderSession+"-"+bookId+","+buyNum;	//购物车中尚无此书
				else
				{
					//购物车已有此书
					sSessionLeft=sOrderSession.substring(0,sOrderSession.indexOf(bookId+","));
					sSessionRight=sOrderSession.substring(sOrderSession.indexOf(bookId+","));
					int iBuyNum;
					if((sSessionLeft.indexOf("-")==-1)&&(sSessionRight.indexOf("-")==-1))
					{
						//之前，此书是 session  order  中仅有的一个记录项
						iBuyNum=iBuyNumTem+Integer.parseInt(sSessionRight.substring(sSessionRight.indexOf(",")+1));
						sTem=bookId+","+Integer.toString(iBuyNum);
					}
					else if((sSessionLeft.indexOf("-")!=-1)&&(sSessionRight.indexOf("-")==-1))
					{
						//之前，此书是 session  order  中最右的一个记录项
						iBuyNum=iBuyNumTem+Integer.parseInt(sSessionRight.substring(sSessionRight.indexOf(",")+1));
						sTem=sSessionLeft.substring(0,sSessionLeft.lastIndexOf("-")+1)+bookId+","+Integer.toString(iBuyNum);
					}
					else if((sSessionLeft.indexOf("-")==-1)&&(sSessionRight.indexOf("-")!=-1))
					{
						//之前，此书是 session  order  中最左的一个记录项
						iBuyNum=iBuyNumTem+Integer.parseInt(sSessionRight.substring(sSessionRight.indexOf(",")+1,sSessionRight.indexOf("-")-1));
						sTem=bookId+","+Integer.toString(iBuyNum)+sSessionRight.substring(sSessionRight.indexOf("-"));
					}
					else
					{
						//之前，此书是 session  order  中间的一个记录项
						iBuyNum=iBuyNumTem+Integer.parseInt(sSessionRight.substring(sSessionRight.indexOf(",")+1,sSessionRight.indexOf("-")-1));
						sTem=sSessionLeft.substring(0,sSessionLeft.lastIndexOf("-")+1)+bookId+","+Integer.toString(iBuyNum)+sSessionRight.substring(sSessionRight.indexOf("-"));
					}
				}
			}//else if(act.equals("add"))
			else if(act.equals("changeNum"))
			{
				//改变购买某书的数量
				if(sOrderSession.indexOf("-")==-1)
					sTem=bookId+","+buyNum;		//修改的是 session  order  中仅有的一个记录项
				else
				{
					sSessionLeft=sOrderSession.substring(0,sOrderSession.indexOf(bookId+","));
					sSessionRight=sOrderSession.substring(sOrderSession.indexOf(bookId+","));
					if((sSessionLeft.indexOf("-")==-1)&&(sSessionRight.indexOf("-")!=-1))
						sTem=bookId+","+buyNum+sSessionRight.substring(sSessionRight.indexOf("-"));		//修改的是 session  order  中最左的记录项。
					else if((sSessionLeft.indexOf("-")!=-1)&&(sSessionRight.indexOf("-")==-1))
						sTem=sSessionLeft.substring(0,sSessionLeft.lastIndexOf("-")+1)+bookId+","+buyNum;	//修改的是 session  order  中最右的记录项
					else
						sTem=sSessionLeft.substring(0,sSessionLeft.lastIndexOf("-")+1)+bookId+","+buyNum+sSessionRight.substring(sSessionRight.indexOf("-"));	//修改的是 session  order  中间位置的记录项
				}
			}//else if(act.equals("changeNum"))
			if(sTem=="")
				session.removeAttribute("order");
			else
			{
				while(sTem.indexOf("*")!=-1)
					sTem=sTem.replace("*" , "");
				session.setAttribute("order",sTem);
			}
		}
		response.sendRedirect("show-order.jsp");
	}catch(Exception e) { }	
%>	