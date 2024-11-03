<%-- 
    Document   : workassignmentcreate
    Created on : Nov 1, 2024, 9:32:06 PM
    Author     : Duong Minh Quyen
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.humanresource.Employee, model.Planning.GeneralPlan" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Work Assignment Entry</title>

    </head>
    <body>
        <form action="create" method="POST">
            <table border="1px">
                <thead>
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
                                            <td rowspan="${requestScope.shifts.size()*requestScope.gplist.size()}">${date}</td>
                                            <!--                                    // identify date-->
                                        </c:if>
                                        <td rowspan="${requestScope.gplist.size()}">${shift.sname}</td> 
                                        <!--                                //identify shift-->
                                    </c:if>
                                    <!--                                //identify general plan-->
                                    <td>
                                        <input style="width:50px" name="proname" value="${generallist.product.prname}"readonly/>
                                        <input style="width:20px" name="detailproquantity" <c:forEach items="${requestScope.dpplist}" var="dpplist">
                                                   <c:if test="${(dpplist.gpid eq generallist.gpid) and (dpplist.date eq date) and (dpplist.sid eq shift.sid)}">
                                                       value="${dpplist.quantity}"
                                                       <c:set var="dppid" value="${dpplist.dppid}" />
                                                   </c:if>
                                               </c:forEach> readonly/>
                                    </td>
                                    <c:forEach items="${requestScope.emps}" var="e">
                                        <td><input style="width:110px" name="orderquantity${date}${shift.sid}${generallist.gpid}${e.eid}" 
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
            <input type="submit" value="SAVE"/>
        </form>
    </body>
</html>