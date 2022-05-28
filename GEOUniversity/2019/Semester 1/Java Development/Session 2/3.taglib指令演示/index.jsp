<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta content="text/html;charset=UTF-8" http-equiv="Content-Type">
    <title>JSP taglib指令演示</title>
  </head>
  <body>
       <table>
           <tr>
               <td>输出值</td>
           </tr>
           <c:forEach begin="1" end="10" var="num">
              <tr>
                 <td><c:out value="${num}"></c:out></td>
              </tr>
           </c:forEach>
       </table>
  </body>
</html>
