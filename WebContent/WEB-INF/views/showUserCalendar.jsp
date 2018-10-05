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
    <div style="background-color: lightblue">Holiday</div>
    <div style="background-color:lightcoral">Weekend</div>
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
                <fmt:formatDate var="currentDate" value="${event.eventDate}" pattern="yyyy-MM-dd" />
                <c:set var="holiday" value=""/>
                <c:set var="weekend" value=""/>
                <c:forEach items="${specialDayList}" var="specialDay">
                    <fmt:formatDate var="specialDate" value="${specialDay.dayDate}" pattern="yyyy-MM-dd" />
                    <c:if test="${currentDate == specialDate && specialDay.dayType == 'holiday'}">
                        <c:set var="holiday" value="yes"/>
                    </c:if>
                    <c:if test="${currentDate == specialDate && specialDay.dayType == 'weekend'}">
                        <c:set var="weekend" value="yes"/>
                    </c:if>
                </c:forEach>

                <tr>
                    <c:if test="${holiday == 'yes'}">
                        <td style="background-color: lightblue">
                            <fmt:formatDate value="${event.eventDate}" pattern="dd-MM-yyyy"/>
                            <br>Holiday
                        </td>
                    </c:if>
                    <c:if test="${weekend == 'yes'}">
                        <td style="background-color: lightcoral">
                            <fmt:formatDate value="${event.eventDate}" pattern="dd-MM-yyyy"/>
                            <br>Weekend
                        </td>
                    </c:if>
                    <c:if test="${holiday != 'yes' && weekend != 'yes'}">
                        <td><fmt:formatDate value="${event.eventDate}" pattern="dd-MM-yyyy"/></td>
                    </c:if>
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
