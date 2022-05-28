<%@ page language="java" contentType="text/html; charset=gb2312"
    import="java.io.*,myPk.*,java.sql.*"
 %>
 <% request.setCharacterEncoding("gb2312");  %>
<jsp:useBean id="bUploadFile" class="myPk.FileUploadBean" scope="application" />
<%
	try
	{
	String sCurPath=request.getRealPath("/");
	for(int i=0;i<6;i++)
	{
		File fCurFile=new File(sCurPath);
		sCurPath=fCurFile.getParent();
	}
	sCurPath+="\\bookShop\\WebContent\\img\\book";
	bUploadFile.setUploadDirectory(sCurPath);
	bUploadFile.setFileName(session.getAttribute("userName").toString());
	bUploadFile.uploadFile(request);
	session.setAttribute("bookPhoto",bUploadFile.getFullFileName());
	response.sendRedirect("upload-file.jsp?uploadResult=ok");
	}catch(Exception e) { response.sendRedirect("upload-file.jsp?uploadResult=fail"); }
%>