package com.sophie.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sophie.models.Employee;
import com.sophie.services.EmployeeService;

public class ConnectionUtil {

	
	private static Logger log = Logger.getLogger(ConnectionUtil.class);
	public static Connection getConnection() {
		
		try {
			Class.forName("org.postgresql.Driver");
		}catch (ClassNotFoundException e){
			log.warn("Cannot load the driver!");
			e.printStackTrace();
		}
		
		Properties props = new Properties();
		//This ClassLoader isn't always necessary, but it's an obj used to search through our entire project
		//to find our connection.properties file to give us the connection credentials.
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Connection conn = null;
		
		try {
			props.load(loader.getResourceAsStream("connection.properties")); //This file stores credentials.
			//Capture the connection URL.
			String url = props.getProperty("url");
			//Capture the username.
			String username = props.getProperty("username");
			//Capture the password.
			String password = props.getProperty("password");
			
			try {
				conn = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				log.warn("Unable to connect to DB!");
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		return conn;
	}
	
//	public static void main(String[] args) { //Use this method only to test the validity of database connection.
//		
//		//1. Create dummy employee
//		Employee e = new Employee("John", "Smith", "jsmith", "12345");
//		//Call insert from employeeService
//		EmployeeService.insert(e);
//	}
}
