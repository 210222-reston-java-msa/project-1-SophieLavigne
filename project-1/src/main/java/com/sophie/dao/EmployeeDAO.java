package com.sophie.dao;

import java.util.ArrayList;
import java.util.List;

import com.sophie.models.Employee;

public interface EmployeeDAO {

	/*
	 * DAO stands for Data Access Object - The object (in impl form), that we use to access our DB.
	 * We use the DAO design pattern to separate business logic (java) from our persistence layer.
	 */
	
	public boolean insert(Employee e);
	public boolean update(Employee e);
	
	public ArrayList<Employee> findAll();
	
	public Employee findByID(int id);
	
}
