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
    <title>Edit Team Calendar</title>
</head>
<body>
<fieldset>
    <legend>Calendar ${teamCalendar.id}</legend>
    <div style="float: none">
        <b>Default people/day:</b> ${teamCalendar.dayLimit}
    </div>
    <div style="float: none">
        <b>Default people/late:</b> ${teamCalendar.lateLimit}
    </div>
    <div style="float: none">
        <b>Default people per special day:</b> ${teamCalendar.specialDayLimit}
    </div>
    <div style="float: none">
        <b>Default people per special late:</b> ${teamCalendar.specialLateLimit}
    </div>
    <br>
    <div>
        <table class="dataTable" >
            <thead>
            <tr>
                <th>Date</th>
                <th>People/Day</th>
                <th>People/Late</th>
                <th>Edit day</th>
                <th>Edit late</th>
                <th>Save</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${teamCalendar.days}" var="day">
                <tr>
                    <c:if test="${day.type eq 'HOLIDAY'}">
                        <td style="background-color: lightblue">
                            <fmt:formatDate value="${day.dayDate}" pattern="dd-MM-yyyy"/>
                            <br>Holiday
                        </td>
                    </c:if>
                    <c:if test="${day.type eq 'WEEKEND'}">
                        <td style="background-color: lightcoral">
                            <fmt:formatDate value="${day.dayDate}" pattern="dd-MM-yyyy"/>
                            <br>Weekend
                        </td>
                    </c:if>
                    <c:if test="${day.type eq 'NORMAL'}">
                        <td><fmt:formatDate value="${day.dayDate}" pattern="dd-MM-yyyy"/></td>
                    </c:if>
                    <td>
                        <c:if test="${day.type eq 'NORMAL'}">
                            <c:if test="${day.dayLimit == 0}">
                                <div>${day.currentDay}/${day.calendar.dayLimit}</div>
                            </c:if>
                            <c:if test="${day.dayLimit != 0}">
                                <div style="color: darkgoldenrod">${day.currentDay}/${day.dayLimit}</div>
                            </c:if>
                        </c:if>
                        <c:if test="${day.type ne 'NORMAL'}">
                            <div>${day.currentDay}/${day.calendar.specialDayLimit}</div>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${day.type eq 'NORMAL'}">
                            <c:if test="${day.lateLimit == 0}">
                                <div>${day.currentLate}/${day.calendar.lateLimit}</div>
                            </c:if>
                            <c:if test="${day.lateLimit != 0}">
                                <div style="color: darkgoldenrod">${day.currentLate}/${day.lateLimit}</div>
                            </c:if>
                        </c:if>
                        <c:if test="${day.type ne 'NORMAL'}">
                            <div>${day.currentLate}/${day.calendar.specialLateLimit}</div>
                        </c:if>
                    </td>
                    <form modelAttribute="day" action="/owner/editDay" name="editDay" method="post">
                        <td><input type="number" name="dayLimit" min="0" style="width: 50px" required/></td>
                        <td><input type="number" name="lateLimit" min="0" style="width: 50px" required/></td>
                        <td><button type="submit">Save</button></td>
                        <input type="hidden" value="${day.id}" name="id"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</fieldset>
<div>
    <form action="/owner/homepage" method="get">
        <button type="submit">Back</button>
    </form>
</div>
</body>
</html>
