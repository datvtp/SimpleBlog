<%-- 
    Document   : emailConfirm
    Created on : Jan 18, 2020, 4:33:10 PM
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
        <c:if test="${sessionScope.CODE == null}">
            <c:redirect url="home.jsp"/>
        </c:if>
        <h1>Welcome, ${sessionScope.NAME}</h1>
    <center>
        <h2>Confirm Email to continue</h2>
        <div style="padding: 20px; margin: 50px; text-align: left; width: 500px; min-height: 50px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
            <form action="MainController" method="POST">
                Enter code: <input type="text" name="txtCode"/>
                <font color="red">
                ${requestScope.INVALID.codeEmailError}
                </font>
                <br/> <br/>
                <input type="submit" name="action" value="Confirm Email"/>
            </form>
        </div>
    </center>

</body>
</html>
