<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.courseendprojects.model.Batch" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Batches</title>
</head>
<body>
	<% List<Batch> batches = (List<Batch>) request.getAttribute("batches"); %>

    <h1>Batches</h1>
    <table border="1">
        <!-- Table header -->
        <thead>
            <tr>
                <th>Batch ID</th>
                <th>Name</th>
                <th>Time Slot</th>
            </tr>
        </thead>
        <!-- Table body -->
        <tbody>
        <% for(Batch batch : batches) { %>  <!-- Iterating through the List<Batch> -->
            <tr>
                <td><%= batch.getBatchID() %></td> <!-- Using getters to fetch the attributes -->
                <td><%= batch.getName() %></td>
                <td><%= batch.getTimeSlot() %></td>
            </tr>
        <% } %>
    </tbody>
    </table>
    <br>
	<a href="index.jsp">Main Menu</a>
</body>
</html>
