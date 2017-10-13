package com.ashish.org.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sellerTable")
public class Seller{

	@Id
	@GeneratedValue
	@Column(name="employeeId", unique = true, nullable = false)
	private int sellerId;
	
	
	private String firstName;
	private String lastName;
	private String emailId;
	private String userName;
	private String password;
	
	
	public Seller(){
		
	}
	
	public Seller(String firstName,String lastName,String emailId,String userName,String password){
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.userName = userName;
		this.password = password;
	}
	
	
	
	public String getFirstName() {
		return firstName;
	}

	

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
