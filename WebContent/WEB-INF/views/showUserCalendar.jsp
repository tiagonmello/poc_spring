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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
    <%@include file="/resources/css/template.css"%>
</style>
<html>
<head>
    <title>${user.userName}'s Calendar</title>
</head>
<body>
<fieldset>
    <legend>${user.userName}'s allocated shifts</legend>
    <div>
        <table class="dataTable" >
            <thead>
            <tr>
                <th>Date</th>
                <th>Day</th>
                <th>Late</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${eventList}" var="event">
                </tr>
                <td>
                    <fmt:formatDate value="${event.eventDate}" pattern="dd-MM-yyyy"/>
                </td>
                <td>
                    <c:if test="${event.dayShift}">
                        <div class="warning">X</div>
                    </c:if>
                </td>
                <td>
                    <c:if test="${event.lateShift}">
                        <div class="warning">X</div>
                    </c:if>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</fieldset>
<br>
<div>
    <form action="/owner/homepage" method="get">
        <button type="submit">Back</button>
    </form>
</div>
</body>
</html>
