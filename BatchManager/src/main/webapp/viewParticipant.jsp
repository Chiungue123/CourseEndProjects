<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.courseendprojects.model.Participant" %>
<%@ page import="com.courseendprojects.model.Batch" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Participants</title>
</head>
<body>
	<% List<Participant> participants = (List<Participant>) request.getAttribute("participants"); %>

    <h1>Participants</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Participant ID</th>
                <th>Name</th>
                <th>Batches</th>
                <th>Email</th>
            </tr>
        </thead>
        <tbody>
        <% for(Participant participant : participants) { %>  <!-- Iterating through the List<Participant> -->
            <% List<Batch> batches = participant.getBatches(); %>
            <tr>
                <td><%= participant.getId() %></td> <!-- Using getters to fetch the attributes -->
                <td><%= participant.getName() %></td>
                <td>
                <% for(Batch b : batches) { %>
                	 <%= b.getName() %>, 
                <% } %>
                </td>
                <td><%= participant.getEmail() %></td>
            </tr>
        <% } %>
    	</tbody>
    </table>
    <br>
	<a href="index.jsp">Main Menu</a>
</body>
</html>