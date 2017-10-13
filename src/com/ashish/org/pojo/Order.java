package com.ashish.org.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="ordersTable")

public class Order {

	@Id
	@GeneratedValue
    private long orderId;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private Customer customer;
	
	private String status;
	private String shipperName;
	
	
	
	private double orderTotal;
	
	private String orderDate;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="order",targetEntity=OrderDetails.class,
			cascade=CascadeType.ALL)
	
	private List<OrderDetails> orderDetails = new ArrayList<OrderDetails>();
	
	
	public Order(){
		
	}
	
	public Order(Customer customer,String status,String shipperName,
			double orderTotal,String orderDate){
		this.customer = customer;
		this.status = status;
		this.shipperName = shipperName;
		
		this.orderTotal = orderTotal;
		this.orderDate = orderDate;
		this.orderDetails = new ArrayList<OrderDetails>();
		
	}

	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}
