<%--
  Created by IntelliJ IDEA.
  User: delores
  Date: 3/31/21
  Time: 11:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/hello">
        <table>
            <tr>
                <td>Username: </td> <td><label for="username"></label><input id="username"/></td>
                <td>Password: </td> <td><label for="password"></label><input id="password" type="password"></td>
                <td><img id="kaptcha" src="${pageContext.request.contextPath}/kaptcha.jpg" alt="kaptcha"></td>
            </tr>
        </table>
    </form>
</body>
</html>
