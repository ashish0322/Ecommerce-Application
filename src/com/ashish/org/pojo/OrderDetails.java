package com.ashish.org.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderDetails {

	@Id
	@GeneratedValue
	private long orderDetalisId;
	@ManyToOne
	@JoinColumn(name="orderId")
	private Order order;
	
	private double unitPrice;
	private long productId;
	private String productName;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="sellerName")
	private Seller sellerName;
	private String status;
	
	public OrderDetails(){
		
	}
	
	public OrderDetails(double unitPrice,long productId,Order order,Seller sellerName,
			String productName){
		this.unitPrice = unitPrice;
		this.productId = productId;
		this.order = order;
		this.sellerName = sellerName;
		this.productName = productName;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getOrderDetalisId() {
		return orderDetalisId;
	}

	public void setOrderDetalisId(long orderDetalisId) {
		this.orderDetalisId = orderDetalisId;
	}

	public Seller getSellerName() {
		return sellerName;
	}

	public void setSellerName(Seller sellerName) {
		this.sellerName = sellerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
