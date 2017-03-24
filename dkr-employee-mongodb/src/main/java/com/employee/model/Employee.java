package com.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="employees")
public class Employee {

	@Id
	private String id; 
	
	private String email;
	private String fullName;
	private String managerEmail;
	
	private int totalPointsEarned;
	
	private int availablePoints;
	
	public Employee(String id, String email, String fullName, String managerEmail, int totalPointsEarned,
			int availablePoints) {
		super();
		this.id = id;
		this.email = email;
		this.fullName = fullName;
		this.managerEmail = managerEmail;
		this.totalPointsEarned = totalPointsEarned;
		this.availablePoints = availablePoints;
	}
	
	

	public Employee(String email, String fullName, String managerEmail, int totalPointsEarned, int availablePoints) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.managerEmail = managerEmail;
		this.totalPointsEarned = totalPointsEarned;
		this.availablePoints = availablePoints;
	}



	public Employee() {
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getFullName() {
		return fullName;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public int getTotalPointsEarned() {
		return totalPointsEarned;
	}

	public int getAvailablePoints() {
		return availablePoints;
	}
	
	
}
