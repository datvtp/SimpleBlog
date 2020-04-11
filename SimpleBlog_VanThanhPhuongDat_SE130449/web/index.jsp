FFF<%-- 
    Document   : index
    Created on : Jan 20, 2020, 5:36:05 PM
    Author     : vanth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:redirect url="MainController?action=Search&txtSearch=&txtPageNumber=1"/>
    </body>
</html>
