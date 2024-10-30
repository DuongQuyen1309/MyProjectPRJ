<%-- 
    Document   : updateplan
    Created on : Oct 29, 2024, 9:13:04 AM
    Author     : Duong Minh Quyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../view/css/updatestyle.css" rel="stylesheet" type="text/css"/>
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
        <form action="update" method="POST">
            <input type="hidden" name="id" value="${requestScope.plan.pid}"/>
            Plan Name: ${requestScope.plan.pname} <br/>
            <input type="hidden" name="name" value="${requestScope.plan.pname}"/>
            From: ${requestScope.plan.start}
            <input type="hidden" name="from" value="${requestScope.plan.start}"/> <br/>
            To: <input type="date" name="to" value="${requestScope.plan.end}"/> <br/>
            Workshop: <select name="did">
                <c:forEach items="${requestScope.depts}" var="d">
                    <option ${requestScope.plan.dept.did eq d.did?"selected=\"selected\"":""} value="${d.did}">${d.dname}</option>
                </c:forEach>
            </select>
            <br/>
            Status:<select name="status">
                <option ${requestScope.plan.status eq 'Not start'?"selected=\"selected\"":""} value="Not start">Not start</option>
                <option ${requestScope.plan.status eq 'Pending'?"selected=\"selected\"":""} value="Pending">Pending</option>
                <option ${requestScope.plan.status eq 'Done'?"selected=\"selected\"":""} value="Done">Done</option>
            </select>
            Created by: ${sessionScope.account.displayname}
            <br/>

            <table border="1px">
                <tr>
                    <td>Product</td>
                    <td>Quantity</td>
                    <td>Estimated Effort</td>
                </tr>
                <c:forEach items="${requestScope.gplans}" var="gp">
                    <tr>
                        <td>${gp.product.prname}<input type="hidden" name="prid" value="${gp.product.prid}"></td>
                        <td><input type="text" name="quantity${gp.product.prid}" value="${gp.quantity}"/></td>
                        <td><input type="text" name="effort${gp.product.prid}" value="${gp.estimatedeffort}" /></td>
                    </tr>    
                </c:forEach>
            </table>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
