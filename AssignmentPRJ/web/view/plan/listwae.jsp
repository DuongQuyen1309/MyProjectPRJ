<%-- 
    Document   : listwae
    Created on : Nov 3, 2024, 11:27:00 PM
    Author     : Duong Minh Quyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f7f7f7;
                color: #333;
                margin: 0;
                padding: 20px;
            }
            .table-container {
                width: 85%;
                height: 60%;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
                overflow-y: auto;
            }

            .unfixed-table {
                width: 100%;
                height: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
                overflow-y: auto;
            }

            .unfixed-table th, .unfixed-table td {
                padding: 12px;
                text-align: center;
                border: 1px solid #ddd;
            }

            .unfixed-table thead {
                background-color: #68d3dc;
                color: black;
            }

            .unfixed-table th {
                font-weight: bold;
                font-size: 16px;
                position: sticky;
                top: 0;
                z-index: 1;
            }

            .unfixed-table td {
                font-size: 14px;
                background-color: #f9f9f9;
            }

            .unfixed-table tr:nth-child(even) td {
                background-color: #f1f1f1;
            }

            .unfixed-table tr:hover td {
                background-color: #e0f7fa;
            }

            .table-container::-webkit-scrollbar {
                width: 8px;
            }

            .table-container::-webkit-scrollbar-thumb {
                background-color: #68d3dc;
                border-radius: 8px;
            }

            .table-container::-webkit-scrollbar-track {
                background-color: #f1f1f1;
            }

            input[type="submit"] {
                background-color: #68d3dc;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s ease;
            }

            input[type="submit"]:hover {
                background-color: #56b4b8;
            }
            #header {
                padding: 5px;
                text-align: center;
            }

            #tabbar {
                display: flex;
                justify-content: flex-end;
                padding-right: 10px;
            }

            #tabbar li {
                margin: 10px;
                padding: 5px;
                background-color: #68d3dc;
            }
            h1 {
                color: #333;
                font-family: cursive;
                font-size: 3em;
            }

            h2 {
                color: #666;
                font-style: italic;
                font-size: 15px;
            }

            div {
                margin-bottom: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            ul {
                list-style-type: none;
                padding: 0;
            }
            a {
                text-decoration: none;
                color: #68d3dc;
            }
            #maso{
                width:5%;
                padding:10px;
                color:white;
                background-color:#68d3dc;
            }
        </style>
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
                                                <td> 
                                                    <c:forEach items="${requestScope.waelist}" var="waelist">
                                                        <c:if test="${(dppid eq waelist.dppid) and (waelist.eid.eid eq e.eid)}">
                                                            ${waelist.orderquantity}
                                                        </c:if>
                                                    </c:forEach>   </td>
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
