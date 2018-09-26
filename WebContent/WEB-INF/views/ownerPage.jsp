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
        background-color: #8DC26F;
        font-family: Monospace;
    }
    div{
        display: flex;
        justify-content: center;
    }
    caption {
        font-weight: bold;
        font-size: 18px;
        text-align:left;
    }
    .dataTable td, .dataTable th {
        padding: .625em;
        line-height: 1.5em;
        border-bottom: 1px dashed #ccc;
        box-sizing: border-box;
        overflow-x: hidden;
        overflow-y: auto;
    }

    .dataTable th {
        text-align: left;
        background: rgba(0, 0, 0, 0.14);
        border-bottom: 1px dashed #aaa;
    }

    .dataTable tr:nth-child(odd) {
        background: rgba(0, 0, 0, 0.07);
    }

    .dataTable tr:nth-child(even) {
        background: rgba(255, 255, 255, 0.2);
    }

    .dataTable thead {
        display: table-header-group;
        float: none;
    }

    .dataTable tbody {
        display: table-row-group;
    }

    .dataTable thead tr, .dataTable tbody tr {
        display: table-row;
    }

    .dataTable th, .dataTable tbody td {
        display: table-cell;
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
    <form action="/logout" method="get">
        <input type="submit" value="Logout"/>
    </form>
</div>
<div>
    <form id="addMember" modelAttribute="user" name="addMember" method="post">
        <table>
            <caption>Add new members to your team:</caption>
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
<br>
<br>
<div>
        <table class="dataTable" >
            <caption>Team members:</caption>
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
<script
        src="http://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/ownerPageScripts.js" />"></script>
</body>
</html>
