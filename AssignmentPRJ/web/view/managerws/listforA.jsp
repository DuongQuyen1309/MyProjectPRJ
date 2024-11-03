<%-- 
    Document   : listforA
    Created on : Nov 3, 2024, 10:55:52 PM
    Author     : Duong Minh Quyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MANAGER A</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f2f5;
                color: #333;
                margin: 0;
                padding: 20px;
                height: 100vh;
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
            table {
                width: 90%;
                margin: 40px auto;
                border-collapse: collapse;
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
                overflow: hidden;
            }

            table, th, td {
                border: 1px solid #ddd;
            }

            th, td {
                padding: 15px;
                text-align: center;
                font-size: 16px;
            }

            th {
                background-color: #007bff;
                color: white;
                font-weight: bold;
            }

            td {
                background-color: #f9f9f9;
            }

            td a {
                color: #007bff;
                text-decoration: none;
                transition: color 0.3s ease;
            }

            td a:hover {
                color: #0056b3;
            }

            tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            @media (max-width: 768px) {
                table, th, td {
                    font-size: 14px;
                }
                th, td {
                    padding: 10px;
                }
            }

            @media (max-width: 480px) {
                table {
                    width: 100%;
                }
                th, td {
                    font-size: 12px;
                    padding: 8px;
                }
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
                <li><a style="color: #f6f7f6" href="../managerA">Home</a></li>
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
                <td>Work Assignment Employee</td>
            </tr>
            <c:forEach items="${requestScope.listplan}" var="pl">
                <tr>
                    <td>${pl.pid}</td>
                    <td>${pl.pname}</td>
                    <td>${pl.start}</td>
                    <td>${pl.end}</td>
                    <td>${pl.dept.did}</td>
                    <td>${pl.status}</td>
                    <td>${pl.createdby.displayname}</td>
                    <td><a href="../workassignment/create?id=${pl.pid}">Overview</a></td>
                </tr>
            </c:forEach>   
        </table>                
    </body>
</html>
