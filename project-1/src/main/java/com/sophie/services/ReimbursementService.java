package com.sophie.services;

import java.util.List;

import com.sophie.dao.ReimbursementDAO;
import com.sophie.dao.ReimbursementDAOImpl;
import com.sophie.models.Employee;
import com.sophie.models.Reimbursement;

public class ReimbursementService {

	public static ReimbursementDAO rDao = new ReimbursementDAOImpl();
	
	public static boolean insert(Reimbursement re) {
		
		return rDao.insert(re);
	}
	
	public static boolean update(Employee e, Reimbursement re) {
		
		return rDao.update(e, re);
	}
	
	public static List<Reimbursement> findAll() {
		return rDao.findAll();
	}
	
	public static List<Reimbursement> findAllPending() {
		return rDao.findAllPending();
	}
	
	public static List<Reimbursement> findAllResolved() {
		return rDao.findAllResolved();
	}
	
	public static List<Reimbursement> findAllForEmployee(Employee e){
		return rDao.findAllForEmployee(e);
	}
	
	public static List<Reimbursement> findAllPendingForEmployee(Employee e){
		return rDao.findAllPendingForEmployee(e);
	}
	
	public static List<Reimbursement> findAllResolvedForEmployee(Employee e){
		return rDao.findAllResolvedForEmployee(e);
}
}
