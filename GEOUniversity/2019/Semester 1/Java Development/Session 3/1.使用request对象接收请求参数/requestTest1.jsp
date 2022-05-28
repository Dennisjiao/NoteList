<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Enumeration"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<body>
		这是一个表单
		<form method="post">
			文本1：<input type="text" name="text1"><BR>
			文本2：<input type="text" name="text2"><BR>
			文本3：<input type="text" name="text3"><BR>
			文本4：<input type="text" name="text4"><BR>
			<input type="submit" value="提交">
		</form>
		<%
   	 		Enumeration pNames = request.getParameterNames(); 
   	        while(pNames.hasMoreElements()){  
   	             String pName = (String)pNames.nextElement();  
   	             String pValue =  request.getParameter(pName);
   	             if(pValue.contains("%")){
   	             	out.println("请求含有非法字符！");
   	             	break;
   	             }
   			}			
	%>
	</body>
</html>