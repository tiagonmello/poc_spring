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
    <title>Member page</title>
</head>
<body>
<div>
    <h1>Team member page - Logged as <security:authentication property="principal.username"/> - Member of Team ${user.team.id} - Your owner is ${user.team.owner.userName}</h1>
</div>
<fieldset>
    <legend><b>Available calendars:</b></legend>
    <div>
        <table class="dataTable" >
            <thead>
            <tr>
                <th>ID</th>
                <th>Start Date</th>
                <th>End Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${calendarList}" var="calendar">
                </tr>
                <td>${calendar.id}</td>
                <td><fmt:formatDate value="${calendar.startDate}" pattern="dd-MM-yyyy"/></td>
                <td><fmt:formatDate value="${calendar.endDate}" pattern="dd-MM-yyyy"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</fieldset>
<br>
<fieldset>
    <legend><b>Shift allocation:</b></legend>
    <div>
        <table class="dataTable" >
            <thead>
            <tr>
                <th>Date</th>
                <th>Day</th>
                <th>Late</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dateList}" var="date">
                </tr>
                    <td><fmt:formatDate value="${date}" pattern="dd-MM-yyyy"/></td>
                    <form modelAttribute="event" action="/member/addEvent" name="addEvent" method="post">
                        <td><input type="checkbox" name="dayShift" value="checked"></td>
                        <td><input type="checkbox" name="lateShift" value="checked"></td>
                        <td><button type="submit">Save</button></td>
                        <input type="hidden" value="<fmt:formatDate value="${date}" pattern="dd-MM-yyyy"/>" name="eventDate" />
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</fieldset>
<br>
<fieldset>
    <legend>Current allocated shifts:</legend>
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
    <form action="/logout" method="get">
        <button type="submit">Logout</button>
    </form>
</div>
</body>
</html>
