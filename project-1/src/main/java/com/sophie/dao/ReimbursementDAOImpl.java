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
import com.sophie.util.ConnectionUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO{
	private static Logger log = Logger.getLogger(ReimbursementDAOImpl.class);

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
	
	ReimbursementDAO contains the following methods - 
	public boolean insert(Reimbursement re);
		
	public boolean update(Reimbursement re);
	
	public List<Reimbursement> findAll();
	
	public List<Reimbursement> findAllPending();
	
	public List<Reimbursement> findAllResolved();
	
	public List<Reimbursement> findAllForEmployee(Employee e);
	
	public List<Reimbursement> findAllPendingForEmployee(Employee e);
	
	public List<Reimbursement> findAllResolvedForEmployee(Employee e);
	 */
	
	@Override
	public boolean insert(Reimbursement re) {
		PreparedStatement stmt = null;
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO reimbursements (amount, submitted, description, submitter_id, status_id, type_id) " + 
			"VALUES (?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, re.getAmount());
			stmt.setTimestamp(2, re.getSubmitted());
			stmt.setString(3, re.getDescription());
			stmt.setInt(4, re.getSubmitter_id());
			stmt.setInt(5, re.getStatus_id());
			stmt.setInt(6, re.getType_id());
			
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
	
	TODO: Check for validity against Project 0 update methods!
	*/
		PreparedStatement stmt = null;
		if (e.getRole_id() == 1) {
			log.error("Employee attempted to resolve a reimbursement. How did this happen?");
			return false;
		}
		else { 
			try {
				Connection conn = ConnectionUtil.getConnection();
				String sql = "UPDATE reimbursements " + 
						"SET resolved = ?, resolver_id = ?, status_id = ?, WHERE id =  ?";
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				stmt = conn.prepareStatement(sql);
				stmt.setTimestamp(1, timestamp);
				stmt.setInt(2, e.getId());
				stmt.setInt(3, re.getStatus_id());
				stmt.setInt(4, re.getId());
			
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
	public List<Reimbursement> findAll() {
		List<Reimbursement> list = new ArrayList<Reimbursement>();
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
			int submitter = rs.getInt("submitter");
			int resolver = rs.getInt("resolver");
			int status = rs.getInt("status_id");
			int type = rs.getInt("type_id");
			re = new Reimbursement(id, amount, submitted, resolved, description, submitter, resolver, status, type);
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
	public List<Reimbursement> findAllPending() {
		List<Reimbursement> list = new ArrayList<Reimbursement>();
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
			int submitter = rs.getInt("submitter");
			int resolver = rs.getInt("resolver");
			int status = rs.getInt("status_id");
			int type = rs.getInt("type_id");
			re = new Reimbursement(id, amount, submitted, resolved, description, submitter, resolver, status, type);
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
	public List<Reimbursement> findAllResolved() {
		List<Reimbursement> list = new ArrayList<Reimbursement>();
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
			int submitter = rs.getInt("submitter");
			int resolver = rs.getInt("resolver");
			int status = rs.getInt("status_id");
			int type = rs.getInt("type_id");
			re = new Reimbursement(id, amount, submitted, resolved, description, submitter, resolver, status, type);
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
	public List<Reimbursement> findAllForEmployee(Employee e) {
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		PreparedStatement stmt = null;
		Reimbursement re = new Reimbursement();
		int eid = e.getId();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbursements WHERE submitter = " + eid + ";";
			stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
			int id = rs.getInt("id");
			double amount = rs.getDouble("amount");
			Timestamp submitted = rs.getTimestamp("submitted");
			Timestamp resolved = rs.getTimestamp("resolved");
			String description = rs.getString("description");
			int submitter = rs.getInt("submitter");
			int resolver = rs.getInt("resolver");
			int status = rs.getInt("status_id");
			int type = rs.getInt("type_id");
			re = new Reimbursement(id, amount, submitted, resolved, description, submitter, resolver, status, type);
			list.add(re);
			}
			
			if (!stmt.execute()) {
				return null;
			}
		} catch (SQLException ex) {
			log.warn("Unable to retrieve all reimbursements for the specified employee", ex);
			return null;
		}
		return list;
	}

	@Override
	public List<Reimbursement> findAllPendingForEmployee(Employee e) {
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		PreparedStatement stmt = null;
		Reimbursement re = new Reimbursement();
		int eid = e.getId();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbursements WHERE submitter = " + eid + " AND status_id = 1";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
			int id = rs.getInt("id");
			double amount = rs.getDouble("amount");
			Timestamp submitted = rs.getTimestamp("submitted");
			Timestamp resolved = rs.getTimestamp("resolved");
			String description = rs.getString("description");
			int submitter = rs.getInt("submitter");
			int resolver = rs.getInt("resolver");
			int status = rs.getInt("status_id");
			int type = rs.getInt("type_id");
			re = new Reimbursement(id, amount, submitted, resolved, description, submitter, resolver, status, type);
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
	public List<Reimbursement> findAllResolvedForEmployee(Employee e) {
		List<Reimbursement> list = new ArrayList<Reimbursement>();
		PreparedStatement stmt = null;
		Reimbursement re = new Reimbursement();
		int eid = e.getId();
		try {
			Connection conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM reimbursements WHERE submitter = " + eid + " AND status_id != 1";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
			int id = rs.getInt("id");
			double amount = rs.getDouble("amount");
			Timestamp submitted = rs.getTimestamp("submitted");
			Timestamp resolved = rs.getTimestamp("resolved");
			String description = rs.getString("description");
			int submitter = rs.getInt("submitter");
			int resolver = rs.getInt("resolver");
			int status = rs.getInt("status_id");
			int type = rs.getInt("type_id");
			re = new Reimbursement(id, amount, submitted, resolved, description, submitter, resolver, status, type);
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

}
