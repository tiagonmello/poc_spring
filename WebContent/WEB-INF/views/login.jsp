<%--
  Created by IntelliJ IDEA.
  User: I500747
  Date: 19/09/2018
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Login Page</title>
    <style>
        .error {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #a94442;
            background-color: #f2dede;
            border-color: #ebccd1;
        }

        .msg {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
            color: #31708f;
            background-color: #d9edf7;
            border-color: #bce8f1;
        }

        #login-box {
            width: 300px;
            padding: 20px;
            margin: 100px auto;
            background: #fff;
            -webkit-border-radius: 2px;
            -moz-border-radius: 2px;
            border: 1px solid #000;
        }
        body{
            background-color: aquamarine;
            font-family: Monospace;
        }
    </style>
</head>
<body onload='document.loginForm.username.focus();'>

<h1>Spring Security Login</h1>

<form action="/registrationForm" method="get">
    <input type="submit" value="Register here"/>
</form>

<div id="login-box">

    <h2>Username and Password</h2>

    <c:if test="${not empty errorMessage}">
        <div style="color:red">${errorMessage}</div>
        <br>
    </c:if>

    <form name='loginForm'
          action="<c:url value="/login" />" method='POST'>

        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='username' value=''></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit"
                                       value="submit" /></td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />

    </form>
</div>
</body>
</html>