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
    <title>Member page</title>
</head>
<body>
<div>
    <h1>Team member page - Logged as <security:authentication property="principal.username" /> </h1>
</div>
<div>
    <h1>You are a member of Team ${user.team.id}</h1>
</div>
<div>
    <h1>The owner of your team is ${user.team.owner.userName}</h1>
</div>

<div>
    <b>This page is a work in progress...</b>
</div>
<div>
    <form action="/logout" method="get">
        <input type="submit" value="Logout"/>
    </form>
</div>
</body>
</html>
