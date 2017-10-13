package com.ashish.org.pojo;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

// entity represents a table
@Entity

public class Customer {

	@Id
	@GeneratedValue
	@Column(name="userId", unique = true, nullable = false)
	private int userId;
	private String firstName;
	private String lastName;
	private String emailId;
	private String userName;
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="address_fk")
	private Address address;
	
	public Customer(String firstName,String lastName,String emailId,String userName,String password){
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.userName = userName;
		this.password = password;
	}
	
	public Customer(){
		
	}
	
	//it is always best practice to keep annotations on getters
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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

	
	
	//mappedBy(how User class is represented in address class)
	//cascade ( if a user is deleted the corresponding address also gets deleted
	//By default fetch is lazy for one-many,many - many mapping
	//If fetch type is eager all the address belong to the user gets loaded 
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
	
	
}
