<%-- 
    Document   : createDetailPlan
    Created on : Oct 24, 2024, 5:04:01 PM
    Author     : Duong Minh Quyen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style><%@include file="/view/plan/style.css"%></style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form action="create" method="POST">
            <div class="table-container">
                <div class="table-horizontal-container">
                    <table class="unfixed-table" border="1px">
                        <thead>
                        <input type="hidden" name="planid" value="${requestScope.planid}"/>
                        <td colspan="2">Product</td>
                        <c:forEach items="${requestScope.planWithGP[0].generalplan}" var="gp">
                            <td>${gp.product.prname}</td>
                        </c:forEach>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.daterange}" var="d">
                            <input type="hidden" name="date" value="${d}"/>
                            <tr>
                                <td rowspan="3">${d}</td>
                                <td>K1<input type="hidden" name="sid1${d}" value="1"/></td>
                                    <c:forEach items="${requestScope.planWithGP[0].generalplan}" var="gp">
                                <input type="hidden" name="gp1${d}" value="${gp.gpid}"/>
                                <td> <input type="text" name="${gp.gpid}a1a${d}"
                                            <c:forEach items="${requestScope.dpplans}" var="detail">
                                                <c:if test="${(detail.gpid eq gp.gpid) and (detail.date eq d) and (detail.sid eq 1)}">
                                                    value="${detail.quantity}"
                                                </c:if>
                                            </c:forEach> >                                    
                                </td>
                            </c:forEach>
                            </tr>
                            <tr>
                                <td>K2<input type="hidden" name="sid2${d}" value="2"/></td>
                                    <c:forEach items="${requestScope.planWithGP[0].generalplan}" var="gp">
                                <input type="hidden" name="gp2${d}" value="${gp.gpid}"/>
                                <td> <input type="text" name="${gp.gpid}a2a${d}"
                                            <c:forEach items="${requestScope.dpplans}" var="detail">
                                                <c:if test="${(detail.gpid eq gp.gpid) and (detail.date eq d) and (detail.sid eq 2)}">
                                                    value="${detail.quantity}"
                                                </c:if>
                                            </c:forEach>>                                    
                                </td>
                            </c:forEach>
                            </tr>
                            <tr>
                                <td>K3<input type="hidden" name="sid3${d}" value="3"/></td>
                                    <c:forEach items="${requestScope.planWithGP[0].generalplan}" var="gp">
                                <input type="hidden" name="gp3${d}" value="${gp.gpid}"/>
                                <td> <input type="text" name="${gp.gpid}a3a${d}"
                                            <c:forEach items="${requestScope.dpplans}" var="detail">
                                                <c:if test="${(detail.gpid eq gp.gpid) and (detail.date eq d) and (detail.sid eq 3)}">
                                                    value="${detail.quantity}"
                                                </c:if>
                                            </c:forEach>>                                    
                                </td>
                            </c:forEach>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
