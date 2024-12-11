package com.backinformal.BackInFormal_Backend.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

 
public class ItemDTO {

	private long itemId;
	private String itemCode;
	private String itemName;
	private int quantity;
	private double gstRate;
	private double itemPrice;
	private double totalPrice;
	
	public ItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	



	public ItemDTO(long itemId, String itemCode, String itemName, int quantity, double gstRate, double itemPrice,
			double totalPrice) {
		super();
		this.itemId = itemId;
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.quantity = quantity;
		this.gstRate = gstRate;
		this.itemPrice = itemPrice;
		this.totalPrice = totalPrice;
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



	public double getGstRate() {
		return gstRate;
	}



	public void setGstRate(double gstRate) {
		this.gstRate = gstRate;
	}


	public long getItemId() {
		return itemId;
	}


	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	
	
	
	
}
