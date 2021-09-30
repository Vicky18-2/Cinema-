<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 25.02.2021
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach var="order" items="${orders}">
<p>
   order id: ${order.id}<br>
    order date: ${order.date}<br>
    order price: ${order.total_price}<br>
    Tickets id: <c:forEach var="t" items="${order.cart}">
    ${t.id},
</c:forEach><br>
</p></c:forEach>
</body>
</html>
