<%@ page language="java" contentType="text/html; charset=gb2312"
	import="java.sql.*,myPk.*"%>
<% request.setCharacterEncoding("gb2312"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>��̳�����������</title>
<link href="blue.css" rel="stylesheet" type="text/css">
<script language="javascript">
<!--
/*ҳ����ʾ*/
function showPage()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//��ǰҳ��
	var pageCount=parseInt(document.getElementById("pageCount").value);	//�ܵ�ҳ��
	if(pageCount<1)
	{
		document.getElementById("toFirst").innerHTML="<img src='img/toFirst-2.gif' width='25' height='25'>";
		document.getElementById("toPri").innerHTML="<img src='img/toLeftStep-2.gif' width='25' height='25'>";
		document.getElementById("toNext").innerHTML="<img src='img/toRightStep-2.gif' width='25' height='25'>";
		document.getElementById("toLast").innerHTML="<img src='img/toLast-2.gif' width='25' height='25'>";
		document.getElementById("go").innerHTML="<img src='img/go-2.gif' width='25' height='25'>";
	}
	else 
	{
		if(curPage==1)
		{
			document.getElementById("toFirst").innerHTML="<img src='img/toFirst-2.gif' width='25' height='25'>";
			document.getElementById("toPri").innerHTML="<img src='img/toLeftStep-2.gif' width='25' height='25'>";
			if(pageCount==1)
			{
				document.getElementById("toNext").innerHTML="<img src='img/toRightStep-2.gif' width='25' height='25'>";
				document.getElementById("toLast").innerHTML="<img src='img/toLast-2.gif' width='25' height='25'>";
				document.getElementById("go").innerHTML="<img src='img/go-2.gif' width='25' height='25'>";
			}
		}//if(curPage==1)
		else if(curPage==pageCount)
		{
			document.getElementById("toNext").innerHTML="<img src='img/toRightStep-2.gif' width='25' height='25'>";
			document.getElementById("toLast").innerHTML="<img src='img/toLast-2.gif' width='25' height='25'>";
			if(pageCount==1)
			{
				document.getElementById("toFirst").innerHTML="<img src='img/toFirst-2.gif' width='25' height='25'>";
				document.getElementById("toPri").innerHTML="<img src='img/toLeftStep-2.gif' width='25' height='25'>";
				document.getElementById("go").innerHTML="<img src='img/go-2.gif' width='25' height='25'>";
			}
		}//else if(curPage==pageCount)
	}
}
/*������һҳ*/
function toFirst()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//��ǰҳ��
	var pageCount=parseInt(document.getElementById("pageCount").value);	//�ܵ�ҳ��
	if((pageCount>1)&&(curPage!=1))
		document.location.href="admin-book.jsp?&pgDes=1";
}
/*������һҳ*/
function toPri()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//��ǰҳ��
	var pageCount=parseInt(document.getElementById("pageCount").value);	//�ܵ�ҳ��
	var pageTem=curPage-1;
	if((pageCount>1)&&(curPage!=1))
		document.location.href="admin-book.jsp?&pgDes="+pageTem;
}
/*������һҳ*/
function toNext()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//��ǰҳ��
	var pageCount=parseInt(document.getElementById("pageCount").value);	//�ܵ�ҳ��
	pageTem=curPage+1;
	if((pageCount>1)&&(curPage!=pageCount))
	document.location.href="admin-book.jsp?&pgDes="+pageTem;
}
/*�������һҳ*/
function toLast()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//��ǰҳ��
	var pageCount=parseInt(document.getElementById("pageCount").value);	//�ܵ�ҳ��
	pageTem=pageCount;
	if((pageCount>1)&&(curPage!=pageCount))
		document.location.href="admin-book.jsp?&pgDes="+pageTem;
}
/*����ָ��ҳ*/
function goIndex()
{
	var curPage=parseInt(document.getElementById("curPage").value);		//��ǰҳ��
	var pageCount=parseInt(document.getElementById("pageCount").value);	//�ܵ�ҳ��
	var toPage=parseInt(document.getElementById("goIndex").value);
	if((curPage!=toPage)&&(toPage>=1)&&(toPage<=pageCount))
		document.location.href="admin-book.jsp?&pgDes="+toPage;
}
/*ģ����̰�����ctrl+z������������*/
function goback()
{
	document.execCommand('Undo');
}
/*ģ����̰�����ctrl+y������������*/
function goforward()
{
	document.execCommand('Redo');
}
/*��ȡtextarea�Ľ���λ��*/
function getTaPos()
{
	document.all.followContent.curPos=document.selection.createRange().duplicate();
}
/* UBB�༭���ƺ���
 * ����: tag Ϊ��ǩ���ơ�val Ϊ��ǩ����
 * ���أ�[��ǩ����=����]��������[/��ǩ����]
 *       [��ǩ����]��������[/��ǩ����]
 *       [��ǩ����=����][/��ǩ����]
 */
