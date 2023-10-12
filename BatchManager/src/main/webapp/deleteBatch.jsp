<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Batch</title>
</head>
<body>
    <h1>Delete Batch</h1>
    <form action="Batch" method="POST">
        <label for="batchID">Batch ID:</label>
        <input type="number" id="batchID" name="batchID" required><br><br>
        
        <input type="hidden" name="action" value="Delete">
        
        <input type="submit" value="Delete">
    </form>
</body>
</html>