<%-- 
    Document   : list
    Created on : Oct 22, 2024, 11:28:47 PM
    Author     : Duong Minh Quyen
--%>
<style><%@include file="/view/css/liststyle.css"%></style>
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
        <div id="header">
            <h1>ABC Company</h1>
            <h2>----Specializing in bamboo and rattan products----</h2>
        </div>
        <div>
            <ul id="tabbar">
                <li><a style="color: #f6f7f6" href="../view/headplanning">Home</a></li>
                <li style="color: #f6f7f6">${sessionScope.account.displayname}</li>
                <li><a style="color: #f6f7f6" href="../logout">Logout</a></li>
            </ul>
        </div>
        <table border="1px">
            <tr>
                <td>Plan ID</td>
                <td>Plan Name</td>
                <td>Start</td>
                <td>End</td>
                <td>Department ID</td>
                <td>Status</td>
                <td>Created By</td>
                <td>Overview About Plan</td>
                <td>Detail Plan </td>
                <td>Update Plan</td>
                <td>Work Assignment</td>
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
                    <td>
                        <a href="update?id=${pl.pid}">Update</a>
                    </td>
                    <td>
                        <a href="../view/listWAE?id=${pl.pid}">Work Assignment</a>
                    </td>
                </tr>

            </c:forEach>   
        </table>                
    </body>
</html>
