package com.ashish.org.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GiftVoucher {

	@Id
	@GeneratedValue
	private int voucherId;
	
	@Column(name="voucherCode", unique=true, nullable=false)
	private String voucherCode;
	
	public GiftVoucher(){
		
	}
	
	public GiftVoucher(String voucherCode){
		this.voucherCode = voucherCode;
	}

	public int getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}
	
	
}
