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
    <%@include file="/resources/css/template.css"%>
</style>
<html>
<head>
    <title>Owner page</title>
</head>
<body>
<h1 style="text-align: center">
    Team owner page - Logged as <security:authentication property="principal.username" /> - Owner of Team ${teamId}
</h1>
<fieldset>
    <legend>Add new members to your team</legend>
    <div>
        <form id="addMember" modelAttribute="user" name="addMember" method="post">
            <table>
                <tr>
                    <td align="right">Username:</td>
                    <td align="left"><input id="userName" type="text" name="userName" required></td>
                </tr>
                <tr>
                    <td align="right">Email:</td>
                    <td align="left"><input id="email" type="email" name="email" required></td>
                </tr>
                <tr>
                    <td align="right">Password:</td>
                    <td align="left"><input id="password" type="password" name="password" required></td>
                </tr>
                <tr>
                    <td align="right">Confirm password:</td>
                    <td align="left"><input id="matchingPassword" type="password" name="matchingPassword" required></td>
                </tr>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <tr><td></td><td align="right"><button id="add" type="submit">Register</button></td></tr>
            </table>
        </form>
    </div>
</fieldset>
<br>
<br>
<fieldset>
    <legend>Team members</legend>
    <div>
        <table class="dataTable" >
            <thead>
            <tr>
                <th>Username</th>
                <th>Email</th>
                <th>Role</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="user">
                </tr>
                <td>${user.userName}</td>
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
                <c:if test="${user.role.name != 'ROLE_MEMBER'}">
                    <td></td>
                    <td></td>
                </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</fieldset>
<br>
<br>
<div>
    <form action="/logout" method="get">
        <input type="submit" value="Logout"/>
    </form>
</div>
<script
        src="http://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/ownerPageScripts.js" />"></script>
</body>
</html>
