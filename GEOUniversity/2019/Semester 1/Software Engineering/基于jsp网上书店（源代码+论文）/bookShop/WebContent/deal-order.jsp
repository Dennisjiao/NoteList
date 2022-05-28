<%@ page language="java" contentType="text/html; charset=gb2312"  import="java.sql.*,myPk.*" %>
<% request.setCharacterEncoding("gb2312"); %>

<%!
	/*������ַ�����str���ĳ���С�ڡ�n�����������������㹻�ġ�*���ţ�ʹ�䳤��Ϊ��n��*/
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
	/*������str���ԡ�-��Ϊ��־�ֶΣ��ڸ�С�������ԡ�,����Ϊ���Σ���Ϊ��a�Ͷ�b������ÿ����a�������䡡*���ţ�ʹÿ����a�ĳ��Ⱦ�Ϊ��n��*/
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
		response.sendRedirect("tip.jsp?tip=use-bag-fail&desPage="+request.getParameter("curUrl"));			//�û�δ��¼
	try
	{
		String act="";
		if(request.getParameter("action")!=null)
			act=request.getParameter("action");
		String sTem="";
		if(act.equals("cancel-all"))
		{
			//��չ��ﳵ
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
				rs.absolute(-1);		//�����һ�У�����orderId������һ��
				if(rs.next())
					maxOrderId=Integer.parseInt(rs.getString("orderId"))+1;
				else
					maxOrderId=1;
				//д�붩��ϸ�� orderItem
				String sqlOrderItem=null;
				int bookTotal=Integer.parseInt(request.getParameter("bookTotal"));
				Double totalValue=Double.parseDouble("0");		//�ܼ�
				for(int i=0;i<bookTotal;i++)
				{
					//��access��һ��ֻ�ܲ���һ������
					sqlOrderItem="insert into orderItem(orderId,bookId,buyNum,bookSta,bookValue) values";
					sqlOrderItem+="("+maxOrderId+",";
					sqlOrderItem+=Integer.parseInt(request.getParameter("bookId-"+Integer.toString(i)))+",";
					sqlOrderItem+=Integer.parseInt(request.getParameter("buyNum-"+Integer.toString(i)))+",";
					sqlOrderItem+="'����',";
					sqlOrderItem+=Double.parseDouble(request.getParameter("bookValue-"+Integer.toString(i)))+");";
					Conn conOrderItem=new Conn();
					conOrderItem.exeUpd(sqlOrderItem);
					conOrderItem.closeNoRs();
					//�� book �����޸� �� �۳�����
					Conn conBook=new Conn();
					conBook.exeUpd("update book set bookSold=bookSold+1 where bookId="+Integer.parseInt(request.getParameter("bookId-"+Integer.toString(i))));
					conBook.closeNoRs();
					totalValue+=Integer.parseInt(request.getParameter("bookId-"+Integer.toString(i)))*Double.parseDouble(request.getParameter("bookValue-"+Integer.toString(i)));
				}
				//д�붩���ܱ� bookTotal
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
				//��ĳ��ӹ��ﳵ���Ƴ�
				if(sOrderSession.indexOf("-")==-1)
					sTem="";		//ɾ������ session  order  �н��е�һ����¼��
				else
				{
					sSessionLeft=sOrderSession.substring(0,sOrderSession.indexOf(bookId+","));
					sSessionRight=sOrderSession.substring(sOrderSession.indexOf(bookId+","));
					if((sSessionLeft.indexOf("-")==-1)&&(sSessionRight.indexOf("-")!=-1))
						sTem=sSessionRight.substring(sSessionRight.indexOf("-")+1);		//ɾ������ session  order  ������ļ�¼�<�����ң�>
					else if((sSessionLeft.indexOf("-")!=-1)&&(sSessionRight.indexOf("-")==-1))
						sTem=sSessionLeft.substring(0,sSessionLeft.lastIndexOf("-"));	//ɾ������ session  order  �����ҵļ�¼��
					else
						sTem=sSessionLeft.substring(0,sSessionLeft.lastIndexOf("-"))+sSessionRight.substring(sSessionRight.indexOf("-"));	//ɾ������ session  order  �м�λ�õļ�¼��
				}
			}//if(act.equals("del")
			else if(act.equals("add"))
			{
				//��ĳ����빺�ﳵ		
				if(sOrderSession==null)
					sTem=bookId+","+buyNum;	//���ﳵ֮ǰΪ��
				else if(sOrderSession.indexOf(bookId+",")==-1)
					sTem=sOrderSession+"-"+bookId+","+buyNum;	//���ﳵ�����޴���
				else
				{
					//���ﳵ���д���
					sSessionLeft=sOrderSession.substring(0,sOrderSession.indexOf(bookId+","));
					sSessionRight=sOrderSession.substring(sOrderSession.indexOf(bookId+","));
					int iBuyNum;
					if((sSessionLeft.indexOf("-")==-1)&&(sSessionRight.indexOf("-")==-1))
					{
						//֮ǰ�������� session  order  �н��е�һ����¼��
						iBuyNum=iBuyNumTem+Integer.parseInt(sSessionRight.substring(sSessionRight.indexOf(",")+1));
						sTem=bookId+","+Integer.toString(iBuyNum);
					}
					else if((sSessionLeft.indexOf("-")!=-1)&&(sSessionRight.indexOf("-")==-1))
					{
						//֮ǰ�������� session  order  �����ҵ�һ����¼��
						iBuyNum=iBuyNumTem+Integer.parseInt(sSessionRight.substring(sSessionRight.indexOf(",")+1));
						sTem=sSessionLeft.substring(0,sSessionLeft.lastIndexOf("-")+1)+bookId+","+Integer.toString(iBuyNum);
					}
					else if((sSessionLeft.indexOf("-")==-1)&&(sSessionRight.indexOf("-")!=-1))
					{
						//֮ǰ�������� session  order  �������һ����¼��
						iBuyNum=iBuyNumTem+Integer.parseInt(sSessionRight.substring(sSessionRight.indexOf(",")+1,sSessionRight.indexOf("-")-1));
						sTem=bookId+","+Integer.toString(iBuyNum)+sSessionRight.substring(sSessionRight.indexOf("-"));
					}
					else
					{
						//֮ǰ�������� session  order  �м��һ����¼��
						iBuyNum=iBuyNumTem+Integer.parseInt(sSessionRight.substring(sSessionRight.indexOf(",")+1,sSessionRight.indexOf("-")-1));
						sTem=sSessionLeft.substring(0,sSessionLeft.lastIndexOf("-")+1)+bookId+","+Integer.toString(iBuyNum)+sSessionRight.substring(sSessionRight.indexOf("-"));
					}
				}
			}//else if(act.equals("add"))
			else if(act.equals("changeNum"))
			{
				//�ı乺��ĳ�������
				if(sOrderSession.indexOf("-")==-1)
					sTem=bookId+","+buyNum;		//�޸ĵ��� session  order  �н��е�һ����¼��
				else
				{
					sSessionLeft=sOrderSession.substring(0,sOrderSession.indexOf(bookId+","));
					sSessionRight=sOrderSession.substring(sOrderSession.indexOf(bookId+","));
					if((sSessionLeft.indexOf("-")==-1)&&(sSessionRight.indexOf("-")!=-1))
						sTem=bookId+","+buyNum+sSessionRight.substring(sSessionRight.indexOf("-"));		//�޸ĵ��� session  order  ������ļ�¼�
					else if((sSessionLeft.indexOf("-")!=-1)&&(sSessionRight.indexOf("-")==-1))
						sTem=sSessionLeft.substring(0,sSessionLeft.lastIndexOf("-")+1)+bookId+","+buyNum;	//�޸ĵ��� session  order  �����ҵļ�¼��
					else
						sTem=sSessionLeft.substring(0,sSessionLeft.lastIndexOf("-")+1)+bookId+","+buyNum+sSessionRight.substring(sSessionRight.indexOf("-"));	//�޸ĵ��� session  order  �м�λ�õļ�¼��
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