function ubbaction(tag, val)
{
 	var r = document.selection.createRange().text;
	var tag = tag.toUpperCase();
	if(typeof(val) == "undefined")
  		val = "";
  	/*�����С*/
  	if(tag=="SIZE")
  	{
  		if(r=="")
  			return;
  		else
  			rr= "[" + tag + "=" + val + "]" + r + "[/" + tag +"]";
  	}
  	/*����*/
  	else if(tag=="FONT-FAMILY")
  	{
  		if(r=="")
  			return;
  		else
  			rr="["+tag+"="+val + "]" + r + "[/" + tag +"]";
  	}
  	/*������ɫ*/
  	else if(tag=="COLOR")
  	{
  		if(r=="")
  			return;
  		else
  			rr="["+tag+"="+val + "]" + r + "[/" + tag +"]";
  	}
 	/*������*/
 	else if(tag == "URL")
 	{
  		val = prompt("���������ӵ�ַ:(����Ϊѡ����ַ)", "http://");
 		if((val ==  "http://")||( val == ""))
  			val = r;
  		if((val=="")||(val==null))
  			return;		//����������ѡ���ı�ʱ�������û�������ȡ����������
  		else
			val = "=" + val; 
 		rr = "[" + tag + val + "]" + r + "[/" + tag +"]";
 	}
 	/*����*/
 	else if(tag == "EMAIL")
 	{
  		val = prompt("�����������ַ:(����Ϊѡ����ַ)", "");
 		if(val == "")
  			val = r;
  		if((val=="")||(val==null))
  			return;		//�����룬����ѡ���ı�ʱ������
  		else
			val = "=" + val;
 		rr = "[" + tag + val + "]" + r + "[/" + tag +"]";
 	}
 	/*ͼƬ*/
 	else if(tag=="IMAGE")
 	{
 		val=prompt("������ͼƬ��ַ��","");
  		if((val=="")||(val==null))
  			return;		//�����룬����ѡ���ı�ʱ������
  		else
			val = "=" + val;
 		rr = "[" + tag + val + "]" ;
		if(document.all.followContent.curPos)
			document.all.followContent.curPos.text=rr;
		else
	 		document.all.followContent.value+=rr;
 		return;
	}
 	/*FLASH*/
 	else if(tag=="FLASH")
 	{
 		val=prompt("������FLASH��ַ��","");
  		if((val=="")||(val==null))
  			return;		//�����룬����ѡ���ı�ʱ������
  		else
			val = "=" + val; 	
 		rr = "[" + tag + val + "]" ;
		if(document.all.followContent.curPos)
			document.all.followContent.curPos.text=rr;
		else
	 		document.all.followContent.value+=rr;
 		return;
	}
 	/*����ˮƽ��*/
	else if(tag=="LINE")
	{
 		rr="["+tag+"]";
		if(document.all.followContent.curPos)
			document.all.followContent.curPos.text=rr;
		else
	 		document.all.followContent.value+=rr;
 		return;
  	}
  	/*�����ı������ǩ����Ӵ֡��»��ߡ���б���ȵ�*/
  	else if((tag=="B")||(tag=="U")||(tag=="I")||(tag=="LEFT")||(tag=="CENTER")||(tag=="RIGHT")||(tag=="FONT")||(tag=="SIZE")||(tag=="COLOR"))
  	{
  		if(r=="")
  			return;
  		rr = "[" + tag + val + "]" + r + "[/" + tag +"]";
 	}
  	document.selection.createRange().text = rr;
  	document.selection.createRange().select();
  	return;
}
/*���ַ�����str���ӡ�Ubb��ת��Ϊ��Html����������*/
function ubb2html(str)
{
		/*����*/
		while(str.indexOf("\n")!=-1)
			str=str.substring(0,str.indexOf("\n"))+"<br>"+str.substring(str.indexOf("\n")+1);
		/*�ո�*/
		while(str.indexOf(" ")!=-1)
			str=str.substring(0,str.indexOf(" "))+"&nbsp"+str.substring(str.indexOf(" ")+1);
		/*�Ӵ�����*/
		do
		{
			re=/(\[B\])([\s\S]+?)(\[\/B\])/gi;
			strOld=str;
 			str=str.replace(re,"<b>$2</b>");
 		}while(strOld!=str);
 		/*��б����*/
		do
		{
			re=/\[I\]([\s\S]+?)\[\/I\]/gi;
			strOld=str;
 			str=str.replace(re,"<em>$1</em>");
 		}while(strOld!=str);
 		/*����*/
		do
		{
			re=/\[FONT-FAMILY=([\s\S]+?)\]([\s\S]+?)\[\/FONT-FAMILY\]/gi;
			strOld=str;
 			str=str.replace(re,"<font face=$1>$2</font>");
 		}while(strOld!=str);
 		/*�����С*/
		do
		{
			re=/\[SIZE=(\d{1}?)\]([\s\S]+?)\[\/SIZE\]/gi;
			strOld=str;
 			str=str.replace(re,"<font size=$1>$2</font>");
 		}while(strOld!=str);
 		/*������ɫ*/
		do
		{
			re=/\[COLOR=([\s\S]+?)\]([\s\S]+?)\[\/COLOR\]/gi;
			strOld=str;
 			str=str.replace(re,"<font color=$1>$2</font>");
 		}while(strOld!=str);
 		/*ͼƬ*/
		do
		{
			re=/\[IMAGE=([\s\S]+?)\]/gi;
			strOld=str;
 			str=str.replace(re,"<img src=$1></img>");
 		}while(strOld!=str);
 		/*����*/
		do
		{
			re=/\[FLASH=([\s\S]+?)\]/gi;
			strOld=str;
 			str=str.replace(re,"<EMBED pluginspage=http://www.macromedia.com/go/getflashplayer src=$1 width=400 height=300 type=application/x-shockwave-flash wmode=transparent quality=high>");
 		}while(strOld!=str);
 		/*�»���*/
		do
		{
			re=/\[U\]([\s\S]+?)\[\/U\]/gi;
			strOld=str;
 			str=str.replace(re,"<u>$1</u>");
 		}while(strOld!=str);
 		/*�����*/
		do
		{
			re=/\[LEFT\]([\s\S]+?)\[\/LEFT\]/gi;
			strOld=str;
 			str=str.replace(re,"<div  align=left>$1</div>");
 		}while(strOld!=str);
 		/*���ж���*/
		do
		{
			re=/\[CENTER\]([\s\S]+?)\[\/CENTER\]/gi;
			strOld=str;
 			str=str.replace(re,"<div  align=center>$1</div>");
 		}while(strOld!=str);
 			/*�Ҷ���*/
		do
		{
			re=/\[RIGHT\]([\s\S]+?)\[\/RIGHT\]/gi;
			strOld=str;
 			str=str.replace(re,"<div  align=right>$1</div>");
 		}while(strOld!=str);
		/*����ˮƽ��*/
		do
		{
			re=/\[LINE\]/gi;
			strOld=str;
 			str=str.replace(re,"<hr>");
 		}while(strOld!=str);
 		/*������*/
		do
		{
			re=/\[URL=([\s\S]+?)\]([\s\S]+?)\[\/URL\]/gi;
			strOld=str;
 			str=str.replace(re,"<a  href=$1  target=_blank>$2</a>");
 		}while(strOld!=str);
 		/*�����ʼ�����*/
		do
		{
 			re=/\[EMAIL=([\s\S]+?)\]([\s\S]+?)\[\/EMAIL\]/gi;  
			strOld=str;
 			str=str.replace(re,"<a  href=''mailto:$1''>$2</a>");
 		}while(strOld!=str);
 		/*����*/
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
	document.getElementById("followContent").value=ubb2html(document.getElementById("followContent").value);
}
-->
</script>
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
</head>

