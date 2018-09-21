<%--
  Created by IntelliJ IDEA.
  User: I500747
  Date: 19/09/2018
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>Access Denied</title>
</head>
<body>
<h2>Sorry, you do not have permission to view this page.</h2>

Click <a href="<c:url value="/homepage" /> ">here</a>
to go back to the Homepage.
</body>
</html>
