<%-- 
    Document   : inforplan
    Created on : Oct 24, 2024, 4:39:41 PM
    Author     : Duong Minh Quyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style><%@include file="/view/css/inforplan.css"%></style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../css/inforplan.css" rel="stylesheet" type="text/css"/>
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
                <td>Start Date</td>
                <td>End Date</td>
                <td>Product Name</td>
                <td>Completed Amount</td>
                <td>Remained Amount</td>
            </tr>
            <c:forEach items="${requestScope.vidu}" var="vd">
                <c:set var="rowspan" value="${vd.generalplan.size()}" />
                <c:forEach items="${vd.generalplan}" var="t" varStatus="status">
                    <tr>
                        <c:if test="${status.index == 0}">
                            <td rowspan="${rowspan}">${vd.pid}</td>
                            <td rowspan="${rowspan}">${vd.pname}</td>
                            <td rowspan="${rowspan}">${vd.start}</td>
                            <td rowspan="${rowspan}">${vd.end}</td>
                        </c:if>
                        <td>${t.product.prname}</td>
                        <td>${t.completedamount}</td>
                        <c:if test="${t.remainedamount < 0}"> 
                            <td>Complete and have leftover product</td>
                        </c:if>
                        <c:if test="${t.remainedamount >= 0}"> 
                            <td>${t.remainedamount}</td>
                        </c:if>                        
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
    </body>
</html>
