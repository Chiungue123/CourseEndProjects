<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.courseendprojects.utils.ErrorStatus" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
    <% ErrorStatus errorStatus = (ErrorStatus)request.getAttribute("errorStatus"); %>
    <h2>Error Status</h2>
    <br>
    <p>Action: <%= errorStatus.getAction() %></p>
    <p>Error Message: <%= errorStatus.getErrorMessage() %></p>
    <br>
    <a href="index.jsp">Main Menu</a>
</body>
</html>