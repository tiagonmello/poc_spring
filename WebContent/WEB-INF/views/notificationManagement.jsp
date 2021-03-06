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
    <title>Notification manager</title>
</head>
<body>
<div>
    <h1>Notification management - Team ${team.id}</h1>
</div>
<div class="columns">
    <div class="left">
        <fieldset>
            <legend>Send text notification:</legend>
            <div>
                <form modelAttribute="textNote" name="textNote" action="/owner/addTextNote" method="post">
                    <table>
                        <tr>
                            <td align="right">Text:</td>
                            <td align="left"><input type="text" name="text" required></td>
                        </tr>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <tr><td></td><td align="right"><button type="submit">Send</button></td></tr>
                    </table>
                </form>
            </div>
        </fieldset>
        <br>
        <fieldset>
            <legend>Active text notifications:</legend>
            <div>
                <table class="dataTable" >
                    <thead>
                    <c:if test="${empty textNotifications}">
                        <b>No notifications</b>
                    </c:if>
                    <c:if test="${not empty textNotifications}">
                        <tr>
                            <th>Notifications</th>
                            <th>Delete</th>
                        </tr>
                    </c:if>
                    </thead>
                    <tbody>
                    <c:forEach items="${textNotifications}" var="notification">
                        <tr>
                            <td>
                                ${notification.text}
                            </td>
                            <form modelAttribute="textNote" action="/owner/deleteNotification" name="deleteNote" method="post">
                                <td>
                                    <input type="hidden" value="${notification.id}" name="id" />
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <button type="submit">X</button>
                                </td>
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
            <legend>Send shift notification:</legend>
            <div>
                <form modelAttribute="shiftNote" name="shiftNote" action="/owner/addShiftNote" method="post">
                    <table>
                        <tr>
                            <td align="right">Date:</td>
                            <td align="left"><input type="date" name="eventDate" required></td>
                        </tr>
                        <tr>
                            <td align="right">Shift:</td>
                            <td align="left"><input type="radio" name="shift" value="day" required>Day</td>
                            <td align="left"><input type="radio" name="shift" value="late" required>Late</td>
                        </tr>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <tr><td></td><td align="right"><button type="submit">Send</button></td></tr>
                    </table>
                </form>
            </div>
        </fieldset>
        <br>
        <fieldset>
            <legend>Active shift notifications:</legend>
            <div>
                <table class="dataTable" >
                    <thead>
                    <c:if test="${empty shiftNotifications}">
                        <b>No notifications</b>
                    </c:if>
                    <c:if test="${not empty shiftNotifications}">
                        <tr>
                            <th>Date</th>
                            <th>Shift</th>
                            <th>Delete</th>
                        </tr>
                    </c:if>
                    </thead>
                    <tbody>
                    <c:forEach items="${shiftNotifications}" var="notification">
                        <tr>
                            <td><fmt:formatDate value="${notification.dayDate}" pattern="dd-MM-yyyy"/></td>
                            <td>
                                <c:if test="${notification.shift eq 'DAY'}">
                                    <div style="color: darkgoldenrod">Day</div>
                                </c:if>
                                <c:if test="${notification.shift eq 'LATE'}">
                                    <div style="color: darkblue">Late</div>
                                </c:if>
                            </td>
                            <form modelAttribute="textNote" action="/owner/deleteNotification" name="deleteNote" method="post">
                                <td>
                                    <input type="hidden" value="${notification.id}" name="id" />
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <button type="submit">X</button>
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
</div>
<div>
    <form action="/owner/homepage" method="get">
        <button type="submit">Back</button>
    </form>
</div>
</body>
</html>
