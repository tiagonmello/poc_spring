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
    <legend>${user.userName}'s allocated shifts:</legend>
    <div>
        <table class="dataTable" >
            <thead>
            <tr>
                <th>Date</th>
                <th>Shift</th>
                <th>Available?</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${eventList}" var="event">
                <tr>
                    <c:if test="${event.day.type eq 'HOLIDAY'}">
                        <td style="background-color: lightblue">
                            <fmt:formatDate value="${event.eventDate}" pattern="dd-MM-yyyy"/>
                            <br>Holiday
                        </td>
                    </c:if>
                    <c:if test="${event.day.type eq 'WEEKEND'}">
                        <td style="background-color: lightcoral">
                            <fmt:formatDate value="${event.eventDate}" pattern="dd-MM-yyyy"/>
                            <br>Weekend
                        </td>
                    </c:if>
                    <c:if test="${event.day.type eq 'NORMAL'}">
                        <td><fmt:formatDate value="${event.eventDate}" pattern="dd-MM-yyyy"/></td>
                    </c:if>
                    <td>
                        <c:if test="${event.shift eq 'ANY_DAY'}">
                            <div>Any (Day)</div>
                        </c:if>
                        <c:if test="${event.shift eq 'ANY_LATE'}">
                            <div>Any (Late)</div>
                        </c:if>
                        <c:if test="${event.shift eq 'DAY'}">
                            <div style="color: darkgoldenrod">Day</div>
                        </c:if>
                        <c:if test="${event.shift eq 'LATE'}">
                            <div style="color: darkblue">Late</div>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${event.dayAvailability}">
                            <div>Yes</div>
                        </c:if>
                        <c:if test="${not event.dayAvailability}">
                            <div style="color: red">No</div>
                        </c:if>
                    </td>
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
