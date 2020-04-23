package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		out.print("Add Employee");
		
		if(action == null){
			response.sendRedirect("index.html");
		}else if(action.equals("view_all_employees")){
			
			String status = null;
			String message = null;
			ArrayList<Employee> employees = null;
			
			try{
			EmployeeDao dao = new EmployeeDao();
			employees = dao.getAllEmployees();
			status = "success";
			message = "Employee Added Successfully";
			}catch(Exception e){
				status = "error";
				message = "Error: " + e;
			}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("displayallemployees.jsp");
			request.setAttribute("status", status);
			request.setAttribute("message", message);
			request.setAttribute("employee_list", employees);
			dispatcher.forward(request,response);
			
		}else if(action.equals("add_employee")){
			response.sendRedirect("addemployee_form.html");
		}else if(action.equals("search_employee")){
			response.sendRedirect("search_form.html");
		}else if(action.equals("delete_employee")){
			response.sendRedirect("delete_form.html");
		}else if(action.equals("home")){
			response.sendRedirect("index.html");
		}else if(action.equals("Add Employee")){
			
			String status = null;
			String message = null;
			Employee employee = null;
			
			try{
			String tmp_id = request.getParameter("empId");
			String empName = request.getParameter("empName");
			String gender = request.getParameter("gender");
			String dept = request.getParameter("dept");
			String tmp_salary = request.getParameter("salary");
			
			int id = Integer.parseInt(tmp_id);
			double salary = Double.parseDouble(tmp_salary);
			
			EmployeeDao dao = new EmployeeDao();
			employee = new Employee(id,empName,gender,dept,salary);
			dao.addEmployee(employee);
			status = "success";
			message = "Employee Added Successfully";
			}catch(Exception e){
				status = "error";
				message = "Error: " + e;
			}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("addemployee.jsp");
			request.setAttribute("status", status);
			request.setAttribute("message", message);
			request.setAttribute("employee", employee);
			dispatcher.forward(request,response);
			
		}else if(action.equals("Delete Employee")){
			EmployeeDao dao = new EmployeeDao();
			String tmp_id = request.getParameter("empId");
			int id = Integer.parseInt(tmp_id);
			boolean deleted = dao.deleteEmployee(id);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("delete.jsp");
			request.setAttribute("deleted", deleted);
			dispatcher.forward(request,response);
			
		}else if(action.equals("Search")){
			String status = null;
			String message = null;
			Employee employee = null;
			
			try{
			
			String tmp_id = request.getParameter("empId");
			int id = Integer.parseInt(tmp_id);
				
			EmployeeDao dao = new EmployeeDao();
			employee = dao.getEmployee(id);
			if(employee!=null){
				status = "success";
				message = "Employee Added Successfully";
			}else{
				status = "error";
				message = "Record Not found";
			}
			
			}catch(Exception e){
				status = "error";
				message = "Error: " + e;
			}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
			request.setAttribute("status", status);
			request.setAttribute("message", message);
			request.setAttribute("employee", employee);
			dispatcher.forward(request,response);
			
			
		}else if(action.equals("Sort")){
			String status = null;
			String message = null;
			ArrayList<Employee> employees = null;
			
			String sortby = request.getParameter("sortby");
			try{
			EmployeeDao dao = new EmployeeDao();
			employees = dao.getAllEmployeesSortBy(sortby);
			status = "success";
			message = "Employee Added Successfully";
			}catch(Exception e){
				status = "error";
				message = "Error: " + e;
			}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("displayallemployees.jsp");
			request.setAttribute("status", status);
			request.setAttribute("message", message);
			request.setAttribute("employee_list", employees);
			dispatcher.forward(request,response);
			
		}else if(action.equals("List By Department")){
			String status = null;
			String message = null;
			ArrayList<Employee> employees = null;
			
			String list_by_dept = request.getParameter("list_by_dept");
			try{
			EmployeeDao dao = new EmployeeDao();
			employees = dao.getAllEmployees("dept", list_by_dept);
			status = "success";
			message = "Employee Added Successfully";
			}catch(Exception e){
				status = "error";
				message = "Error: " + e;
			}
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("displayallemployees.jsp");
			request.setAttribute("status", status);
			request.setAttribute("message", message);
			request.setAttribute("employee_list", employees);
			dispatcher.forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
