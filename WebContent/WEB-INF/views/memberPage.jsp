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
<div class="columns">
    <div class="left">

        <fieldset>
            <legend><b>Shift allocation:</b></legend>
            <div>
                <table class="dataTable" >
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Shift</th>
                        <th>Available?</th>
                        <th>Save</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${dateList}" var="date">
                        <fmt:formatDate var="currentDate" value="${date}" pattern="yyyy-MM-dd" />
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
                        </tr>
                        <c:if test="${holiday == 'yes'}">
                            <td style="background-color: lightblue;">
                                <fmt:formatDate value="${date}" pattern="dd-MM-yyyy"/>
                                <br>Holiday
                            </td>
                        </c:if>
                        <c:if test="${weekend == 'yes'}">
                            <td style="background-color: lightcoral;">
                                <fmt:formatDate value="${date}" pattern="dd-MM-yyyy"/>
                                <br>Weekend
                            </td>
                        </c:if>
                        <c:if test="${holiday != 'yes' && weekend != 'yes'}">
                            <td><fmt:formatDate value="${date}" pattern="dd-MM-yyyy"/></td>
                        </c:if>
                        <form modelAttribute="event" action="/member/addEvent" name="addEvent" method="post">
                            <td>
                                <select name="shift">
                                    <option value="any">Any</option>
                                    <option value="day">Day</option>
                                    <option value="late">Late</option>
                                </select>
                            </td>

                            <c:if test="${holiday == 'yes' || weekend == 'yes'}">
                                <td><input type="checkbox" name="dayAvailability" value="checked"/></td>
                            </c:if>
                            <c:if test="${holiday != 'yes' && weekend != 'yes'}">
                                <td><input type="checkbox" name="dayAvailability" value="checked" disabled checked/></td>
                                <input type="hidden" name="dayAvailability" value="checked"/>
                            </c:if>

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
    </div>
    <div class="right">
        <fieldset>
            <legend>Current allocated shifts:</legend>
            <div>
                <table class="dataTable" >
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Shift</th>
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
                                <c:if test="${event.shift eq 'ANY'}">
                                    <div class="warning">Any</div>
                                </c:if>
                                <c:if test="${event.shift eq 'DAY'}">
                                    <div class="warning">Day</div>
                                </c:if>
                                <c:if test="${event.shift eq 'LATE'}">
                                    <div class="warning">Late</div>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </fieldset>
    </div>
</div>
<br>
<div>
    <form action="/logout" method="get">
        <button type="submit">Logout</button>
    </form>
</div>
</body>
</html>
