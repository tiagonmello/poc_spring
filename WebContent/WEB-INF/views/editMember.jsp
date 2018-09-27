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
    <title>Edit ${user.userName}</title>
</head>
<body>
<div class="mainContainer">
    <div class="content">
        <fieldset>
            <legend><b>Enter new password and email for ${user.userName}:</b></legend>
            <div>
                <form:form id="register-form" modelAttribute="user" action="/owner/updateMember" name="editMember" method="post">
                    <table>
                        <tr>
                            <td align="right"><label for="password">Password:</label></td>
                            <td align="left"><input id="password" type="password" name="password" required></td>
                        </tr>
                        <tr>
                            <td align="right"><label for="matchingPassword">Confirm password:</label></td>
                            <td align="left"><input id="matchingPassword" type="password" name="matchingPassword" required></td>
                        </tr>
                        <tr>
                            <td align="right"><label for="email">Email:</label></td>
                            <td align="left"><input id="email" type="email" name="email" required></td>
                        </tr>
                        <input id="username" type="hidden" value="${user.userName}" name="userName">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <tr><td></td><td align="right"><button id="add" type="submit">Save</button></td></tr>
                    </table>
                </form:form>
            </div>
            <c:if test="${not empty updateMessage}">
                <div class="warning">${updateMessage}</div>
                <br>
            </c:if>
        </fieldset>
        <br>
        <br>
        <div>
            <form action="/owner/homepage" method="get">
                <input type="submit" value="Back"/>
            </form>
        </div>
    </div>
</div>
<script
        src="http://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/registrationScripts.js" />"></script>
</body>
</html>