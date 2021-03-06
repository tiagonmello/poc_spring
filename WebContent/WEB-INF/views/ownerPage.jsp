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
    <title>Owner page</title>
</head>
<body>
<h1 style="text-align: center">
    Team owner page - Logged as <security:authentication property="principal.username" /> - Owner of Team ${teamId}
</h1>
<div class="columns">
    <div class="left">
        <fieldset>
            <legend>Add new members to your team</legend>
            <div>
                <form id="addMember" modelAttribute="user" name="addMember" method="post">
                    <table>
                        <tr>
                            <td align="right">Username:</td>
                            <td align="left"><input id="userName" type="text" name="userName" required></td>
                        </tr>
                        <tr>
                            <td align="right">Email:</td>
                            <td align="left"><input id="email" type="email" name="email" required></td>
                        </tr>
                        <tr>
                            <td align="right">Password:</td>
                            <td align="left"><input id="password" type="password" name="password" required></td>
                        </tr>
                        <tr>
                            <td align="right">Confirm password:</td>
                            <td align="left"><input id="matchingPassword" type="password" name="matchingPassword" required></td>
                        </tr>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <tr><td></td><td align="right"><button id="add" type="submit">Register</button></td></tr>
                    </table>
                </form>
            </div>
        </fieldset>
        <br>
        <fieldset>
            <legend>Team members</legend>
            <div>
                <table class="dataTable" >
                    <thead>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Delete</th>
                        <th>Edit</th>
                        <th>Shifts</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userList}" var="user">
                        </tr>
                        <td>${user.userName}</td>
                        <td>${user.email}</td>
                        <c:if test="${user.role.name == 'ROLE_MEMBER'}">
                            <form modelAttribute="user" action="/owner/deleteMember" name="deleteUser" method="post">
                                <td>
                                    <input type="hidden" value="${user.userName}" name="userName" />
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <button type="submit">Delete</button>
                                </td>
                            </form>
                            <form modelAttribute="user" action="/owner/editMember" name="editUser" method="post">
                                <td>
                                    <input type="hidden" value="${user.userName}" name="userName" />
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <button type="submit">Edit</button>
                                </td>
                            </form>
                            <form action="/owner/calendar/${user.userName}" method="get">
                                <td>
                                    <button type="submit">Calendar</button>
                                </td>
                            </form>
                        </c:if>
                        <c:if test="${user.role.name != 'ROLE_MEMBER'}">
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </fieldset>
    </div>
    <div class="right">
        <fieldset>
            <legend>Add a new team calendar</legend>
            <div>
                <form id="addCalendar" modelAttribute="teamCalendarDto" name="addCalendar" method="post">
                    <table>
                        <tr>
                            <td align="right">Start date:</td>
                            <td align="left"><input id="startDate" type="date" name="startDate" style="width: 140px" required></td>
                        </tr>
                        <tr>
                            <td align="right">End date:</td>
                            <td align="left"><input id="endDate" type="date" name="endDate" style="width: 140px" required></td>
                        </tr>
                        <tr>
                            <td align="right">People per day:</td>
                            <td align="left"><input id="peopleDay" type="number" name="dayLimit" style="width: 50px" required></td>
                        </tr>
                        <tr>
                            <td align="right">People per late:</td>
                            <td align="left"><input id="peopleLate" type="number" name="lateLimit" style="width: 50px" required></td>
                        </tr>
                        <tr>
                            <td align="right">People per special day:</td>
                            <td align="left"><input id="peopleSpecialDay" type="number" name="specialDayLimit" style="width: 50px" required></td>
                        </tr>
                        <tr>
                            <td align="right">People per special late:</td>
                            <td align="left"><input id="peopleSpecialLate" type="number" name="specialLateLimit" style="width: 50px" required></td>
                        </tr>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <tr><td></td><td align="right"><button id="saveCalendar" type="submit">Save</button></td></tr>
                    </table>
                </form>
            </div>
        </fieldset>
        <br>
        <fieldset>
            <legend>Add special days</legend>
            <div>
                <form id="addDay" modelAttribute="dayDto" name="addDay" method="post">
                    <table>
                        <tr>
                            <td align="right">Date:</td>
                            <td align="left"><input id="dayDate" type="date" name="dayDate" required></td>
                        </tr>
                        <tr>
                            <td align="right">Type:</td>
                            <td align="left"><input type="radio" name="dayType" value="holiday" required>Holiday</td>
                            <td align="left"><input type="radio" name="dayType" value="weekend" required>Weekend</td>
                        </tr>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <tr><td></td><td align="right"><button id="saveDay" type="submit">Save</button></td></tr>
                    </table>
                </form>
            </div>
        </fieldset>
        <br>
        <fieldset>
            <legend>Team calendars</legend>
            <div>
                <table class="dataTable" >
                    <thead>
                    <c:if test="${empty calendarList}">
                        <b>No calendar registered</b>
                    </c:if>
                    <c:if test="${not empty calendarList}">
                        <tr>
                            <th>ID</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>People/day</th>
                            <th>People/late</th>
                            <th>Manage</th>
                        </tr>
                    </c:if>
                    </thead>
                    <tbody>
                    <c:forEach items="${calendarList}" var="calendar">
                        </tr>
                        <td>${calendar.id}</td>
                        <td><fmt:formatDate value="${calendar.startDate}" pattern="dd-MM-yyyy"/></td>
                        <td><fmt:formatDate value="${calendar.endDate}" pattern="dd-MM-yyyy"/></td>
                        <td>${calendar.dayLimit}</td>
                        <td>${calendar.lateLimit}</td>
                        <form action="/owner/teamCalendar/${calendar.id}" method="get">
                            <td>
                                <button type="submit">Manage</button>
                            </td>
                        </form>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </fieldset>
    </div>
</div>
<div>
    <div style="float: left">
        <form action="/owner/calendarOverview" method="get">
            <button type="submit">Calendar overview</button>
        </form>
    </div>
    <div>
        &nbsp;
    </div>
    <div style="float: left">
        <form action="/owner/notifications" method="get">
            <button type="submit">Manage notifications</button>
        </form>
    </div>
    <div>
        &nbsp;
    </div>
    <div style="float: right;">
        <form action="/logout" method="get">
            <button type="submit">Logout</button>
        </form>
    </div>
</div>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/ownerPageScripts.js" />"></script>
</body>
</html>
