package com.sophie.models;

import java.sql.Timestamp;

import com.sophie.services.EmployeeService;

public class Reimbursement {

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
	
	public Reimbursement() {
		super();
	}
	
	
	
	
	
	public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String description,
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
		this.type = "";
		this.status = "";
		switch(status_id) {
		case 1:
			this.status = "Pending";
			break;
		case 2:
			this.status = "Approved";
			break;
		case 3:
			this.status = "Rejected";
			break;
		default:
			break;
		}
		switch(type_id) {
		case 1:
			this.type = "Lodging";
			break;
		case 2:
			this.type = "Travel";
			break;
		case 3:
			this.type = "Food";
			break;
		case 4:
			this.type = "Other";
			break;
		default:
			break;
		}
	}





	public Reimbursement(double amount, Timestamp submitted, Timestamp resolved, String description,
			int submitter_id, String submitter, int resolver_id, String resolver, int status_id, int type_id,
			String status, String type) {
		super();
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



	public Reimbursement(double amount, Timestamp submitted, Timestamp resolved, String description, int submitter_id,
			int resolver_id, int status_id, int type_id) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.submitter_id = submitter_id;
		this.submitter = EmployeeService.findByID(submitter_id).getUsername();
		this.resolver_id = resolver_id;
		if (resolver_id == 0) {
			this.resolver="";
		} else {
		this.resolver = EmployeeService.findByID(resolver_id).getUsername();
		}
		this.status_id = status_id;
		this.type_id = type_id;
		this.type = "";
		this.status = "";
		switch(status_id) {
		case 1:
			this.status = "Pending";
			break;
		case 2:
			this.status = "Approved";
			break;
		case 3:
			this.status = "Rejected";
			break;
		default:
			break;
		}
		switch(type_id) {
		case 1:
			this.type = "Lodging";
			break;
		case 2:
			this.type = "Travel";
			break;
		case 3:
			this.type = "Food";
			break;
		case 4:
			this.type = "Other";
			break;
		default:
			break;
		}
	}
	
	public Reimbursement(double amount, Timestamp submitted, Timestamp resolved, String description, int submitter_id,
			int resolver_id, String status, String type) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.submitter_id = submitter_id;
		this.submitter = EmployeeService.findByID(submitter_id).getUsername();
		this.resolver_id = resolver_id;
		if (resolver_id == 0) {
			this.resolver="";
		} else {
		this.resolver = EmployeeService.findByID(resolver_id).getUsername();
		}
		this.status = status;
		this.type = type;
	}
	
	public Reimbursement(double amount, String description, int submitter_id,
			int status_id, int type_id) {
		super();
		this.amount = amount;
		this.submitted = null;
		this.resolved = null;
		this.description = description;
		this.submitter_id = submitter_id;
		this.submitter = EmployeeService.findByID(submitter_id).getUsername();
		this.resolver_id = 0;
		if (resolver_id == 0) {
			this.resolver="";
		} else {
		this.resolver = EmployeeService.findByID(resolver_id).getUsername();
		}
		this.status_id = status_id;
		this.type_id = type_id;
		this.type = "";
		this.status = "";
		switch(status_id) {
		case 1:
			this.status = "Pending";
			break;
		case 2:
			this.status = "Approved";
			break;
		case 3:
			this.status = "Rejected";
			break;
		default:
			break;
		}
		switch(type_id) {
		case 1:
			this.type = "Lodging";
			break;
		case 2:
			this.type = "Travel";
			break;
		case 3:
			this.type = "Food";
			break;
		case 4:
			this.type = "Other";
			break;
		default:
			break;
		}
	}
	public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String description,
			String submitter, String resolver, int submitter_id, int resolver_id, int status_id, int type_id) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.submitter_id = submitter_id;
		this.submitter = EmployeeService.findByID(submitter_id).getUsername();
		this.resolver_id = resolver_id;
		if (resolver_id == 0) {
			this.resolver="";
		} else {
		this.resolver = EmployeeService.findByID(resolver_id).getUsername();
		}
		this.status_id = status_id;
		this.type_id = type_id;
		this.type = "";
		this.status = "";
		switch(status_id) {
		case 1:
			this.status = "Pending";
			break;
		case 2:
			this.status = "Approved";
			break;
		case 3:
			this.status = "Rejected";
			break;
		default:
			break;
		}
		switch(type_id) {
		case 1:
			this.type = "Lodging";
			break;
		case 2:
			this.type = "Travel";
			break;
		case 3:
			this.type = "Food";
			break;
		case 4:
			this.type = "Other";
			break;
		default:
			break;
		}
	}
	
	public Reimbursement(double amount, String description, String type) {
		super();
		this.amount = amount;
		this.description = description;
		this.type = type;
		this.status_id = 1;
		this.status = "Pending";
		switch(type) {
		case "Lodging":
			status_id = 1;
			break;
		case "Travel":
			status_id = 2;
			break;
		case "Food":
			status_id = 3;
			break;
		default:
			status_id = 4;
			break;
		}
	}
	
	
	public Reimbursement(double amount, Timestamp submitted, Timestamp resolved, String description, String submitter,
			String resolver, String status, String type) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.submitter = submitter;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
		switch(status_id) {
		case 1:
			this.status = "Pending";
			break;
		case 2:
			this.status = "Approved";
			break;
		case 3:
			this.status = "Rejected";
			break;
		default:
			break;
		}
		switch(type_id) {
		case 1:
			this.type = "Lodging";
			break;
		case 2:
			this.type = "Travel";
			break;
		case 3:
			this.type = "Food";
			break;
		case 4:
			this.type = "Other";
			break;
		default:
			break;
		}
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
	public int getResolver_id() {
		return resolver_id;
	}
	public void setResolver_id(int resolver_id) {
		this.resolver_id = resolver_id;
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
	
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	public String getResolver() {
		return resolver;
	}
	public void setResolver(String resolver) {
		this.resolver = resolver;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + resolver_id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + status_id;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + submitter_id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + type_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolver_id != other.resolver_id)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (status_id != other.status_id)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (submitter_id != other.submitter_id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (type_id != other.type_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", submitter=" + submitter + ", resolver=" + resolver + ", status="
				+ status + ", type=" + type + "]";
	}
	
	
	
	
	
}
