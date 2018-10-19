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
    <title>Calendar overview</title>
</head>
<body>
<fieldset style="width: 1200px">
    <legend>Team Calendar Overview</legend>
    <div>
        <table class="dataTable">
            <thead>
            <tr>
                <th/>
                <c:forEach items="${eventList[0]}" var="event">
                    <c:if test="${event.day.type eq 'HOLIDAY'}">
                        <th style="background-color: lightblue">
                            <fmt:formatDate value="${event.day.dayDate}" pattern="dd-MM-yyyy"/>
                        </th>
                    </c:if>
                    <c:if test="${event.day.type eq 'WEEKEND'}">
                        <th style="background-color: lightcoral">
                            <fmt:formatDate value="${event.dayDate}" pattern="dd-MM-yyyy"/>
                        </th>
                    </c:if>
                    <c:if test="${event.day.type eq 'NORMAL'}">
                        <th><fmt:formatDate value="${event.day.dayDate}" pattern="dd-MM-yyyy"/></th>
                    </c:if>
                </c:forEach>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${eventList}" var="events">
                </tr>
                    <td>${events[0].user.userName}</td>
                    <c:forEach items="${events}" var="event">
                        <td>
                            <c:if test="${event.shift eq 'ANY_DAY' || event.shift eq 'DAY'}">
                                <div style="color: darkgoldenrod">Day</div>
                            </c:if>
                            <c:if test="${event.shift eq 'ANY_LATE' || event.shift eq 'LATE'}">
                                <div style="color: darkblue">Late</div>
                            </c:if>
                        </td>
                    </c:forEach>
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
<script src="http://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
<script src="<c:url value="/resources/js/ownerPageScripts.js" />"></script>
</body>
</html>
