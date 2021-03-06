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
<div class="columns">
    <div class="left">
        <fieldset>
            <legend><b>Available calendars</b></legend>
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
                        <tr>
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
            <legend><b>Shift allocation</b></legend>
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
                    <c:forEach items="${daysList}" var="day">
                        <tr>
                            <c:if test="${day.type eq 'HOLIDAY'}">
                                <td style="background-color: lightblue;">
                                    <fmt:formatDate value="${day.dayDate}" pattern="dd-MM-yyyy"/>
                                    <br>Holiday
                                </td>
                            </c:if>
                            <c:if test="${day.type eq 'WEEKEND'}">
                                <td style="background-color: lightcoral;">
                                    <fmt:formatDate value="${day.dayDate}" pattern="dd-MM-yyyy"/>
                                    <br>Weekend
                                </td>
                            </c:if>
                            <c:if test="${day.type eq 'NORMAL'}">
                                <td><fmt:formatDate value="${day.dayDate}" pattern="dd-MM-yyyy"/></td>
                            </c:if>
                            <form modelAttribute="event" action="/member/addEvent" name="addEvent" method="post">
                                <td>
                                    <select name="shift">
                                        <option value="any">Any</option>
                                        <option value="day">Day</option>
                                        <option value="late">Late</option>
                                    </select>
                                </td>

                                <c:if test="${day.type eq 'HOLIDAY' || day.type eq 'WEEKEND'}">
                                    <td><input type="checkbox" name="dayAvailability" value="checked"/></td>
                                </c:if>
                                <c:if test="${day.type eq 'NORMAL'}">
                                    <td><input type="checkbox" name="dayAvailability" value="checked" disabled checked/></td>
                                    <input type="hidden" name="dayAvailability" value="checked"/>
                                </c:if>

                                <td><button type="submit">Save</button></td>
                                <input type="hidden" value="<fmt:formatDate value="${day.dayDate}" pattern="dd-MM-yyyy"/>" name="eventDate" />
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
            <legend>Notifications</legend>
            <div>
                <table class="dataTable" >
                    <thead>
                    <c:if test="${empty textNotifications && empty shiftNotifications}">
                        <b>No notifications</b>
                    </c:if>
                    <c:if test="${not empty textNotifications || not empty shiftNotifications}">
                        <tr>
                            <th>Information</th>
                            <th>Action</th>
                        </tr>
                    </c:if>
                    </thead>
                    <tbody>
                    <c:forEach items="${textNotifications}" var="notification">
                        <tr>
                            <td>
                                ${notification.text}
                            </td>
                            <td></td>
                        </tr>
                    </c:forEach>
                    <c:forEach items="${shiftNotifications}" var="notification">
                        <tr>
                            <td>
                                <fmt:formatDate value="${notification.dayDate}" pattern="dd-MM-yyyy"/> - ${notification.shift}
                            </td>
                            <form modelAttribute="notificationAction" action="/member/notificationAction" name="addShift" method="post">
                                <td>
                                    <input type="hidden" value="<fmt:formatDate value="${notification.dayDate}" pattern="dd-MM-yyyy"/>" name="eventDate" />
                                    <input type="hidden" value="${notification.shift}" name="shift" />
                                    <input type="hidden" value="${notification.id}" name="notificationId" />
                                    <input type="hidden" value="checked" name="dayAvailability" />
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <button type="submit">Accept</button>
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </fieldset>
        <br>
        <fieldset>
            <legend>Current allocated shifts</legend>
            <div>
                <table class="dataTable" >
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Shift</th>
                        <th>Available?</th>
                        <th>Day</th>
                        <th>Late</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${eventList}" var="event">
                        <tr>
                            <c:if test="${event.day.type eq 'HOLIDAY'}">
                                <td style="background-color: lightblue">
                                    <fmt:formatDate value="${event.day.dayDate}" pattern="dd-MM-yyyy"/>
                                    <br>Holiday
                                </td>
                            </c:if>
                            <c:if test="${event.day.type eq 'WEEKEND'}">
                                <td style="background-color: lightcoral">
                                    <fmt:formatDate value="${event.day.dayDate}" pattern="dd-MM-yyyy"/>
                                    <br>Weekend
                                </td>
                            </c:if>
                            <c:if test="${event.day.type eq 'NORMAL'}">
                                <td><fmt:formatDate value="${event.day.dayDate}" pattern="dd-MM-yyyy"/></td>
                            </c:if>
                            <td>
                                <c:if test="${event.shift eq 'ANY_DAY'}">
                                    <div>Any - Day</div>
                                </c:if>
                                <c:if test="${event.shift eq 'ANY_LATE'}">
                                    <div>Any - Late</div>
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
                            <td>
                                <c:if test="${event.day.type eq 'NORMAL'}">
                                    <c:if test="${event.day.dayLimit == 0}">
                                        <div>${event.day.currentDay}/${event.day.calendar.dayLimit}</div>
                                    </c:if>
                                    <c:if test="${event.day.dayLimit != 0}">
                                        <div style="color: darkgoldenrod">${event.day.currentDay}/${event.day.dayLimit}</div>
                                    </c:if>
                                </c:if>
                                <c:if test="${event.day.type ne 'NORMAL'}">
                                    <div>${event.day.currentDay}/${event.day.calendar.specialDayLimit}</div>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${event.day.type eq 'NORMAL'}">
                                    <c:if test="${event.day.lateLimit == 0}">
                                        <div>${event.day.currentLate}/${event.day.calendar.lateLimit}</div>
                                    </c:if>
                                    <c:if test="${event.day.lateLimit != 0}">
                                        <div style="color: darkgoldenrod">${event.day.currentLate}/${event.day.lateLimit}</div>
                                    </c:if>
                                </c:if>
                                <c:if test="${event.day.type ne 'NORMAL'}">
                                    <div>${event.day.currentLate}/${event.day.calendar.specialLateLimit}</div>
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
