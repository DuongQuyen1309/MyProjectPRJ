<%-- 
    Document   : headplanning
    Created on : Oct 27, 2024, 10:28:47 AM
    Author     : Duong Minh Quyen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style><%@include file="../css/headplanning.css"%></style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Planning Department</title>
        <link href="../css/headplanning.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <div id="header">
                <h1>ABC Company</h1>
                <h2>----Specializing in bamboo and rattan products----</h2>
            </div>
            <div>
                <ul id="tabbar">
                    <li><a style="color: #f6f7f6" href="headplanning">Home</a></li>                  
                    <li style="color: #f6f7f6">${sessionScope.account.displayname}</li>
                    <li><a style="color: #f6f7f6" href="../logout">Logout</a></li>
                </ul>
            </div>
        </header>
        <div id="content">
            <div>
                <h4>Announcement from board of directors</h4>
                <ul>
                    <li><a href="#">Strategy of plan for quarterly 1/2024</a></li>
                    <li><a href="#">Strategy of plan for quarterly 2/2024</a></li>
                </ul>
            </div>
            <div>
                <h4>Planning Department</h4>
                <ul>
                    <li><a href="../plan/create">Create New Plan</a></li>
                    <li><a href="../plan/list">List All Plan</a></li>
                </ul>
            </div>
            <div>
                <h4>Periodic Report</h4>
                <ul>
                    <li><a href="#">Periodic Reports for January and February</a></li>
                    <li><a href="#">Periodic Reports for March and April</a></li>
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