<body onLoad="showPage()">
  <%
  		int pgDes=1;
		int pgTotal=0;
  		int topicId=0;
		int userId=0;
		String userName="";
		String topicDate="";
		String topicContent="";
		String topicTitle="";
		String followTotal="";
		if(request.getParameter("topicId")!=null)
			topicId=Integer.parseInt(request.getParameter("topicId"));
		if(request.getParameter("desPage")!=null)
			pgDes=Integer.parseInt(request.getParameter("pgDes"));
		try
		{
			Conn conTopic=new Conn();
			Pageable rsTopic=conTopic.getRs("select * from topic where topicId="+topicId);
			if(rsTopic.next())
			{
				userId=Integer.parseInt(rsTopic.getString("userId"));
				userName=rsTopic.getString("userName");
				topicDate=rsTopic.getString("topicDate").substring(0,10);
				topicContent=rsTopic.getString("topicContent");
				topicTitle=rsTopic.getString("topicTitle");
				followTotal=rsTopic.getString("followTotal");
			}
			conTopic.closeRs();
		}catch(Exception e) { out.print(e.toString()); }
  %>
<div align="center">
  <table width="788"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><jsp:include page="head.jsp" flush="true"></jsp:include></td>
    </tr>
    <tr>
      <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><div align="right"></div></td>
        </tr>
        <tr>
          <td><table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td valign="top">			  <div id="frameThin">
			    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td valign="top">
			 <%
			  		try
					{
						Conn conTUser=new Conn();
						Pageable rsTUser=conTUser.getRs("select * from user where userId="+userId);
						if(rsTUser.next())
						{
			  %>			 
			  <div id="frameThin">
			  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td bgcolor="#CCCCCC"><div align="right"></div>                    
                    <div align="center"><%=rsTUser.getString("userName") %></div></td>
                  </tr>
                <tr>
                  <td> <div align="left">�Ա�<%=rsTUser.getString("userSex") %>&nbsp;&nbsp;���䣺<%=rsTUser.getString("userAge") %></div></td>
                  </tr>
                <tr>
                  <td><div align="left">�־�ס�أ�<%=rsTUser.getString("userAddress") %></div></td>
                  </tr>
                <tr>
                  <td><div align="left">���᣺ <%=rsTUser.getString("userHome") %></div></td>
                  </tr>
              </table>
			  </div>
			  <%
			  				}
							conTUser.closeRs();
						}catch(Exception e) { out.print(e.toString());  }
			  %>			  </td>
                    <td valign="top">
					<div id="frameThin">
					<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td colspan="3" bgcolor="#CCCCCC"><div align="left"><span class="style1">����</span>���⣺<strong><%=topicTitle %></strong></div></td>
                      </tr>
                      <tr>
                        <td width="50%">&nbsp;</td>
                        <td>�����ڣ�<%=topicDate %></td>
                        <td>�ظ���<%=followTotal %></td>
                      </tr>
                      <tr>
                        <td colspan="3">
							<div id="blankLine3Px"></div>
							<div id="brokenLine"></div>
							<div id="blankLine3Px"></div>
                            <div align="left"><strong><%=topicContent %></strong></div></td>
                      </tr>
                    </table>
					</div>
					</td>
                  </tr>
                </table>
			    </div></td>
            </tr>
          </table>
		  <div id="blankLine3Px"></div>
		  <div id="brokenLine"></div>
		  <div id="blankLine3Px"></div>
		  </td>
        </tr>
        <tr>
          <td>
		  <%
		  		try
				{
					Conn conTF=new Conn();
					Pageable rsTF=conTF.getRs("select * from topic_follow where topicId="+topicId);
					rsTF.setPageSize(10);
					rsTF.gotoPage(pgDes);
					pgTotal=rsTF.getPageCount();
					for(int i=0;i<rsTF.getPageRowsCount();i++)
					{
		  %>
		  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td valign="top">
                <div id="frameThin">
                  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td valign="top">
						<%
						try
						{
							Conn conFUser=new Conn();
							Pageable rsFUser=conFUser.getRs("select * from user where userId="+Integer.parseInt(rsTF.getString("followUserId")));
							if(rsFUser.next())
							{
				  		%>
						<div id="frameThin">
                        <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td bgcolor="#CCCCCC"><div align="right"></div>                              <div align="center"><%=rsFUser.getString("userName") %></div></td>
                            </tr>
                          <tr>
                            <td>
                              <div align="left">�Ա�<%=rsFUser.getString("userSex") %>&nbsp;&nbsp;���䣺<%=rsFUser.getString("userAge") %></div></td>
                            </tr>
                          <tr>
                            <td><div align="left">�־�ס�أ�<%=rsFUser.getString("userAddress") %></div></td>
                            </tr>
                          <tr>
                            <td><div align="left">���᣺<%=rsFUser.getString("userHome") %></div></td>
                            </tr>
                        </table>
						</div>
                        <%
			  				}
							conFUser.closeRs();
						}catch(Exception e) { out.print(e.toString());  }
			  			%>
                      </td>
                      <td valign="top">
					  <%
					  	try
						{
							Conn conFollow=new Conn();
							Pageable rsFollow=conFollow.getRs("select * from follow where followId="+Integer.parseInt(rsTF.getString("followId")));
							if(rsFollow.next())
							{
					%>
					<div id="frameThin">
					  <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                          <tr>
                            <td colspan="2" bgcolor="#CCCCCC"><div align="left"><span class="style1">�ظ�</span>���⣺<%=rsFollow.getString("followTitle") %></div></td>
                          </tr>
                          <tr>
                            <td width="50%">&nbsp;</td>
                            <td>�����ڣ�<%=rsFollow.getString("followDate").substring(0,10) %></td>
                            </tr>
                          <tr>
                            <td colspan="2">
								<div id="blankLine3Px"></div>
								<div id="brokenLine"></div>
								<div id="blankLine3Px"></div>
                                <div align="left"><%=rsFollow.getString("followContent") %></div></td>
                          </tr>
                      </table>
					  </div>
					  <%
					  			}//if(rsFollow.next())
							}catch(Exception e) { out.print(e.toString());  }
					  %>
					  </td>
                    </tr>
                  </table>
              </div></td>
            </tr>
          </table>
		  <%
		  			rsTF.next();
					}//for(int i=0;i<rsTF.getPageRowsCount();i++)
				}catch(Exception e) { }
		  %>
		  </td>
        </tr>
        <tr>
          <td><div align="right">
            <table  border="0" cellpadding="0" cellspacing="3">
              <tr>
                <td width="25" height="25">
                  <button id="toFirst" name="toFirst" onClick="toFirst()"><img src="img/toFirst.gif"></button></td>
                <td width="25" height="25">
                  <button id="toPri" name="toPri" onClick="toPri()"><img src="img/toLeftStep.gif" width="25" height="25"></button></td>
                <td height="25">��<%=pgDes %>ҳ��</td>
                <td height="25">��<%=pgTotal %>ҳ</td>
                <td width="25" height="25">
                  <button id="toNext" name="toNext" onClick="toNext()"><img src="img/toRightStep.gif" width="25" height="25"></button></td>
                <td width="25" height="25">
                  <button id="toLast" name="toLast" onClick="toLast()"><img src="img/toLast.gif" width="25" height="25"></button></td>
                <td height="25">
                  <input id="curPage" name="curPage" type="hidden" value="<%=pgDes %>" >
                  <input id="pageCount" name="pageCount" type="hidden" value="<%=pgTotal %>" >
