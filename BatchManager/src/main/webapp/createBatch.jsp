<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Batch Creation Form</title>
</head>
<body>
    <h2>Batch Creation Form</h2>
    <br><br>
    <form action="Batch" method="POST">
        <label for="batchName">Enter Batch Name:</label>
        <input type="text" id="batchName" name="name" required>
        <br><br>

        <label for="timeSlot">Select Time Slot:</label>
        <select id="timeSlot" name="timeSlot" required>
            <option value="morning">Morning</option>
            <option value="afternoon">Afternoon</option>
            <option value="evening">Evening</option>
            <option value="night">Night</option>
        </select>
        <br><br>
        
        <input type="hidden" name="action" value="Create">

        <input type="submit" value="Create Batch">
    </form>

    <p>Miss Clicked? <a href="index.jsp">Go Back</a></p>
</body> 
</html>