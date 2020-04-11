<%-- 
    Document   : error
    Created on : Jan 16, 2020, 1:39:43 AM
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
        <h1>Error Page</h1>
        <h2>
            <font color="red">
            ${requestScope.ERROR}
            <font/>
            <a href="MainController?action=Search&txtSearch=&txtPageNumber=1">Back to Home</a><br/><br/>
        </h2>
    </body>
</html>