&nbsp;</td>
                <td height="25">
                  <input name="goIndex" type="text" id="goIndex" size="5" value="<%=pgDes %>"></td>
                <td width="25" height="25">
                  <button id="go" name="go" onClick="goIndex()"><img src="img/go.gif" width="25" height="25"></button></td>
              </tr>
            </table>
          </div></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td>
	  	<div id="blankLine6Px"></div>
	  	<div id="brokenLine"></div>
	  	<div id="blankLine6Px"></div>
	  	<div id="frameThin">
		<form id="addFollow" action="save-topic.jsp" method="post">
        <table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="2"><div align="left"><strong>����ظ�</strong></div></td>
            </tr>
          <tr>
            <td><div align="right">���⣺</div></td>
            <td><div align="left">
              <input name="followTitle" type="text" id="followTitle" size="98">
            </div></td>
          </tr>
          <tr>
            <td valign="top"><div align="right">���ݣ�</div></td>
            <td><table width="100%">
              <tr background="img/bg.gif">
                <td colspan="2" valign="middle">
                  <div align="left">
                    <select name='ffont' onChange="ubbaction('FONT-FAMILY', this.options[this.selectedIndex].value); this.value = 0;">
                        <option value='0' selected>����</option>
                        <option value='Arial' style='font-family:Arial'>Arial</option>
                        <option value='��������' style='font-family:��������'>��������</option>
                        <option value='����Ҧ��' style='font-family:����Ҧ��'>����Ҧ��</option>
                        <option value='����' style='font-family:����'>����</option>
                        <option value='���Ĳ���' style='font-family:���Ĳ���'>���Ĳ���</option>
                        <option value='���ķ���' style='font-family:���ķ���'>���ķ���</option>
                        <option value='��������' style='font-family:��������'>��������</option>
                        <option value='���Ŀ���' style='font-family:���Ŀ���'>���Ŀ���</option>
                        <option value='��������' style='font-family:��������'>��������</option>
                        <option value='��������' style='font-family:��������'>��������</option>
                        <option value='����ϸ��' style='font-family:����ϸ��'>����ϸ��</option>
                        <option value='������κ' style='font-family:������κ'>������κ</option>
                        <option value='�����п�' style='font-family:�����п�'>�����п�</option>
                        <option value='��������' style='font-family:��������'>��������</option>
                        <option value='����' style='font-family:����'>����</option>
                        <option value='����' style='font-family:����'>����</option>
                        <option value='΢���ź�' style='font-family:΢���ź�'>΢���ź�</option>
                        <option value='������' style='font-family:������'>������</option>
                        <option value='��Բ' style='font-family:��Բ'>��Բ</option>
                    </select>
                    <select name='fsize' onChange="ubbaction('SIZE', this.options[this.selectedIndex].value); this.value = 0;">
                        <option value='0' selected>��С</option>
                        <option value='1'>1</option>
                        <option value='2'>2</option>
                        <option value='3'>3</option>
                        <option value='4'>4</option>
                        <option value='5'>5</option>
                        <option value='6'>6</option>
                        <option value='7'>7</option>
                    </select>
                    <select name='fcolor' onChange="ubbaction('COLOR', this.options[this.selectedIndex].value); this.value = 0;">
                        <option value='0' selected>��ɫ</option>
                        <option value=black style="background-color:black;color:black">Black</option>
                        <option value=red style="background-color:red;color:red">Red</option>
                        <option value=yellow style="background-color:yellow;color:yellow">Yellow</option>
                        <option value=pink style="background-color:pink;color:pink">Pink</option>
                        <option value=green style="background-color:green;color:green">Green</option>
                        <option value=orange style="background-color:orange;color:orange">Orange</option>
                        <option value=purple style="background-color:purple;color:purple">Purple</option>
                        <option value=blue style="background-color:blue;color:blue">Blue</option>
                        <option value=beige style="background-color:beige;color:beige">Beige</option>
                        <option value=brown style="background-color:brown;color:brown">Brown</option>
                        <option value=teal style="background-color:teal;color:teal">Teal</option>
                        <option value=navy style="background-color:navy;color:navy">Navy</option>
                        <option value=maroon style="background-color:maroon;color:maroon">Maroon</option>
                        <option value=limegreen style="background-color:limegreen;color:limegreen">LimeGreen</option>
                    </select>
                    <img src="img/brkspace.gif" height="15"> <a href="javascript:ubbaction('b')"><img src="img/BB_BOLD.GIF" border="0" alt="�Ӵ�����"></a> <a href="javascript:ubbaction('i')"><img src="img/bb_italicize.gif" border="0" alt="��б����"></a> <a href="javascript:ubbaction('u')"><img src="img/bb_underline.gif" border="0" alt="�»���"></a> <a href="javascript:ubbaction('left')"><img src="img/justifyleft.gif" alt="�����" border="0"></a> <a href="javascript:ubbaction('center')"><img src="img/justifycenter.gif" alt="���ж���" border="0"></a> <a href="javascript:ubbaction('right')"><img src="img/justifyright.gif" alt="�Ҷ���" border="0"></a> <img src="img/brkspace.gif" height="15"> <a href="javascript:ubbaction('line')"><img src="img/inserthorizontalrule.gif" alt="����ˮƽ��" border="0"></a> <a href="javascript:ubbaction('image')"><img src="img/BB_IMAGE.GIF" border="0" alt="����ͼƬ"></a> <a href="javascript:ubbaction('flash')"><img src="img/SWF.GIF" border="0" alt="����Flash"></a> <img src="img/brkspace.gif" height="15"> <a href="javascript:ubbaction('url')"><img src="img/BB_URL.GIF" border="0" alt="������"></a> <a href="javascript:ubbaction('email')"><img src="img/BB_EMAIL.GIF" border="0" alt="����"></a> <img src="img/brkspace.gif" height="15"> <a href="javascript:goback()"><img src="img/UNDO.GIF" border="0" alt="����"></a> <a href="javascript:goforward()"><img src="img/REDO.GIF" border="0" alt="����"></a> </div></td>
              </tr>
              <tr>
                <td align="left" width="100%">
                  <textarea name="followContent" id="followContent" cols="100" rows="15" onfocus="getTaPos()" onchange="getTaPos()"></textarea>
                </td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td colspan="2"><div align="center">
				<input id="action" name="action" type="hidden" value="add-follow">
				<input id="topicId" name="topicId" type="hidden" value="<%=topicId %>">
              <input type="submit" name="Submit" value="����ظ�" onClick="turnCode()">
            </div></td>
          </tr>
        </table>
		</form>
		</div>
	  </td>
    </tr>
    <tr>
      <td><jsp:include page="bottom.jsp" flush="true"></jsp:include></td>
    </tr>
  </table>
</div>
</body>
</html>
