<%--
  Created by IntelliJ IDEA.
  User: delores
  Date: 4/1/21
  Time: 11:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Employees</title>
</head>
<body>
<table>
    <tr>
        <td>id</td>
        <td>lastName</td>
        <td>email</td>
        <td>gender</td>
    </tr>
    <c:forEach items="${emps}" var="emp">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.lastName}</td>
            <td>${emp.email}</td>
            <td>${emp.gender}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
