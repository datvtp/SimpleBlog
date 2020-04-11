<%-- 
    Document   : memberViewDetailArticle
    Created on : Jan 20, 2020, 4:26:16 PM
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
        <div style="padding: 20px; margin: 50px; text-align: left; width: 500px; min-height: 200px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
            <h3>${requestScope.DTO.author}</h3>
            <h6>Time: ${requestScope.DTO.timeGenerated}</h6>
            <h6>Title: ${requestScope.DTO.title}</h6>
            <h6>Short Description: ${requestScope.DTO.shortDescription}</h6>
            <p>Content: ${requestScope.DTO.articleContent}</p>
        </div>
        <form action="MainController" method="POST">
            <input type="text" name="txtContent"/>
            <input type="hidden" name="txtArticleId" value="${requestScope.DTO.articleId}"/> <br>
            <font color="red">
            ${requestScope.INVALID.commentContentError}
            </font>
            <br/>
            <input type="submit" name="action" value="Comment"/>
        </form>
        <c:if test="${not empty requestScope.LIST}">
            <c:forEach items="${requestScope.LIST}" var="comment_dto">
                <div style="padding: 20px; margin: 50px; text-align: left; width: 300px; min-height: 100px; border: 1px solid #CDCDCD;">
                    <h4>${comment_dto.author}</h4>
                    <p>${comment_dto.commentContent}</p>
                    <h6>${comment_dto.timeGenerated}</h6>
                </div>
            </c:forEach>
        </c:if>
    </center>
</body>
</html>
