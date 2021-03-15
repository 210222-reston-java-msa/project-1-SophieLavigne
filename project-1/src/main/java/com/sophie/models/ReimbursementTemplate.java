package com.sophie.models;

import java.sql.Timestamp;

public class ReimbursementTemplate {

	private int id;
	private double amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private int submitter_id;
	String submitter;
	private int resolver_id;
	String resolver;
	private int status_id;
	private int type_id;
	private String status;
	private String type;
	
	ReimbursementTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public ReimbursementTemplate(int id, double amount, Timestamp submitted, Timestamp resolved, String description,
			int submitter_id, String submitter, int resolver_id, String resolver, int status_id, int type_id,
			String status, String type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.submitter_id = submitter_id;
		this.submitter = submitter;
		this.resolver_id = resolver_id;
		this.resolver = resolver;
		this.status_id = status_id;
		this.type_id = type_id;
		this.status = status;
		this.type = type;
	}
	
	



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSubmitter_id() {
		return submitter_id;
	}

	public void setSubmitter_id(int submitter_id) {
		this.submitter_id = submitter_id;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public int getResolver_id() {
		return resolver_id;
	}

	public void setResolver_id(int resolver_id) {
		this.resolver_id = resolver_id;
	}

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}


