package com.backinformal.BackInFormal_Backend.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class InvoiceListPageDetailDTO {

	private String uniqueInvoiceNumber;
	private String custName;
	private LocalDate createdOn;
	private double netTotal;
	private double amtReceived;
	private double amtUnpaid;
	
	public InvoiceListPageDetailDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public InvoiceListPageDetailDTO(String uniqueInvoiceNumber, String custName, LocalDate createdOn, double netTotal,
			double amtReceived, double amtUnpaid) {
		super();
		this.uniqueInvoiceNumber = uniqueInvoiceNumber;
		this.custName = custName;
		this.createdOn = createdOn;
		this.netTotal = netTotal;
		this.amtReceived = amtReceived;
		this.amtUnpaid = amtUnpaid;
	}



	public String getUniqueInvoiceNumber() {
		return uniqueInvoiceNumber;
	}
	public void setUniqueInvoiceNumber(String uniqueInvoiceNumber) {
		this.uniqueInvoiceNumber = uniqueInvoiceNumber;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}



	public LocalDate getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}



	public double getNetTotal() {
		return netTotal;
	}



	public void setNetTotal(double netTotal) {
		this.netTotal = netTotal;
	}



	public double getAmtReceived() {
		return amtReceived;
	}



	public void setAmtReceived(double amtReceived) {
		this.amtReceived = amtReceived;
	}



	public double getAmtUnpaid() {
		return amtUnpaid;
	}



	public void setAmtUnpaid(double amtUnpaid) {
		this.amtUnpaid = amtUnpaid;
	}
	
	
}
