<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Register</h1>

    <form:form method="POST" modelAttribute="spitter">
      First Name: <form:input type="text" path="firstName" /><br/>
      Last Name: <form:input type="text" path="lastName" /><br/>
      Email: <form:input type="email" path="email" /><br/>
      Username: <form:input type="text" path="username" /><br/>
      <form:errors path="username" /><br/>
      Password: <form:input type="password" path="password" /><br/>
      <form:errors path="password" /><br/>
      <input type="submit" value="Register" />
    </form:form>
  </body>
</html>
