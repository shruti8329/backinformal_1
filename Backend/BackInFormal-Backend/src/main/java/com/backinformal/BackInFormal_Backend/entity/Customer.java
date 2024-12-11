package com.backinformal.BackInFormal_Backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customermaster")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_Id")
	private long customerId;
	
	@Column(name = "customer_name", nullable = false)
	private String custName;
	
	@Column(name = "cust_mobile", nullable = false,unique = true)
	private String custMobile;
	
	@Column(name = "cust_email")
	private String custEmail;
	
	@Column(name = "cust_GSTIN")
	private String custGSTIN;
	
	@Column(name = "cust_PAN")
	private String custPAN;
	
	@Column(name = "cust_Address", nullable = false)
	private String custAddress;

	

	public Customer(long customerId, String custName, String custMobile, String custEmail, String custGSTIN,
			String custPAN, String custAddress) {
		super();
		this.customerId = customerId;
		this.custName = custName;
		this.custMobile = custMobile;
		this.custEmail = custEmail;
		this.custGSTIN = custGSTIN;
		this.custPAN = custPAN;
		this.custAddress = custAddress;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustGSTIN() {
		return custGSTIN;
	}

	public void setCustGSTIN(String custGSTIN) {
		this.custGSTIN = custGSTIN;
	}

	public String getCustPAN() {
		return custPAN;
	}

	public void setCustPAN(String custPAN) {
		this.custPAN = custPAN;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}
	
	
	
}
