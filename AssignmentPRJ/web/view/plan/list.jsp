<%-- 
    Document   : list
    Created on : Oct 22, 2024, 11:28:47 PM
    Author     : Duong Minh Quyen
--%>
<style><%@include file="/view/plan/liststyle.css"%></style>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="liststyle.css" rel="stylesheet" type="text/css"/>
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
                <td>detail plan </td>
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
                    <td>
                        <a href="../productionplan/detail/create?id=${pl.pid}">Detail</a>
                    </td>
                </tr>
            </c:forEach>                   



    </body>
</html>
