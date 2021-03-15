package com.sophie.dao;

import java.util.ArrayList;

import com.sophie.models.Employee;
import com.sophie.models.Reimbursement;

public interface ReimbursementDAO {
	/*
	 * DAO stands for Data Access Object - The object (in impl form), that we use to access our DB.
	 * We use the DAO design pattern to separate business logic (java) from our persistence layer.
	 */
	
	public boolean insert(Employee e, Reimbursement re);
	
	public boolean update(Employee e, Reimbursement re);
	
	public ArrayList<Reimbursement> findAll();
	
	public ArrayList<Reimbursement> findById();
	
	public ArrayList<Reimbursement> findAllPending();
	
	public ArrayList<Reimbursement> findAllResolved();
	
	public ArrayList<Reimbursement> findAllForEmployee(Employee e);
	
	public ArrayList<Reimbursement> findAllPendingForEmployee(Employee e);
	
	public ArrayList<Reimbursement> findAllResolvedForEmployee(Employee e);

}
