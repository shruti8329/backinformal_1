package com.backinformal.BackInFormal_Backend.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoice_items_list")
public class InvoiceItemsList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_items_id")
	private long invoiceItemId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cust_id")
	private Customer custId;
	
	@CreationTimestamp
	private LocalDate createdOn;
	
	@OneToMany(mappedBy = "invoiceId",cascade = CascadeType.ALL)
	private List<ItemData> itemDataList = new ArrayList<ItemData>();

	public InvoiceItemsList(long invoiceItemId, Customer custId, LocalDate createdOn, List<ItemData> itemDataList) {
		super();
		this.invoiceItemId = invoiceItemId;
		this.custId = custId;
		this.createdOn = createdOn;
		this.itemDataList = itemDataList;
	}

	public InvoiceItemsList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getInvoiceItemId() {
		return invoiceItemId;
	}

	public void setInvoiceItemId(long invoiceItemId) {
		this.invoiceItemId = invoiceItemId;
	}

	public Customer getCustId() {
		return custId;
	}

	public void setCustId(Customer custId) {
		this.custId = custId;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public List<ItemData> getItemDataList() {
		return itemDataList;
	}

	public void setItemDataList(List<ItemData> itemDataList) {
		this.itemDataList = itemDataList;
	}
	
	
}
