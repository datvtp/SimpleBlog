<%-- 
    Document   : admin
    Created on : Jan 16, 2020, 1:43:24 AM
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
            <c:if test="${sessionScope.ROLE != 1}">
                <c:redirect url="home.jsp"/>
            </c:if>
        </c:if>
        <h1>Welcome, ${sessionScope.NAME}</h1>
        <a href="MainController?action=Logout">Logout</a><br/><br/>
    <center>
        <form action="MainController" method="POST">
            Search content: <input type="text" name="txtSearch" value="${param.txtSearch}"/>
            <select name="txtStatusId">
                <option value="0">All</option>
                <option value="1">New</option>
                <option value="2">Active</option>
                <option value="3">Deleted</option>
            </select>
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
                    <h6>Title: ${article_dto.title}</h6>
                    <h6>Short Description: ${article_dto.shortDescription}</h6>
                    <p>Content: ${article_dto.articleContent}</p>
                    <c:if test="${article_dto.statusId == 1}">
                        <c:url var="acceptlLink" value="MainController">
                            <c:param name="txtArticleId" value="${article_dto.articleId}"/>
                            <c:param name="txtSearch" value="${param.txtSearch}"/>
                            <c:param name="txtPageNumber" value="${param.txtPageNumber}"/>
                            <c:param name="txtStatusId" value="${param.txtStatusId}"/>
                            <c:param name="action" value="Accept"/>
                        </c:url>
                        <a href="${acceptlLink}">Accept</a>
                    </c:if>

                    <c:if test="${(article_dto.statusId == 1) || (article_dto.statusId == 2)}">
                        <c:url var="deletelLink" value="MainController">
                            <c:param name="txtArticleId" value="${article_dto.articleId}"/>
                            <c:param name="txtSearch" value="${param.txtSearch}"/>
                            <c:param name="txtPageNumber" value="${param.txtPageNumber}"/>
                            <c:param name="txtStatusId" value="${param.txtStatusId}"/>
                            <c:param name="action" value="Delete"/>
                        </c:url>
                        <a href="${deletelLink}">Delete</a>
                    </c:if>

                </div>
            </c:forEach>
            <c:if test="${requestScope.NUMBEROFPAGE > 1}">
                <c:forEach begin="1" end="${requestScope.NUMBEROFPAGE}" var="i">
                    <div style="float: left; padding-left: 20px;">
                        <c:if test="${(param.txtPageNumber) == i}">
                            <h1><a href="MainController?action=Search&txtSearch=${param.txtSearch}&txtPageNumber=${i}&txtStatusId=${param.txtStatusId}">${i}</a></h1>
                        </c:if>
                        <c:if test="${(param.txtPageNumber) != i}">
                            <h3><a href="MainController?action=Search&txtSearch=${param.txtSearch}&txtPageNumber=${i}&txtStatusId=${param.txtStatusId}">${i}</a></h3>
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
