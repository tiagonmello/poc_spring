<%--
  Created by IntelliJ IDEA.
  User: I867174
  Date: 07/02/2018
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
<head>
    <title>Owner page</title>
</head>
<body>
<div>
    <h1>Team owner page - Logged as <security:authentication property="principal.username" /> </h1>
</div>
<div>
    <h1>You are the owner of Team ${teamId}</h1>
</div>
<div>
    <b>Add new members to your team:</b>
</div>
<div>
    <form:form id="addMember" modelAttribute="user" action="/owner/addMember" name="addMember" method="post">
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
</div>
<div>
    <form action="/logout" method="get">
        <input type="submit" value="Logout"/>
    </form>
</div>
<div>
    <h1>
        Username<br>
        <c:forEach items="${userList}" var="user">
            ${user.userName}<br>
        </c:forEach>
    </h1>
    <h1>
        &nbsp;
    </h1>
    <h1>
        Password<br>
        <c:forEach items="${userList}" var="user">
            ${user.password}<br>
        </c:forEach>
    </h1>
    <h1>
        &nbsp;
    </h1>
    <h1>
        Email<br>
        <c:forEach items="${userList}" var="user">
            ${user.email}<br>
        </c:forEach>
    </h1>
    <h1>
        &nbsp;
    </h1>
    <h1>
        Role<br>
        <c:forEach items="${userList}" var="user">
            ${user.role.name}<br>
        </c:forEach>
    </h1>
</div>
</body>
</html>
