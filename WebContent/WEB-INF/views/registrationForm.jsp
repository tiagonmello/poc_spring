<%--
  Created by IntelliJ IDEA.
  User: I500747
  Date: 19/09/2018
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<title>Registration Page</title>
<style>
    <%@include file="/resources/css/login.css"%>
</style>
</head>
<body>
<div class="login-page">
    <h1>Team owner registration</h1>
    <div class="form">
        <form:form id="register-form" class="register-form" method="post" modelAttribute="user" action="/register" name="user">
            <label for="username">Username:</label>
            <input id="username" type="text" placeholder="name" name="userName" required/>
            <label for="email">Email:</label>
            <input id="email" type="text" placeholder="email address" name="email" required/>
            <label for="password">Password:</label>
            <input id="password" type="password" placeholder="password" name="password" required/>
            <label for="matchingPassword">Confirm password:</label>
            <input id="matchingPassword" type="password" placeholder="confirm password" name="matchingPassword" required/>
            <button type="submit">create</button>
            <p class="message">Already registered? <a href="/login">Sign In</a></p>
        </form:form>
        <c:if test="${not empty registrationSuccess}">
            <div class="success">${registrationSuccess}</div>
        </c:if>
        <c:if test="${not empty registrationError}">
            <div class="warning">${registrationError}</div>
        </c:if>
    </div>
</div>
<script
        src="http://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/registrationScripts.js" />"></script>
</body>
</html>