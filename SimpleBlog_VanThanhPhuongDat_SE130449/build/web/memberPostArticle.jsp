<%-- 
    Document   : memberPostArticle
    Created on : Jan 21, 2020, 1:22:19 PM
    Author     : vanth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple Blog</title>
    </head>
    <body>
        <c:if test="${sessionScope.ROLE == null}">
            <c:redirect url="home.jsp"/>
        </c:if>
        <c:if test="${sessionScope.ROLE != null}">
            <c:if test="${sessionScope.ROLE != 2}">
                <c:redirect url="home.jsp"/>
            </c:if>
        </c:if>
        <h1>Welcome, ${sessionScope.NAME}</h1>
        <a href="MainController?action=Search&txtSearch=&txtPageNumber=1">Home</a><br/>

    <center>
        <h2>Create new article</h2>
        <div style="padding: 20px; margin: 50px; text-align: left; width: 500px; min-height: 150px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
            <form action="MainController" method="POST">
                <p>Title: <input type="text" name="txtTitle" value="${param.txtTitle}"/></p>
                <font color="red">
                ${requestScope.INVALID.titleError}
                </font>
                <p>Short Description: <input type="text" name="txtShortDescription" value="${param.txtShortDescription}"/></p>
                <font color="red">
                ${requestScope.INVALID.shortDescriptionError}
                </font>
                <p>Content: <input type="text" name="txtContent" value="${param.txtContent}"/></p>
                <font color="red">
                ${requestScope.INVALID.contentError}
                </font> <br/>
                <input type="submit" name="action" value="Post"/>
            </form>
        </div>
    </center>
</body>
</html>
