<%@ page language="java" contentType="text/html; charset=gb2312"%>
<% request.setCharacterEncoding("gb2312"); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>发表新帖－－网上书记</title>
<link href="blue.css" rel="stylesheet" type="text/css">
<script language="javascript">
<!--
/*模拟键盘按键“ctrl+z”即撤消操作*/
function goback()
{
	document.execCommand('Undo');
}
/*模拟键盘按键“ctrl+y”即重做操作*/
function goforward()
{
	document.execCommand('Redo');
}
/*获取textarea的焦点位置*/
function getTaPos()
{
	document.all.topicContent.curPos=document.selection.createRange().duplicate();
}
/* UBB编辑控制函数
 * 参数: tag 为标签名称　val 为标签参数
 * 返回：[标签名称=参数]修饰文字[/标签名称]
 *       [标签名称]修饰文字[/标签名称]
 *       [标签名称=参数][/标签名称]
 */
function ubbaction(tag, val)
{
 	var r = document.selection.createRange().text;
	var tag = tag.toUpperCase();
	if(typeof(val) == "undefined")
  		val = "";
  	/*字体大小*/
  	if(tag=="SIZE")
  	{
  		if(r=="")
  			return;
  		else
  			rr= "[" + tag + "=" + val + "]" + r + "[/" + tag +"]";
  	}
  	/*字体*/
  	else if(tag=="FONT-FAMILY")
  	{
  		if(r=="")
  			return;
  		else
  			rr="["+tag+"="+val + "]" + r + "[/" + tag +"]";
  	}
  	/*字体颜色*/
  	else if(tag=="COLOR")
  	{
  		if(r=="")
  			return;
  		else
  			rr="["+tag+"="+val + "]" + r + "[/" + tag +"]";
  	}
 	/*超链接*/
 	else if(tag == "URL")
 	{
  		val = prompt("请输入连接地址:(留空为选定地址)", "http://");
 		if((val ==  "http://")||( val == ""))
  			val = r;
  		if((val=="")||(val==null))
  			return;		//无输入且无选中文本时，或者用户单击“取消”，返回
  		else
			val = "=" + val; 
 		rr = "[" + tag + val + "]" + r + "[/" + tag +"]";
 	}
 	/*邮箱*/
 	else if(tag == "EMAIL")
 	{
  		val = prompt("请输入邮箱地址:(留空为选定地址)", "");
 		if(val == "")
  			val = r;
  		if((val=="")||(val==null))
  			return;		//无输入，且无选中文本时，返回
  		else
			val = "=" + val;
 		rr = "[" + tag + val + "]" + r + "[/" + tag +"]";
 	}
 	/*图片*/
 	else if(tag=="IMAGE")
 	{
 		val=prompt("请输入图片地址：","");
  		if((val=="")||(val==null))
  			return;		//无输入，且无选中文本时，返回
  		else
			val = "=" + val;
 		rr = "[" + tag + val + "]" ;
		if(document.all.topicContent.curPos)
			document.all.topicContent.curPos.text=rr;
		else
	 		document.all.topicContent.value+=rr;
 		return;
	}
 	/*FLASH*/
 	else if(tag=="FLASH")
 	{
 		val=prompt("请输入FLASH地址：","");
  		if((val=="")||(val==null))
  			return;		//无输入，且无选中文本时，返回
  		else
			val = "=" + val; 	
 		rr = "[" + tag + val + "]" ;
		if(document.all.topicContent.curPos)
			document.all.topicContent.curPos.text=rr;
		else
	 		document.all.topicContent.value+=rr;
 		return;
	}
 	/*插入水平条*/
	else if(tag=="LINE")
	{
 		rr="["+tag+"]";
		if(document.all.topicContent.curPos)
			document.all.topicContent.curPos.text=rr;
		else
	 		document.all.topicContent.value+=rr;
 		return;
  	}
  	/*插入文本处理标签，如加粗、下划线、倾斜，等等*/
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
/*将字符串　str　从　Ubb　转换为　Html　，并返回*/
function ubb2html(str)
{
		/*换行*/
		while(str.indexOf("\n")!=-1)
			str=str.substring(0,str.indexOf("\n"))+"<br>"+str.substring(str.indexOf("\n")+1);
		/*空格*/
		while(str.indexOf(" ")!=-1)
			str=str.substring(0,str.indexOf(" "))+"&nbsp"+str.substring(str.indexOf(" ")+1);
		/*加粗字体*/
		do
		{
			re=/(\[B\])([\s\S]+?)(\[\/B\])/gi;
			strOld=str;
 			str=str.replace(re,"<b>$2</b>");
 		}while(strOld!=str);
 		/*倾斜字体*/
		do
		{
			re=/\[I\]([\s\S]+?)\[\/I\]/gi;
			strOld=str;
 			str=str.replace(re,"<em>$1</em>");
 		}while(strOld!=str);
 		/*字体*/
		do
		{
			re=/\[FONT-FAMILY=([\s\S]+?)\]([\s\S]+?)\[\/FONT-FAMILY\]/gi;
			strOld=str;
 			str=str.replace(re,"<font face=$1>$2</font>");
 		}while(strOld!=str);
 		/*字体大小*/
		do
		{
			re=/\[SIZE=(\d{1}?)\]([\s\S]+?)\[\/SIZE\]/gi;
			strOld=str;
 			str=str.replace(re,"<font size=$1>$2</font>");
 		}while(strOld!=str);
 		/*字体颜色*/
		do
		{
			re=/\[COLOR=([\s\S]+?)\]([\s\S]+?)\[\/COLOR\]/gi;
			strOld=str;
 			str=str.replace(re,"<font color=$1>$2</font>");
 		}while(strOld!=str);
 		/*图片*/
		do
		{
			re=/\[IMAGE=([\s\S]+?)\]/gi;
			strOld=str;
 			str=str.replace(re,"<img src=$1></img>");
 		}while(strOld!=str);
 		/*动画*/
		do
		{
			re=/\[FLASH=([\s\S]+?)\]/gi;
			strOld=str;
 			str=str.replace(re,"<EMBED pluginspage=http://www.macromedia.com/go/getflashplayer src=$1 width=400 height=300 type=application/x-shockwave-flash wmode=transparent quality=high>");
 		}while(strOld!=str);
 		/*下划线*/
		do
		{
			re=/\[U\]([\s\S]+?)\[\/U\]/gi;
			strOld=str;
 			str=str.replace(re,"<u>$1</u>");
 		}while(strOld!=str);
 		/*左对齐*/
		do
		{
			re=/\[LEFT\]([\s\S]+?)\[\/LEFT\]/gi;
			strOld=str;
 			str=str.replace(re,"<div  align=left>$1</div>");
 		}while(strOld!=str);
 		/*居中对齐*/
		do
		{
			re=/\[CENTER\]([\s\S]+?)\[\/CENTER\]/gi;
			strOld=str;
 			str=str.replace(re,"<div  align=center>$1</div>");
 		}while(strOld!=str);
 			/*右对齐*/
		do
		{
			re=/\[RIGHT\]([\s\S]+?)\[\/RIGHT\]/gi;
			strOld=str;
 			str=str.replace(re,"<div  align=right>$1</div>");
 		}while(strOld!=str);
		/*插入水平条*/
		do
		{
			re=/\[LINE\]/gi;
			strOld=str;
 			str=str.replace(re,"<hr>");
 		}while(strOld!=str);
 		/*超链接*/
		do
		{
			re=/\[URL=([\s\S]+?)\]([\s\S]+?)\[\/URL\]/gi;
			strOld=str;
 			str=str.replace(re,"<a  href=$1  target=_blank>$2</a>");
 		}while(strOld!=str);
 		/*电子邮件链接*/
		do
		{
 			re=/\[EMAIL=([\s\S]+?)\]([\s\S]+?)\[\/EMAIL\]/gi;  
			strOld=str;
 			str=str.replace(re,"<a  href=''mailto:$1''>$2</a>");
 		}while(strOld!=str);
 		/*返回*/
 		return str;
}
/*弹出上传图片的窗口*/
function showUpload()
{
	childWindow=window.open("upload-file.jsp",width="400",height="300");
}
/*提交表单前，转换　Ubb　为　Html*/
function turnCode()
{
	document.getElementById("topicContent").value=ubb2html(document.getElementById("topicContent").value);
}
-->
</script>
</head>

<body>

<div align="center">
  <table width="788"  border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td><jsp:include page="head.jsp" flush="true"></jsp:include></td>
    </tr>
    <tr>
      <td>
	  <div id="frameThin">
	  <form id="addTopic" action="save-topic.jsp" method="post">
	  <table width="100%"  border="0" cellspacing="5" cellpadding="0">
        <tr>
          <td valign="top"><div align="right">标题：</div></td>
          <td align="left"><input type="text" id="topicTitle" name="topicTitle" size="98"></td>
        </tr>
        <tr>
          <td valign="top"><div align="right">内容：</div></td>
          <td align="left">
          <table width="100%">
            <tr background="img/bg.gif">
              <td colspan="2">
                <select name='ffont' onChange="ubbaction('FONT-FAMILY', this.options[this.selectedIndex].value); this.value = 0;">
                  <option value='0' selected>字体</option>
                  <option value='Arial' style='font-family:Arial'>Arial</option>
                  <option value='方正舒体' style='font-family:方正舒体'>方正舒体</option>
                  <option value='方正姚体' style='font-family:方正姚体'>方正姚体</option>
                  <option value='仿宋' style='font-family:仿宋'>仿宋</option>
                  <option value='华文彩云' style='font-family:华文彩云'>华文彩云</option>
                  <option value='华文仿宋' style='font-family:华文仿宋'>华文仿宋</option>
                  <option value='华文琥珀' style='font-family:华文琥珀'>华文琥珀</option>
                  <option value='华文楷体' style='font-family:华文楷体'>华文楷体</option>
                  <option value='华文隶书' style='font-family:华文隶书'>华文隶书</option>
                  <option value='华文宋体' style='font-family:华文宋体'>华文宋体</option>
                  <option value='华文细黑' style='font-family:华文细黑'>华文细黑</option>
                  <option value='华文新魏' style='font-family:华文新魏'>华文新魏</option>
                  <option value='华文行楷' style='font-family:华文行楷'>华文行楷</option>
                  <option value='华文中宋' style='font-family:华文中宋'>华文中宋</option>
                  <option value='隶书' style='font-family:隶书'>隶书</option>
                  <option value='宋体' style='font-family:宋体'>宋体</option>
                  <option value='微软雅黑' style='font-family:微软雅黑'>微软雅黑</option>
                  <option value='新宋体' style='font-family:新宋体'>新宋体</option>
                  <option value='幼圆' style='font-family:幼圆'>幼圆</option>
                </select>
                <select name='fsize' onChange="ubbaction('SIZE', this.options[this.selectedIndex].value); this.value = 0;">
                  <option value='0' selected>大小</option>
                  <option value='1'>1</option>
                  <option value='2'>2</option>
                  <option value='3'>3</option>
                  <option value='4'>4</option>
                  <option value='5'>5</option>
                  <option value='6'>6</option>
                  <option value='7'>7</option>
                </select>
                <select name='fcolor' onChange="ubbaction('COLOR', this.options[this.selectedIndex].value); this.value = 0;">
                  <option value='0' selected>颜色</option>
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
                <img src="img/brkspace.gif" height="15"> <a href="javascript:ubbaction('b')"><img src="img/BB_BOLD.GIF" border="0" alt="加粗文字"></a> <a href="javascript:ubbaction('i')"><img src="img/bb_italicize.gif" border="0" alt="倾斜文字"></a> <a href="javascript:ubbaction('u')"><img src="img/bb_underline.gif" border="0" alt="下划线"></a> <a href="javascript:ubbaction('left')"><img src="img/justifyleft.gif" alt="左对齐" border="0"></a> <a href="javascript:ubbaction('center')"><img src="img/justifycenter.gif" alt="居中对齐" border="0"></a> <a href="javascript:ubbaction('right')"><img src="img/justifyright.gif" alt="右对齐" border="0"></a> <img src="img/brkspace.gif" height="15"> <a href="javascript:ubbaction('line')"><img src="img/inserthorizontalrule.gif" alt="插入水平条" border="0"></a> <a href="javascript:ubbaction('image')"><img src="img/BB_IMAGE.GIF" border="0" alt="插入图片"></a> <a href="javascript:ubbaction('flash')"><img src="img/SWF.GIF" border="0" alt="插入Flash"></a> <img src="img/brkspace.gif" height="15"> <a href="javascript:ubbaction('url')"><img src="img/BB_URL.GIF" border="0" alt="超链接"></a> <a href="javascript:ubbaction('email')"><img src="img/BB_EMAIL.GIF" border="0" alt="邮箱"></a> <img src="img/brkspace.gif" height="15"> <a href="javascript:goback()"><img src="img/UNDO.GIF" border="0" alt="撤消"></a> <a href="javascript:goforward()"><img src="img/REDO.GIF" border="0" alt="重做"></a> </td>
            </tr>
            <tr>
              <td align="left" width="100%">
                <textarea name="topicContent" id="topicContent" cols="100" rows="15" onfocus="getTaPos()" onchange="getTaPos()"></textarea>
              </td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <td colspan="2"><div align="center">
		  	<input id="action" name="action" type="hidden" value="add-topic">
            <input type="submit" name="Submit" value="提交" onClick="turnCode()">
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
