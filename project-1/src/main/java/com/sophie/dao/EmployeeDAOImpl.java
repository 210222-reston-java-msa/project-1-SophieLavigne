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
			String sql = "INSERT INTO users (first_name, last_name, email, username, pass) " + 
			"VALUES (?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, e.getFirstName());
			stmt.setString(2, e.getLastName());
			stmt.setString(3, e.getEmail());
			stmt.setString(4, e.getUsername());
			stmt.setString(5, e.getPassword());
			
			if (!stmt.execute()) {
				return false;
			}
		} catch (SQLException ex) {
			log.warn("Unable to insert user", ex);
			return false;
		}
		return true;
	}

	public boolean update(Employee e) {
		PreparedStatement stmt = null;
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "UPDATE users " + 
			"SET first_name = ?, last_name = ?, email = ?, username = ?, pass = ? WHERE id =  ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, e.getFirstName());
			stmt.setString(2, e.getLastName());
			stmt.setString(3, e.getEmail());
			stmt.setString(4, e.getUsername());
			stmt.setString(5, e.getPassword());
			stmt.setInt(6, e.getId());
			
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
			String sql = "SELECT * FROM users;";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
			int id = rs.getInt("id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String email = rs.getString("email");
			String username = rs.getString("username");
			String pass = rs.getString("pass");
			int role = rs.getInt("role_id");
			e = new Employee(id, firstName, lastName, email, username, pass, role);
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

	@Override
	public Employee findByID(int id) {
		PreparedStatement stmt = null;
		Employee e = null;
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM users WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String email = rs.getString("email");
			String username = rs.getString("username");
			String pass = rs.getString("pass");
			int role = rs.getInt("role_id");
			e = new Employee(id, firstName, lastName, email, username, pass, role);
			
			}
			
			if (!stmt.execute()) {
				return null;
			}
		} catch (SQLException ex) {
			log.warn("Unable to retrieve user with specified ID", ex);
			return null;
		}
		return e;
	}

}
