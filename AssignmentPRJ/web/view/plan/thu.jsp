<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.humanresource.Employee, model.Planning.GeneralPlan" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Thu</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script>
            function checkQuantities(date, shift, gpid, requiredQuantity) {
                let totalAssigned = 0;

                // Sử dụng vòng lặp của JSP để duyệt qua từng phần tử `emp.eid` và tìm `id` tương ứng
            <c:forEach items="${requestScope.emps}" var="emp">
                // Tạo `id` động cho từng phần tử `input` với `emp.eid`
                const element = document.getElementById(`orderquantity${date}${shift}${gpid}${emp.eid}`);

                // Kiểm tra nếu phần tử tồn tại và có giá trị
                if (element && element.value) {
                    totalAssigned += parseFloat(element.value) || 0; // Chuyển đổi giá trị sang số và mặc định là 0 nếu không hợp lệ
                }
            </c:forEach>

                // Tính chênh lệch
                const difference = totalAssigned - requiredQuantity;

                // Kiểm tra chênh lệch và hiển thị thông báo
                if (difference < 0) {
                    alert("Missing " + (-difference));
                } else if (difference > 0) {
                    alert("Exceeding " + difference);
                } else {
                    alert("Enough");
                }
            }
        </script>
    </head>
    <body>
        <table border="1px">
            <thead>
                <tr>
                    <td colspan="3">Employee</td>
                    <c:forEach items="${requestScope.emps}" var="e">
                        <td>${e.ename}</td>
                    </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.daterange}" var="date">
                    <c:forEach items="${requestScope.shifts}" var="shift" varStatus="status1">
                        <c:forEach items="${requestScope.gplist}" var="generallist" varStatus="status">
                            <tr>
                                <c:if test="${status.index == 0}">
                                    <c:if test="${status1.index == 0}">
                                        <td rowspan="${requestScope.shifts.size() * requestScope.gplist.size()}">${date}</td>
                                    </c:if>
                                    <td rowspan="${requestScope.gplist.size()}">${shift.sname}</td>
                                </c:if>

                                <!-- Set resolvedQuantity outside the emps loop -->
                                <c:set var="resolvedQuantity" value="0" />
                                <c:forEach items="${requestScope.dpplist}" var="dpplist">
                                    <c:if test="${(dpplist.gpid eq generallist.gpid) && (dpplist.date eq date) && (dpplist.sid eq shift.sid)}">
                                        <c:set var="resolvedQuantity" value="${dpplist.quantity}" />
                                    </c:if>
                                </c:forEach>

                                <td>
                                    <input style="width:50px" name="proname" value="${generallist.product.prname}" readonly/>
                                    <input style="width:20px" name="detailproquantity" value="${resolvedQuantity}" readonly/>
                                </td>

                                <c:forEach items="${requestScope.emps}" var="e">
                                    <td>
                                        <input type="text"
                                               style="width:110px" 
                                               id="${date}a${shift.sid}a${generallist.gpid}a${e.eid}"
                                               name="orderquantity${date}${shift.sid}${generallist.gpid}${e.eid}"
                                               />
                                    </td>
                                </c:forEach>

                            </tr>
                        </c:forEach>
                    </c:forEach>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
