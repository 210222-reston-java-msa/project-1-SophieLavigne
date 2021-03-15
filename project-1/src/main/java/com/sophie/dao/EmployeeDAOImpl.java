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
			String sql = "INSERT INTO users (username, pass, first_name, last_name, email) " + 
			"VALUES (?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, e.getUsername());
			stmt.setString(2, e.getPassword());
			stmt.setString(3, e.getFirstName());
			stmt.setString(4, e.getLastName());
			stmt.setString(5, e.getEmail());
			
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
			log.info(e.toString());
			String sql = "UPDATE users " + 
			"SET username = ?, pass = ?, first_name = ?, last_name = ?, email = ? WHERE id =  ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, e.getUsername());
			stmt.setString(2, e.getPassword());
			stmt.setString(3, e.getFirstName());
			stmt.setString(4, e.getLastName());
			stmt.setString(5, e.getEmail());
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

	public ArrayList<Employee> findAll() {
		ArrayList<Employee> list = new ArrayList<Employee>();
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
			int role_id = rs.getInt("role_id");
			e = new Employee(id, firstName, lastName, email, username, pass, role_id);
			e = new Employee(id, firstName, lastName, email, username, pass, role_id, e.getRole());
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
	public ArrayList<Employee> findByID(int id) {
		PreparedStatement stmt = null;
		Employee e = null;
		ArrayList<Employee> out = new ArrayList<Employee>();
		
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
			e = new Employee(id, firstName, lastName, email, username, pass, role, e.getRole());
			out.add(e);
			
			}
			
			if (!stmt.execute()) {
				return null;
			}
		} catch (SQLException ex) {
			log.warn("Unable to retrieve user with specified ID", ex);
			return null;
		}
		return out;
	}

}
