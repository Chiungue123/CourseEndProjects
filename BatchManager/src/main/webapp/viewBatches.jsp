<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.courseendprojects.model.Batch" %>
<%@ page import="com.courseendprojects.model.Participant" %>
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
        <thead>
            <tr>
                <th>Batch ID</th>
                <th>Name</th>
             	<th>Scheduled</th>
                <th>Time Slot</th>
                <th>Participants</th>
            </tr>
        </thead>
        <tbody>
        <% for(Batch batch : batches) { %>  <!-- Iterating through the List<Batch> -->
            <% List<Participant> participants = batch.getParticipants(); %>
            <tr>
                <td><%= batch.getBatchID() %></td> <!-- Using getters to fetch the attributes -->
                <td><%= batch.getName() %></td>
                <td>
                <% for(Participant p : participants) { %>
                	 <%= p.getName() %>, 
                <% } %>
                </td>
                <td><%= batch.getTimeSlot() %></td>
                <td>
                 <form action="BatchSignup" method="GET">
		       		<input type="hidden" name="action" value="add>">
		       		<input type="hidden" name="batchID" value="<%= batch.getBatchID() %>">
		       		<input type="submit" value="Add">
       			 </form>
       			 <form action="BatchSignup" method="GET">
		       		<input type="hidden" name="action" value="remove">
		       		<input type="hidden" name="batchID" value="<%= batch.getBatchID() %>">
		       		<input type="submit" value="Remove">
       			 </form>
        		</td>
            </tr>
        <% } %>
        
    </tbody>
    </table>
    <br>
	<a href="index.jsp">Main Menu</a>
</body>
</html>