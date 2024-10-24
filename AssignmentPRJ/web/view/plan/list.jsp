<%-- 
    Document   : list
    Created on : Oct 22, 2024, 11:28:47 PM
    Author     : Duong Minh Quyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1px">
            <tr>
                <td>plan id</td>
                <td>plan name</td>
                <td>start</td>
                <td>end</td>
                <td>did</td>
                <td>status</td>
                <td>created by</td>
                <td>overview about plan</td>
            </tr>
            <c:forEach items="${requestScope.plans}" var="pl">
                <tr>
                    <td>${pl.pid}</td>
                    <td>${pl.pname}</td>
                    <td>${pl.start}</td>
                    <td>${pl.end}</td>
                    <td>${pl.dept.did}</td>
                    <td>${pl.status}</td>
                    <td>${pl.createdby.displayname}</td>
                    <td>
                        <a href="overview?id=${pl.pid}">Overview</a>
                    </td>
                </tr>
            </c:forEach>                   



    </body>
</html>
