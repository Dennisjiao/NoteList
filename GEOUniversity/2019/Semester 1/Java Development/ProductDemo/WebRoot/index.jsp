<%@ page  import="java.util.*,com.eshore.pojo.Product" pageEncoding="UTF-8"%>
<%@ page import="com.eshore.factory.DAOFactory" %>
<%
request.setCharacterEncoding("utf-8");//解决中文乱码
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
  </head>
  
 <body>
      <%
        Product product = new Product();	
		product.setProductId(request.getParameter("product_id"));
		product.setProductName(request.getParameter("product_name"));
		product.setPrice(Double.parseDouble(request.getParameter("price")));
		product.setInfo(request.getParameter("info"));
		boolean flag = DAOFactory.getIEmpDAOInstance().addProduct(product);	
		if(flag){
       %>
          <h4>添加产品信息失败</h4>
        <%
        }else{
         %>
           <h4>添加产品信息成功</h4>
         <%} %>
  </body>
</html>
