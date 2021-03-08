package com.sophie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sophie.models.Employee;
import com.sophie.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO{

	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);

	public boolean insert(Employee e) {
		PreparedStatement stmt = null;
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO employee (first_name, last_name, username, pwd) " + 
			"VALUES (?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, e.getFirstName());
			stmt.setString(2, e.getLastName());
			stmt.setString(3, e.getUsername());
			stmt.setString(4, e.getPassword());
			
			if (!stmt.execute()) {
				return false;
			}
		} catch (SQLException ex) {
			log.warn("Unable to insert user", ex);
			return false;
		}
		return true;
	}

	public boolean update(Employee e) { //UPDATE this method, it won't work right yet.
PreparedStatement stmt = null;
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "UPDATE employee " + 
			"SET first_name = ?, last_name = ?, username = ?, pwd = ? "; //+
			//"WHERE ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, e.getFirstName());
			stmt.setString(2, e.getLastName());
			stmt.setString(3, e.getUsername());
			stmt.setString(4, e.getPassword());
			
			if (!stmt.execute()) {
				return false;
			}
		} catch (SQLException ex) {
			log.warn("Unable to update user", ex);
			return false;
		}
		return true;
	}

	public List<Employee> findAll() {
		List<Employee> list = new ArrayList<Employee>();
		PreparedStatement stmt = null;
		Employee e = new Employee();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM employee;";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
			int id = rs.getInt("id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String username = rs.getString("username");
			String pwd = rs.getString("pwd");
			e = new Employee(id, firstName, lastName, username, pwd);
			list.add(e);
			}
			
			if (!stmt.execute()) {
				return null;
			}
		} catch (SQLException ex) {
			log.warn("Unable to retrieve all users", ex);
			return null;
		}
		return list;
	}

}
