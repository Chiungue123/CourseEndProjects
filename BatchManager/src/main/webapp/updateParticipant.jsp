<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Participant</title>
</head>
<body>
 <h2>Update Participant</h2>
    <br><br>
    <form action="Participant" method="POST">
    	<label for="participantID">Enter Participant ID:</label>
        <input type="number" id="participantID" name="id" required>
        <br>
        <label for="participantName">Enter Participant Name:</label>
        <input type="text" id="participantName" name="name" required>
        <br>
        <label for="participantName">Enter Participant Email:</label>
        <input type="email" id="participantEmail" name="email" required>
        <br>
                
        <input type="hidden" name="action" value="Update">

        <input type="submit" value="Create Participant">
    </form>
	<br>
    <p>Misclicked? <a href="index.jsp">Go Back</a></p>
</body>
</html>