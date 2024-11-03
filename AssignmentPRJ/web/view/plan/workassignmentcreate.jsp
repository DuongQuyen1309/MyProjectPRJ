<%-- 
    Document   : workassignmentcreate
    Created on : Nov 1, 2024, 9:32:06 PM
    Author     : Duong Minh Quyen
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.humanresource.Employee, model.Planning.GeneralPlan" %>
<style><%@include file="../css/waestyle.css"%></style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Work Assignment Entry</title>
        <link href="../css/waestyle.css" rel="stylesheet" type="text/css"/>
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
                <li><a style="color: #f6f7f6" href="../../logout">Logout</a></li>
            </ul>
        </div>
        <div id="maso">
            PLAN ID: ${requestScope.planid}
        </div>        
        <form action="create" method="POST">
            <div class="table-container">
                <div class="table-horizontal-container">
                    <table class="unfixed-table" border="1px">
                        <thead>
                        <input type="hidden" name="planid" value="${requestScope.planid}"/>
                        <td colspan="3">Employee</td>
                        <c:forEach items="${requestScope.emps}" var="e">
                            <td>${e.ename}</td>
                        </c:forEach>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.daterange}" var="date">               
                                <c:forEach items="${requestScope.shifts}" var="shift" varStatus="status1">
                                    <c:forEach items="${requestScope.gplist}" var="generallist" varStatus="status">
                                        <tr>
                                            <c:if test="${status.index == 0}">
                                                <c:if test="${status1.index == 0}">
                                                    <td style="width:50px"rowspan="${requestScope.shifts.size()*requestScope.gplist.size()}">${date}</td>
                                                    <!--                                    // identify date-->
                                                </c:if>
                                                <td rowspan="${requestScope.gplist.size()}">${shift.sname}</td> 
                                                <!--                                //identify shift-->
                                            </c:if>
                                            <!--                                //identify general plan-->
                                            <c:set var="dppid" value="0" />
                                            <td>
                                                <input style="width:50px;text-align: center;" name="proname" value="${generallist.product.prname}"readonly/>
                                                <input style="width:50px;text-align: center;" name="detailproquantity" <c:forEach items="${requestScope.dpplist}" var="dpplist">
                                                           <c:if test="${(dpplist.gpid eq generallist.gpid) and (dpplist.date eq date) and (dpplist.sid eq shift.sid)}">
                                                               value="${dpplist.quantity}"
                                                               <c:set var="dppid" value="${dpplist.dppid}" />
                                                           </c:if>
                                                       </c:forEach> readonly/>
                                            </td>
                                            <c:forEach items="${requestScope.emps}" var="e">
                                                <td><input style="width:110px" name="orderquantity${date}a${shift.sid}a${generallist.gpid}a${e.eid}" 
                                                           <c:forEach items="${requestScope.waelist}" var="waelist">
                                                               <c:if test="${(dppid eq waelist.dppid) and (waelist.eid.eid eq e.eid)}">
                                                                   value="${waelist.orderquantity}"
                                                               </c:if>
                                                           </c:forEach>   /></td>
                                                </c:forEach>   
                                        </tr>
                                    </c:forEach>
                                </c:forEach>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <input type="submit" value="SAVE"/>
        </form>
    </body>
</html>