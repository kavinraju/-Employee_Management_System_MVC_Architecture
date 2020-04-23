package com;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmployeeDao {

	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String dbname = "system";
	private static String pass = "pass";
	
	public ArrayList<Employee> getAllEmployees(){
		
		ArrayList<Employee> employees = new ArrayList<>();
		Connection connection = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, dbname, pass);
			
			String query= "SELECT * FROM employee";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				String gender = rs.getString(3);
				String dept = rs.getString(4);
				Double salary = rs.getDouble(5);
				Employee emp = new Employee(id, name, gender, dept, salary);
				employees.add(emp);
			}
			
			statement.close();
			connection.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return employees;
	}
	
	public void addEmployee(Employee employee) {
		
		Connection connection = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, dbname, pass);
			
			String query= "insert into employee values(?,?,?,?,?)";
			
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, employee.getEmpID());
			ps.setString(2, employee.getName());
			ps.setString(3, employee.getGender());
			ps.setString(4, employee.getDept());
			ps.setDouble(5, employee.getSalary());
			ps.executeUpdate();
			
			ps.close();
			connection.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public Employee getEmployee(int id) {
		Employee employee = null;
		
		Connection connection = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, dbname, pass);
			
			String query= "SELECT * FROM employee where id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				Integer empId = rs.getInt(1);
				String name = rs.getString(2);
				String gender = rs.getString(3);
				String dept = rs.getString(4);
				Double salary = rs.getDouble(5);
				employee = new Employee(empId, name, gender, dept, salary);
				
				ps.close();
				connection.close();
			}else{
				System.out.println("isNotFirst");
			}
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return employee;
	}
	
	public boolean deleteEmployee(int id){
		boolean deleted = false;
		
		Connection connection = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, dbname, pass);
			
			String query = "delete from employee where id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			int num_of_records = ps.executeUpdate();
			if(num_of_records > 0)
				deleted = true;
			ps.close();
			connection.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return deleted;
	}
	
public ArrayList<Employee> getAllEmployeesSortBy(String sortby){
		
		ArrayList<Employee> employees = new ArrayList<>();
		Connection connection = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, dbname, pass);
			
			String query= "SELECT * FROM employee order by " + sortby;
			System.out.println(query);
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()){
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				String gender = rs.getString(3);
				String dept = rs.getString(4);
				Double salary = rs.getDouble(5);
				Employee emp = new Employee(id, name, gender, dept, salary);
				employees.add(emp);
			}
			
			statement.close();
			connection.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return employees;
	}

public ArrayList<Employee> getAllEmployees(String colname, String value){
	
	ArrayList<Employee> employees = new ArrayList<>();
	Connection connection = null;
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection(url, dbname, pass);
		
		String query= "SELECT * FROM employee where " + colname + "=" + "\'"+value+"\'";
		System.out.println(query);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		while(rs.next()){
			Integer id = rs.getInt(1);
			String name = rs.getString(2);
			String gender = rs.getString(3);
			String dept = rs.getString(4);
			Double salary = rs.getDouble(5);
			Employee emp = new Employee(id, name, gender, dept, salary);
			employees.add(emp);
		}
		
		statement.close();
		connection.close();
		
	}catch(Exception e){
		System.out.println(e);
	}
	
	return employees;
}
}

