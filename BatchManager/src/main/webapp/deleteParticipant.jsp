<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Participant</title>
</head>
<body>
    <h1>Delete Participant</h1>
    <form action="Participant" method="POST">
        <label for="participantID">Participant ID:</label>
        <input type="number" id="participantID" name="id" required><br><br>
        
        <input type="hidden" name="action" value="Delete">
        
        <input type="submit" value="Delete">
    </form>
    <br>
    <p>Miss Clicked? <a href="index.jsp">Go Back</a></p>
</body>
</html>