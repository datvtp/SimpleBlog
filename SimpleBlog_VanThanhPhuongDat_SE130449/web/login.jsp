<%-- 
    Document   : login
    Created on : Jan 16, 2020, 1:38:46 AM
    Author     : vanth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple Blog</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <a href="MainController?action=Search&txtSearch=&txtPageNumber=1">Home</a><br/><br/>
    <center>
        <h2>Login to continue</h2>
        <div style="padding: 20px; margin: 50px; text-align: left; width: 500px; min-height: 50px; background-color: #F5F5F5; border: 1px solid #CDCDCD;">
            <form action="MainController" method="POST">
                Email: <input type="text" name="txtEmail" value="${param.txtEmail}"/>
                <font color="red">
                ${requestScope.INVALID.emailError}
                </font>
                <br/>
                Password: <input type="password" name="txtPassword"/>
                <font color="red">
                ${requestScope.INVALID.passwordError}
                </font>
                <br/>
                <input type="submit" name="action" value="Login"/>
            </form>
        </div>
    </center>
</body>
</html>
