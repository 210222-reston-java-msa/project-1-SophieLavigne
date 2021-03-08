package com.sophie.services;

import java.util.List;

import com.sophie.dao.EmployeeDAO;
import com.sophie.dao.EmployeeDAOImpl;
import com.sophie.models.Employee;

public class EmployeeService {

	public static EmployeeDAO eDao = new EmployeeDAOImpl();
	
	public static boolean insert(Employee e) {
		
		return eDao.insert(e);
	}
	
	public static boolean update(Employee e) {
		
		return eDao.update(e);
	}
	
	public static List<Employee> findAll() {
		return eDao.findAll();
	}
	
	public static Employee findByUsername(String username) {
		List<Employee> all = eDao.findAll();
		
		for (Employee e: all) {
			if (e.getUsername().equals(username)) {
				return e;
			}
		}
		
		return null;
	}
	
	public static Employee confirmLogin(String username, String password) {
		
		Employee e = findByUsername(username);
		
		if (e == null) {
			return null;
		}
		
		if (e.getPassword() == password) {
			return e;
		}
		else {
			return null;
		}
	}
}
