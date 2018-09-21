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
<style>
    body{
        background-color: aquamarine;
        font-family: Monospace;
    }
    div{
        display: flex;
        justify-content: center;
    }
</style>
<html>
<body>
<h1>Registration form</h1>

<form:form method="post" modelAttribute="user" action="/register" name="user">
    <label for="userName">Username:</label>
    <input id="userName" type="text" name="userName">
    <label for="email">Email:</label>
    <input id="email" type="email" name="email">
    <label for="password">Password:</label>
    <input id="password" type="password" name="password">
    <label for="matchingPassword">Confirm password:</label>
    <input id="matchingPassword" type="password" name="matchingPassword">
    <button id="add" type="submit">Register</button>
</form:form>

<form action="/login" method="get">
    <input type="submit" value="Login"/>
</form>
</body>
</html>