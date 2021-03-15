package com.sophie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sophie.models.Employee;
import com.sophie.models.Reimbursement;
import com.sophie.services.EmployeeService;
import com.sophie.util.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO{
	private static Logger log = Logger.getLogger(ReimbursementDAOImpl.class);
	
	@Override
	public boolean insert(Employee e, Reimbursement re) {
		PreparedStatement stmt = null;
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			Timestamp submitted = new Timestamp(System.currentTimeMillis());
			
			String sql = "INSERT INTO reimbursements (amount, submitted, description, submitter_id, status_id, type_id) " + 
			"VALUES (?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, re.getAmount());
			stmt.setTimestamp(2, submitted);
			stmt.setString(3, re.getDescription());
			stmt.setInt(4, e.getId());
			stmt.setInt(5, re.getStatus_id());
			stmt.setInt(6, re.getType_id());
			
			if (re.getAmount() < 0) {
				log.warn("Attempt at a negative reimbursement!");
				return false;
			}
			
			if (!stmt.execute()) {
				return false;
			}
		} catch (SQLException ex) {
			log.warn("Unable to insert user", ex);
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Employee e, Reimbursement re) { //Should only allow updates to add resolved timestamp, resolution status, and resolver_id. Erroneous reimbursements can simply be denied.
	/*Reimbursement contains the following fields - 
	private int id;
	private double amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private int submitter_id;
	private int resolver_id;
	private int status_id;
	private int type_id;
	*/
		PreparedStatement stmt = null;
		if (e.getRole_id() == 1) {
			log.error("Employee attempted to resolve a reimbursement. How did this happen?");
			return false;
		}
		else { 
			try {
				Connection conn = ConnectionUtil.getConnection();
				if (e.getRole_id() == 2) {
				String sql = "UPDATE reimbursements " + 
						"SET resolved = ?, resolver_id = ?, status_id = ?, WHERE id =  ?";
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				stmt = conn.prepareStatement(sql);
				stmt.setTimestamp(1, timestamp);
				stmt.setInt(2, e.getId());
				stmt.setInt(3, re.getStatus_id());
				stmt.setInt(4, re.getId());
				}
				else if (e.getRole_id() == 1) {
					log.warn("Attempted update by unqualified user!");
					return false;
				}
			
				if (!stmt.execute()) {
					return false;
				}
			} catch (SQLException ex) {
				log.warn("Unable to update reimbursement", ex);
				return false;
			}
		}
		return true;
	}

	@Override
	public ArrayList<Reimbursement> findAll() {
		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		PreparedStatement stmt = null;
		Reimbursement re = new Reimbursement();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbursements;";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
			int id = rs.getInt("id");
			double amount = rs.getDouble("amount");
			Timestamp submitted = rs.getTimestamp("submitted");
			Timestamp resolved = rs.getTimestamp("resolved");
			String description = rs.getString("description");
			int submitter_id = rs.getInt("submitter_id");
			int resolver_id = rs.getInt("resolver_id");
			int status_id = rs.getInt("status_id");
			int type_id = rs.getInt("type_id");
			String resolver = "";
			if (resolver_id != 0) {
			resolver = EmployeeService.findByID(resolver_id).get(0).getUsername();
			}
			String type = "";
			String status = "";
			switch(status_id) {
			case 1:
				status = "Pending";
				break;
			case 2:
				status = "Approved";
				break;
			case 3:
				status = "Rejected";
				break;
			default:
				break;
			}
			switch(type_id) {
			case 1:
				type = "Lodging";
				break;
			case 2:
				type = "Travel";
				break;
			case 3:
				type = "Food";
				break;
			case 4:
				type = "Other";
				break;
			default:
				break;
			}
			String submitter = EmployeeService.findByID(submitter_id).get(0).getUsername();
			
			re = new Reimbursement(amount, submitted, resolved, description, submitter_id, submitter, resolver_id, resolver, status_id, type_id, status, type);
			log.info(re.toString());
			list.add(re);
			}
			
			if (!stmt.execute()) {
				return null;
			}
		} catch (SQLException ex) {
			log.warn("Unable to retrieve all reimbursements", ex);
			return null;
		}
		return list;
	}

	@Override
	public ArrayList<Reimbursement> findAllPending() {
		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		PreparedStatement stmt = null;
		Reimbursement re = new Reimbursement();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbursements WHERE status_id = 1;";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
			int id = rs.getInt("id");
			double amount = rs.getDouble("amount");
			Timestamp submitted = rs.getTimestamp("submitted");
			Timestamp resolved = rs.getTimestamp("resolved");
			String description = rs.getString("description");
			int submitter_id = rs.getInt("submitter_id");
			int resolver_id = rs.getInt("resolver_id");
			int status_id = rs.getInt("status_id");
			int type_id = rs.getInt("type_id");
			
			re = new Reimbursement(amount, submitted, resolved, description, submitter_id, resolver_id, status_id, type_id);
			re = new Reimbursement(amount, submitted, resolved, description, re.getSubmitter(), re.getResolver(), re.getStatus(), re.getType());
			log.info(re.toString());
			list.add(re);
			}
			
			if (!stmt.execute()) {
				return null;
			}
		} catch (SQLException ex) {
			log.warn("Unable to retrieve all pending reimbursements", ex);
			return null;
		}
		return list;
	}

	@Override
	public ArrayList<Reimbursement> findAllResolved() {
		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		PreparedStatement stmt = null;
		Reimbursement re = new Reimbursement();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbursements WHERE status_id != 1;";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
			int id = rs.getInt("id");
			double amount = rs.getDouble("amount");
			Timestamp submitted = rs.getTimestamp("submitted");
			Timestamp resolved = rs.getTimestamp("resolved");
			String description = rs.getString("description");
			int submitter_id = rs.getInt("submitter_id");
			int resolver_id = rs.getInt("resolver_id");
			int status_id = rs.getInt("status_id");
			int type_id = rs.getInt("type_id");
			
			re = new Reimbursement(amount, submitted, resolved, description, submitter_id, resolver_id, status_id, type_id);
			re = new Reimbursement(amount, submitted, resolved, description, re.getSubmitter(), re.getResolver(), re.getStatus(), re.getType());
			log.info(re.toString());
			list.add(re);
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
	public ArrayList<Reimbursement> findAllForEmployee(Employee e) {
		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		PreparedStatement stmt = null;
		Reimbursement re = new Reimbursement();
		int eid = e.getId();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbursements WHERE submitter_id = " + eid + ";";
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
			int id = rs.getInt("id");
			double amount = rs.getDouble("amount");
			Timestamp submitted = rs.getTimestamp("submitted");
			Timestamp resolved = rs.getTimestamp("resolved");
			String description = rs.getString("description");
			int submitter_id = rs.getInt("submitter_id");
			int resolver_id = rs.getInt("resolver_id");
			int status_id = rs.getInt("status_id");
			int type_id = rs.getInt("type_id");
			
			re = new Reimbursement(amount, submitted, resolved, description, submitter_id, resolver_id, status_id, type_id);
			re = new Reimbursement(amount, submitted, resolved, description, re.getSubmitter(), re.getResolver(), re.getStatus(), re.getType());
			log.info(re.toString());
			list.add(re);
			}
			
			if (!stmt.execute()) {
				return null;
			}
		} catch (SQLException ex) {
			log.warn("Unable to retrieve all reimbursements for " + e.getUsername(), ex);
			return null;
		}
		return list;
	}

	@Override
	public ArrayList<Reimbursement> findAllPendingForEmployee(Employee e) {
		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		PreparedStatement stmt = null;
		Reimbursement re = new Reimbursement();
		int eid = e.getId();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbursements WHERE submitter_id = " + eid + " AND status_id = 1";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
			int id = rs.getInt("id");
			double amount = rs.getDouble("amount");
			Timestamp submitted = rs.getTimestamp("submitted");
			Timestamp resolved = rs.getTimestamp("resolved");
			String description = rs.getString("description");
			int submitter = rs.getInt("submitter_id");
			int resolver = rs.getInt("resolver_id");
			int status_id = rs.getInt("status_id");
			int type_id = rs.getInt("type_id");
			
			re = new Reimbursement(amount, submitted, resolved, description, submitter, resolver, status_id, type_id);
			re = new Reimbursement(amount, submitted, resolved, description, re.getSubmitter(), re.getResolver(), re.getStatus(), re.getType());
			log.info(re.toString());
			list.add(re);
			}
			
			if (!stmt.execute()) {
				return null;
			}
		} catch (SQLException ex) {
			log.warn("Unable to retrieve all pending reimbursements for " + e.getUsername(), ex);
			return null;
		}
		return list;
	}

	@Override
	public ArrayList<Reimbursement> findAllResolvedForEmployee(Employee e) {
		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		PreparedStatement stmt = null;
		Reimbursement re = new Reimbursement();
		int eid = e.getId();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbursements WHERE submitter_id = " + eid + " AND status_id != 1";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
			int id = rs.getInt("id");
			double amount = rs.getDouble("amount");
			Timestamp submitted = rs.getTimestamp("submitted");
			Timestamp resolved = rs.getTimestamp("resolved");
			String description = rs.getString("description");
			int submitter = rs.getInt("submitter_id");
			int resolver = rs.getInt("resolver_id");
			int status_id = rs.getInt("status_id");
			int type_id = rs.getInt("type_id");
			
			re = new Reimbursement(amount, submitted, resolved, description, submitter, resolver, status_id, type_id);
			re = new Reimbursement(amount, submitted, resolved, description, re.getSubmitter(), re.getResolver(), re.getStatus(), re.getType());
			log.info(re.toString());
			list.add(re);
			}
			
			if (!stmt.execute()) {
				return null;
			}
		} catch (SQLException ex) {
			log.warn("Unable to retrieve all resolved reimbursements for " + e.getUsername(), ex);
			return null;
		}
		return list;
	}

	@Override
	public ArrayList<Reimbursement> findById() {
		PreparedStatement stmt = null;
		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();
		Reimbursement re = new Reimbursement();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbursements WHERE id = " + re.getId() +";";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
			int id = rs.getInt("id");
			double amount = rs.getDouble("amount");
			Timestamp submitted = rs.getTimestamp("submitted");
			Timestamp resolved = rs.getTimestamp("resolved");
			String description = rs.getString("description");
			int submitter_id = rs.getInt("submitter_id");
			int resolver_id = rs.getInt("resolver_id");
			int status_id = rs.getInt("status_id");
			int type_id = rs.getInt("type_id");
			String resolver = "";
			if (resolver_id != 0) {
			resolver = EmployeeService.findByID(resolver_id).get(0).getUsername();
			}
			String type = "";
			String status = "";
			switch(status_id) {
			case 1:
				status = "Pending";
				break;
			case 2:
				status = "Approved";
				break;
			case 3:
				status = "Rejected";
				break;
			default:
				break;
			}
			switch(type_id) {
			case 1:
				type = "Lodging";
				break;
			case 2:
				type = "Travel";
				break;
			case 3:
				type = "Food";
				break;
			case 4:
				type = "Other";
				break;
			default:
				break;
			}
			String submitter = EmployeeService.findByID(submitter_id).get(0).getUsername();
			
			re = new Reimbursement(amount, submitted, resolved, description, submitter_id, submitter, resolver_id, resolver, status_id, type_id, status, type);
			list.add(re);
			if (!stmt.execute()) {
				return null;
	}}
		}catch (SQLException ex) {
			log.warn("Unable to retrieve reimbursement by ID", ex);
			return null;
		}
		return list;
	}
	
//	public static void main(String[] args) { //Initialize two reimbursements.
//		Employee e = EmployeeService.findByID(1);
//	//(double amount, String description, int submitter_id, int status_id, int type_id)
//		Reimbursement re = new Reimbursement(50, "1st Test", 1, 1, 1);
//		insert(e, re);
//		re = new Reimbursement(500, "2nd Test", 1, 1, 2);
//		insert(e, re);
//		
//	}

}
