<%--
  Created by IntelliJ IDEA.
  User: I500747
  Date: 19/09/2018
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login Page</title>
    <style>
        <%@include file="/resources/css/login.css"%>
    </style>
</head>
<body>
<div class="login-page">
    <h1>Team management login</h1>
    <div class="form">
        <form:form class="login-form" action="/login">
            <label for="username">Username:</label>
            <input id="username" type="text" placeholder="username" name="username" required/>
            <label for="password">Password:</label>
            <input id="password" type="password" placeholder="password" name="password" required/>
            <button type="submit" name="submit">login</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <p class="message">Not registered? <a href="/registrationForm">Create an account</a></p>
        </form:form>
        <c:if test="${not empty errorMessage}">
            <div class="warning">${errorMessage}</div>
        </c:if>
    </div>
</div>
</body>
</html>