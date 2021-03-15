package com.sophie.models;

public class Employee {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email; 
	private String username;
	private String password;
	private int role_id;
	String role;
	
	public Employee() {
		super();
	}
	public Employee(int id, String firstName, String lastName, String email, String username, String password, int role_id, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role_id = role_id;
		switch(role_id) {
		case 1:
			this.role="Employee";
			break;
		case 2:
			this.role="Manager";
			break;
		default:
			this.role="";
			break;
		}
	}
	
	public Employee(int id, String firstName, String lastName, String email, String username, int role_id) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		switch(role_id) {
		case 1:
			this.role="Employee";
			break;
		case 2:
			this.role="Manager";
			break;
		default:
			this.role="";
			break;
		}
	}
	
	public Employee(String firstName, String lastName, String email, String username, String password, int role_id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role_id = role_id;
		switch(role_id) {
		case 1:
			this.role="Employee";
			break;
		case 2:
			this.role="Manager";
			break;
		default:
			this.role="";
			break;
		}
	}
	
	public Employee(String firstName, String lastName, String email, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role_id = 1;
		switch(role_id) {
		case 1:
			this.role="Employee";
			break;
		case 2:
			this.role="Manager";
			break;
		default:
			this.role="";
			break;
		}
	}
	
	public Employee(String firstName, String lastName, String email, String username, String password, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public Employee(int id, String firstName, String lastName, String email, String username, String password,
			int role_id) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		switch(role_id) {
		case 1:
			this.role="Employee";
			break;
		case 2:
			this.role="Manager";
			break;
		default:
			this.role="";
			break;
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + role_id;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Employee other = (Employee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (role_id != other.role_id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", username="
				+ username + ", password=" + password + ", role=" + role + "]";
	}
	

	
	
	
	
	
	
}
