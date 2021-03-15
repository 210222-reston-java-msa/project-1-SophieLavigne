package com.sophie.services;

import java.util.ArrayList;

import com.sophie.dao.ReimbursementDAO;
import com.sophie.dao.ReimbursementDAOImpl;
import com.sophie.models.Employee;
import com.sophie.models.Reimbursement;

public class ReimbursementService {

	public static ReimbursementDAO rDao = new ReimbursementDAOImpl();
	
	public static boolean insert(Employee e, Reimbursement re) {
		
		return rDao.insert(e, re);
	}
	
	public static boolean update(Employee e, Reimbursement re) {
		
		return rDao.update(e, re);
	}
	
	public static ArrayList<Reimbursement> findAll() {
		return rDao.findAll();
	}
	
	public static ArrayList<Reimbursement> findAllPending() {
		return rDao.findAllPending();
	}
	
	public static ArrayList<Reimbursement> findAllResolved() {
		return rDao.findAllResolved();
	}
	
	public static ArrayList<Reimbursement> findAllForEmployee(Employee e){
		return rDao.findAllForEmployee(e);
	}
	
	public static ArrayList<Reimbursement> findAllPendingForEmployee(Employee e){
		return rDao.findAllPendingForEmployee(e);
	}
	
	public static ArrayList<Reimbursement> findAllResolvedForEmployee(Employee e){
		return rDao.findAllResolvedForEmployee(e);
}
}
