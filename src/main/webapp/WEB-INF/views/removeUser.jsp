<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirm</title>
</head>
<body>
<h4>Please type your email id to delete</h4>
<form method="POST" action="/edu/${requestScope.user}/delete.htm">
	EmailId:<input type="email" name="email" id="email"/>
	<input type="submit" value="Delete Account"> 
</form>
</body>
</html>