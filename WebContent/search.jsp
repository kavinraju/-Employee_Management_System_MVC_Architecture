<%@page import="com.Employee"%>
<%@page import="com.EmployeeDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Employee</title>
</head>
<body>
	<center><h2>Search Employee</h2></center>
	<center>
	
		<%
		
			String status = (String) request.getAttribute("status");
			String message = (String) request.getAttribute("message");
			if(status!=null && status.equals("success")){
				
				Employee employee =(Employee) request.getAttribute("employee");
			
			if(employee != null){
		%>
			<h2>Employee details available</h2>
			<b>Employee ID:</b>&nbsp;<%=employee.getEmpID() %><br>
			<b>Name:</b>&nbsp;<%=employee.getName() %><br>
			<b>Gender:</b>&nbsp;<%=employee.getGender() %><br>
			<b>Department:</b>&nbsp;<%=employee.getDept() %><br>
			<b>Salary:&nbsp;</b><%=employee.getSalary() %><br>
			
		<%}
		}else if(status.equals("error")){ %>
			<h2><%=message %></h2>
		
		<%}
		else{%>
			<h2>Employee details not available</h2>
		<%} %>
	</center>
	<a href="EmployeeController?action=home" style="padding:40px;">Home</a>
</body>
</html>