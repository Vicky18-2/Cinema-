<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 12.02.2021
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
|${minDate}|${maxDate}|<br>
<c:set var="min" value="${minDate}"></c:set>
<c:set var="max" value="${maxDate}"></c:set>
<h1>Create session for ${day}</h1>
<form method="post" action="createSession?day=${day}">
    <input type="datetime-local" min="${minDate}" value="${minDate}" max="${maxDate}" name="date_get" readonly>
    <select name="film_get" required>
        <c:forEach var="film" items="${films}">
            <option value="${film.id}">${film.name}</option>
        </c:forEach>
    </select>
    <h4>Virus restrictions <input type="checkbox" name="blocked" value="true"></h4>

    <input type="submit">
</form>
</body>
</html>
