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
    <form id="addMember" modelAttribute="user" name="addMember" method="post">
        <label for="userName">Username:</label>
        <input id="userName" type="text" name="userName">
        <label for="email">Email:</label>
        <input id="email" type="email" name="email">
        <label for="password">Password:</label>
        <input id="password" type="password" name="password">
        <label for="matchingPassword">Confirm password:</label>
        <input id="matchingPassword" type="password" name="matchingPassword">
        <button id="add" type="submit">Register</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</div>
<div>
    <form action="/logout" method="get">
        <input type="submit" value="Logout"/>
    </form>
</div>
<div>
    <h1>Members of your team:</h1>
</div>
<div>
    <h1>
        <table style="width:100%" >
            <tr>
                <td><b>Username</b></td>
                <td><b>Password</b></td>
                <td><b>Email</b></td>
                <td><b>Role</b></td>
                <td><b>Delete</b></td>
                <td><b>Edit</b></td>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.userName}</td>
                    <td>${user.password}</td>
                    <td>${user.email}</td>
                    <td>${user.role.name}</td>
                        <c:if test="${user.role.name == 'ROLE_MEMBER'}">
                            <td>
                                <form modelAttribute="user" action="/owner/deleteMember" name="deleteUser" method="post">
                                    <input type="hidden" value="${user.userName}" name="userName" />
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <button type="submit">Delete</button>
                                </form>
                            </td>
                            <td>
                                <form modelAttribute="user" action="/owner/editMember" name="editUser" method="post">
                                    <input type="hidden" value="${user.userName}" name="userName" />
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <button type="submit">Edit</button>
                                </form>
                            </td>
                        </c:if>
                </tr>
            </c:forEach>
        </table>
    </h1>
</div>
<script
        src="http://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/ownerPageScripts.js" />"></script>
</body>
</html>
