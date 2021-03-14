package com.sophie.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.sophie.dao.EmployeeDAO;
import com.sophie.dao.EmployeeDAOImpl;
import com.sophie.models.Employee;

public class EmployeeService {
	
	private static Logger log = Logger.getLogger(EmployeeService.class);

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
	
	public static Employee findByID(int id) {
		return eDao.findByID(id);
	}
	
	public static Employee confirmLogin(String username, String password) {
		log.info("Employee Service attempting to confirm login!");
		Employee e = findByUsername(username);
		log.info(e.toString());
		
		if (e.equals(null)) {
			log.info("Employee not found!");
			return null;
		}
		
		if (e.getPassword().equals(password)) {
			log.info("confirmLogin success!");
			return e;
		}
		else {
			log.info("Password mismatch!");
			return null;
		}
	}
}
