<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="com.courseendprojects.utils.OperationStatus" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
</head>
<body>
<% OperationStatus status = (OperationStatus)request.getAttribute("status"); %>
<h2>Result Status</h2>
<p>Action: <%= status.getAction() %></p>
<p>Success: <%= status.isSuccess() %></p>
<p>Message: <%= status.getMessage() %></p>
<br>
<a href="index.jsp">Main Menu</a>
</body>
</html>