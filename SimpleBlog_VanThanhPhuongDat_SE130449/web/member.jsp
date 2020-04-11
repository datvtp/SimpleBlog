<%-- 
    Document   : member
    Created on : Jan 19, 2020, 3:01:15 PM
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
        <a href="MainController?action=Logout">Logout</a><br/><br/>
        <c:url var="postLink" value="MainController">
            <c:param name="action" value="PostArticle"/>
        </c:url>
        <a href="${postLink}">Post Article</a>
    <center>
        <form action="MainController" method="POST">
            Search by title: <input type="text" name="txtSearch" value="${param.txtSearch}"/>
            <input type="hidden" name="txtPageNumber" value="1"/>
            <input type="submit" name="action" value="Search"/>
        </form>
    </center>


    <center>
        <c:if test="${not empty requestScope.LIST}" var="check">

            <c:forEach items="${requestScope.LIST}" var="article_dto">
                <div style="padding: 20px; margin: 50px; text-align: left; width: 500px; min-height: 200px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
                    <h3>${article_dto.author}</h3>
                    <h6>Time: ${article_dto.timeGenerated}</h6>
                    <c:url var="detailLink" value="MainController">
                        <c:param name="txtArticleId" value="${article_dto.articleId}"/>
                        <c:param name="action" value="ViewDetailArticle"/>
                    </c:url>
                    <p>Title: <a href="${detailLink}">${article_dto.title}</a></p>
                    <p>Short Description: ${article_dto.shortDescription}</p>
                </div>
            </c:forEach>
            <c:if test="${requestScope.NUMBEROFPAGE > 1}">
                <c:forEach begin="1" end="${requestScope.NUMBEROFPAGE}" var="i">
                    <div style="float: left; padding-left: 20px;">
                        <c:if test="${(param.txtPageNumber) == i}">
                            <h1><a href="MainController?action=Search&txtSearch=${param.txtSearch}&txtPageNumber=${i}">${i}</a></h1>
                        </c:if>
                        <c:if test="${(param.txtPageNumber) != i}">
                            <h3><a href="MainController?action=Search&txtSearch=${param.txtSearch}&txtPageNumber=${i}">${i}</a></h3>
                        </c:if>
                        
                    </div>
                </c:forEach>
            </c:if>
        </c:if>
        <c:if test="${!check}">
            <h3>No Record Found</h3>
        </c:if>
    </center>
</body>
</html>
