<%@page import="com.Employee"%>
<%@page import="com.EmployeeDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>
</head>
<body>
	<center><h2>Add Employee Result</h2></center>
	<%
	
		String status = (String) request.getAttribute("status");
		String message = (String) request.getAttribute("message");
		if(status!=null && status.equals("success")){
			
			Employee employee =(Employee) request.getAttribute("employee");
	%>
	<h2>Added Employee Successfully<br>
		<%if(employee != null){ %>
			<%=employee.toString() %>
		<%}else{ %>
			None
		<%} %>	
	 </h2>
	<%}
		else if(status.equals("error")){ %>
		<h2>Employee details not added Successfully<br>
		<%=message %>
		</h2>
	<%} %>
	<a href="EmployeeController?action=home" style="padding:40px;">Home</a>
</body>
</html>