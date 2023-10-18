<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.courseendprojects.model.Participant" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Batch Sign Up</title>
</head>
<body>
<% List<Participant> filteredParticipants = (List<Participant>) request.getAttribute("filteredParticipants"); %>
<h1>Batch ID: <%= request.getAttribute("batchID") %></h1>
<h2>Add Participants Below</h2>
<form action="BatchSignup" method="POST">
    <table border="1">
        <thead>
            <tr>
                <th>Participant ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Add</th>
            </tr>
        </thead>
        <tbody>
        <% for(Participant p : filteredParticipants) { %>  <!-- Iterating through the List<Participant> -->
            <tr>
                <td><%= p.getId() %></td>
                <td><%= p.getName() %></td>
                <td><%= p.getEmail() %></td>
                <td>
                    <input type="checkbox" name="selectedParticipants" value="<%= p.getId() %>" />
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>
    <input type="hidden" name="batchID" value="<%= request.getAttribute("batchID") %>">
    <input type="Submit" value="Add Participant(s)">
</form>
<br>
<a href="index.jsp">Main Menu</a>
</body>
</html>

