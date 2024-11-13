<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<form action="/registration" method="post">
    <fieldset>
        <div>
            <label>Login: <input type="text" name="login"/></label>
        </div>
        <c:forEach var="loginValidationErrorMessage" items="${loginValidationErrorMessages}">
            <p style="color: darkgreen">
                    ${loginValidationErrorMessage}
            </p>
        </c:forEach>
        <div>
            <label>Password: <input type="text" name="password"/></label>
        </div>
        <c:forEach var="passwordValidationErrorMessage" items="${passwordValidationErrorMessages}">
            <p style="color: darkgreen">
                    ${passwordValidationErrorMessage}
            </p>
        </c:forEach>
        <button type="submit">Register</button>
    </fieldset>
</form>
</body>
</html>

