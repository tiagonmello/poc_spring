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
    <title>Homepage</title>
</head>
<body>
    <div>
        <h1>Users and Courses management - Logged as <security:authentication property="principal.username" /> </h1>
    </div>
    <div>
        <form action="/admin/editUsers" method="get">
            <input type="submit" value="Edit users"/>
        </form>
    </div>
    <div>
        <form action="/admin/editCourses" method="get">
            <input type="submit" value="Edit courses"/>
        </form>
    </div>
    <div>
        <form action="/user/showAllUsers" method="get">
            <input type="submit" value="Show users"/>
        </form>
    </div>
    <div>
        <form action="/user/showAllCourses" method="get">
            <input type="submit" value="Show courses"/>
        </form>
    </div>
    <div>
        <form action="/logout" method="get">
            <input type="submit" value="Logout"/>
        </form>
    </div>
</body>
</html>
