<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Batch</title>
</head>
<body>
    <h1>Update Batch</h1>
    <form action="Batch" method="POST">
        <label for="batchID">Batch ID:</label>
        <input type="number" id="batchID" name="batchID" required><br><br>
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br><br>
        
        <label for="timeSlot">Time Slot:</label>
        <select id="timeSlot" name="timeSlot">
            <option value="morning">Morning</option>
            <option value="afternoon">Afternoon</option>
            <option value="evening">Evening</option>
        </select><br><br>
        
        <input type="hidden" name="action" value="Update">
        
        <input type="submit" value="Update">
    </form>
    <br>
    <p>Misclicked? <a href="index.jsp">Go Back</a></p>
</body>
</html>
