package com.sophie.services;

import java.util.ArrayList;
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
	
	public static ArrayList<Employee> findAll() {
		return eDao.findAll();
	}
	
	public static ArrayList<Employee> findByUsername(String username) {
		ArrayList<Employee> all = eDao.findAll();
		ArrayList<Employee> out = new ArrayList<Employee>();
		for (Employee e: all) {
			if (e.getUsername().equals(username)) {
				out.add(e);
				return out;
			}
		}
		
		return null;
	}
	
	public static ArrayList<Employee> findByID(int id) {
		return eDao.findByID(id);
	}
	
	public static Employee confirmLogin(String username, String password) {
		log.info("Employee Service attempting to confirm login!");
		ArrayList<Employee> e = findByUsername(username);
		
		if (e == null) {
			log.info("Employee not found!");
			return null;
		}
		
		if (e.get(0).getPassword().equals(password)) {
			log.info(e.get(0).toString());
			log.info("confirmLogin success!");
			return e.get(0);
		}
		else {
			log.info(e.get(0).toString());
			log.info("Password mismatch!");
			return null;
		}
	}
}
