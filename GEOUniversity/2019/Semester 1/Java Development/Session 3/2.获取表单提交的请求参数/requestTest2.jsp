<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>'getParameter.jsp'</title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	</head>
	<body>
		<p>这里是食品柜台，请选择您要购买的食品:
		<form action="" method="post" name="form1">
			<input type="checkbox" name="choice" value="香肠">香肠
			<input type="checkbox" name="choice" value="热狗">热狗
			<input type="checkbox" name="choice" value="烤鸭">烤鸭
			<input type="checkbox" name="choice" value="酸奶">酸奶
			<br>
			<input type="submit" value="提交" name="submit">
		</form>
		<%
			request.setCharacterEncoding("utf-8");
   	 		//获得所有name=choice的标签中被选中的
			String food[]=request.getParameterValues("choice");
			if(food!=null)
			{
				for(int a=0;a<food.length;a++)
				{
					out.print(food[a]);
					//out.print(new String(food[a].getBytes("ISO-8859-1"),"utf-8"));
				}
			}
		%>
	</body>
</html>