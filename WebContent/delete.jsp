<%@page import="com.EmployeeDao"%>
<%@page import="com.Employee" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deleted Employee</title>
</head>
<body>
	<center><h2>Deleted Employee</h2></center>
	<%
		
		boolean deleted = (Boolean)request.getAttribute("deleted");
	
		if(deleted){
	%>
		<center><h2>Deleted Employee details successfully</h2></center>
	<%}
		else{%>
		<center><h2>Not Deleted Employee details successfully</h2></center>
	<%} %>		
	<a href="EmployeeController?action=home" style="padding:40px;">Home</a>
</body>
</html>