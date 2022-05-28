<%@ page language="java" contentType="text/html; charset=gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" type="text/css" href="blue.css">
<title>上传图片－－网上书店</title>
<script language="javascript">
<!--
function goBack()
{
	/*在父窗口中显示图片预览*/
	window.opener=null;
	window.close();
}
function showTip()
{
	window.opener.document.getElementById("bookPhoto").innerHTML=self.document.all.previewPhoto.innerHTML;
	document.getElementById("tip").innerText="正在上传……";
}
/*如果是上传的是图片文件，则进行预览*/
function viewPhoto()
{
	/*获取并显示文件大小*/
	var aoFile=new ActiveXObject("Scripting.FileSystemObject");
	var fUpload=aoFile.GetFile(document.uploadBookPhoto.filePath.value);
	var iSize=0;
	iSize+=parseInt(fUpload.size/1024);
	document.getElementById("fileSize").innerHTML=iSize+"  K";
 	//先取得扩展名
	var fileext=document.uploadBookPhoto.filePath.value.substring(document.uploadBookPhoto.filePath.value.lastIndexOf("."),document.uploadBookPhoto.filePath.value.length)
    fileext=fileext.toLowerCase()
    if ((fileext!='.jpg')&&(fileext!='.gif')&&(fileext!='.jpeg')&&(fileext!='.png')&&(fileext!='.bmp'))
    {
        document.uploadBookPhoto.filePath.focus();
        return;
    }
    else
    {
        //预览图片
    	document.getElementById("previewPhoto").innerHTML="<img src='"+document.uploadBookPhoto.filePath.value+"' padding:5px; width=180 height=240>";
     }
}
-->
</script>
</head>
<body>
<div id="frameThin">
<form id="uploadBookPhoto" name="uploadBookPhoto" method="post" enctype="multipart/form-data" action="save-book-photo.jsp?">
  <table width="100%"  border="0" cellspacing="5" cellpadding="0">
    <tr>
      <td><div align="center">
        <input id="filePath" name="filePath" type="file" size="80" onChange="viewPhoto()">
      </div></td>
      </tr>
    <tr>
      <td valign="top">
	  	<div id="previewPhoto"  style="width:120px; height:170px "></div>
		<div align="center"><br>
		    <br>
		    文件大小：
        </div>		<div id="fileSize"></div></td>
      </tr>
    <tr>
      <td><div id="tip" >
        <div align="center">
            <%
      	if(request.getParameter("uploadResult")!=null)
      	{
			if(request.getParameter("uploadResult").equals("ok"))
				out.print("上传图片成功！");
			else
				out.print("上传图片失败！");
      	}
		%>
        </div>
      </div></td>
    </tr>
    <tr>
      <td>        <table width="100%"  border="0" cellspacing="5" cellpadding="0">
          <tr>
            <td><div align="right">
              <input type="submit" id="uploadBookPhoto" name="uploadBookPhoto" value="上传" onClick="showTip()">
            </div></td>
            <td>&nbsp;</td>
            <td><input type="button" id="Input" name="Input"closethis"" value="返回" onClick="goBack()"></td>
          </tr>
        </table></td>
      </tr>
    <tr>
      <td></td>
      </tr>
    <tr>
      <td>&nbsp;</td>
      </tr>
    <tr>
      <td></td>
      </tr>
  </table>
</form>
</div>
</body>
</html>