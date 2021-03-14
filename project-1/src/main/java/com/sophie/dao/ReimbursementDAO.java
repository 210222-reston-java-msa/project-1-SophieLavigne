package com.sophie.dao;

import java.util.List;

import com.sophie.models.Employee;
import com.sophie.models.Reimbursement;

public interface ReimbursementDAO {
	/*
	 * DAO stands for Data Access Object - The object (in impl form), that we use to access our DB.
	 * We use the DAO design pattern to separate business logic (java) from our persistence layer.
	 */
	
	public boolean insert(Employee e, Reimbursement re);
	
	public boolean update(Employee e, Reimbursement re);
	
	public List<Reimbursement> findAll();
	
	public List<Reimbursement> findAllPending();
	
	public List<Reimbursement> findAllResolved();
	
	public List<Reimbursement> findAllForEmployee(Employee e);
	
	public List<Reimbursement> findAllPendingForEmployee(Employee e);
	
	public List<Reimbursement> findAllResolvedForEmployee(Employee e);

}
