package com.backinformal.BackInFormal_Backend.service;

import org.springframework.http.ResponseEntity;

import com.backinformal.BackInFormal_Backend.DTO.DeleteItemsDTO;
import com.backinformal.BackInFormal_Backend.DTO.InvoiceDetailsDTO;

public interface IInvoiceService {

	ResponseEntity<?> addInvoiceData(InvoiceDetailsDTO invoiceObj);

	ResponseEntity<?> getAllInvoices();

	ResponseEntity<?> displayInvoiceNumberAndCustName();

	ResponseEntity<?> getInvoiceDetailByUniqueCode(String invoiceNum);

	ResponseEntity<?> deleteItemList(DeleteItemsDTO delItemsList);

	ResponseEntity<?> updateInvoiceByUniqueCode(String invoiceNum, InvoiceDetailsDTO invoiceObj);

	ResponseEntity<?> deleteInvoiceByUniqueCode(String invoiceNum);

}
