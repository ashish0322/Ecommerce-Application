package com.ashish.org.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import com.ashish.org.pojo.Customer;



@Entity
public class Address {

	@Id
	@GeneratedValue
	@Column(name="address_pk", unique = true, nullable = false)
	private int addressId;
	private String street;
	private String city;
	private String state;
	private String country;
    private String contactNumber;
	private String pinCode;
	
	@OneToOne(mappedBy = "address",cascade = CascadeType.ALL)
	private Customer customer;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}
	
	public Address(String street, String city,String state,String country,
			String contactNumber,String pinCode){
		this.street= street;
		this.city = city;
		this.state=state;
		this.country=country;
		
		this.contactNumber=contactNumber;
		this.pinCode=pinCode;
	}
	
	
	
	
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}
