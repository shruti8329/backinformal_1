package com.backinformal.BackInFormal_Backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "itemmaster")
public class ItemData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_Id")
	private long itemId;
	
	@Column(name = "item_code")
	private String itemCode;
	
	@Column(name = "item_name", nullable = false)
	private String itemName;
	
	@Column(name = "item_quantity", nullable = false)
	private int quantity;
	
	@Column(name = "item_price", nullable = false)
	private double itemPrice;
	
	@Column(name = "total_price", nullable = false)
	private double totalPrice;
	
	@Column(name = "gst_rate")
	private double gstRate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "invoice_id")
	private InvoiceItemsList invoiceId;

	

	public ItemData(long itemId, String itemCode, String itemName, int quantity, double itemPrice, double totalPrice,
			double gstRate, InvoiceItemsList invoiceId) {
		super();
		this.itemId = itemId;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
		this.totalPrice = totalPrice;
		this.gstRate = gstRate;
		this.invoiceId = invoiceId;
	}

	public ItemData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public InvoiceItemsList getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(InvoiceItemsList invoiceId) {
		this.invoiceId = invoiceId;
	}

	public double getGstRate() {
		return gstRate;
	}

	public void setGstRate(double gstRate) {
		this.gstRate = gstRate;
	}
	
	
	
}
