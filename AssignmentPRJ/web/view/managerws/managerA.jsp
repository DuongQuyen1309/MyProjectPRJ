<%-- 
    Document   : managerA
    Created on : Nov 3, 2024, 10:29:52 PM
    Author     : Duong Minh Quyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style><%@include file="../css/mwastyle.css"%></style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager A</title>
        <link href="../css/mwastyle.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <div id="header">
                <h1>ABC Company</h1>
                <h2>----Specializing in bamboo and rattan products----</h2>
            </div>
            <div>
                <ul id="tabbar">
                    <li><a style="color: #f6f7f6" href="managerA">Home</a></li>                  
                    <li style="color: #f6f7f6">${sessionScope.account.displayname}</li>
                    <li><a style="color: #f6f7f6" href="logout">Logout</a></li>
                </ul>
            </div>
        </header>
        <div id="content">
            <div>
                <h4>Planning Department</h4>
                <ul>
                    <li><a href="plan/listforA">List Plan of WSA</a></li>
                </ul>
            </div>
        </div>
        <footer>
            <p>Email : information@abccompany.com</p>
            <p>Address: KCN Hoa Lac, Thach That, Ha Noi</p>
            <h5>&copy; Copyright 2021. ABCcompany.com</h5>
        </footer>
    </body>
</html>
