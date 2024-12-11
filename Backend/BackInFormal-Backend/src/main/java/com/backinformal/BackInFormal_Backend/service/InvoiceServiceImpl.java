package com.backinformal.BackInFormal_Backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backinformal.BackInFormal_Backend.DTO.DeleteItemsDTO;
import com.backinformal.BackInFormal_Backend.DTO.InvoiceDetailsDTO;
import com.backinformal.BackInFormal_Backend.DTO.InvoiceListPageDetailDTO;
import com.backinformal.BackInFormal_Backend.DTO.ItemDTO;
import com.backinformal.BackInFormal_Backend.entity.Customer;
import com.backinformal.BackInFormal_Backend.entity.InvoiceItemsList;
import com.backinformal.BackInFormal_Backend.entity.ItemData;
import com.backinformal.BackInFormal_Backend.entity.MainInvoice;
import com.backinformal.BackInFormal_Backend.repository.CustomerRepository;
import com.backinformal.BackInFormal_Backend.repository.InvoiceItemsListRepository;
import com.backinformal.BackInFormal_Backend.repository.ItemRepositroy;
import com.backinformal.BackInFormal_Backend.repository.MainInvoiceRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InvoiceServiceImpl implements IInvoiceService{

	@Autowired
	private MainInvoiceRepository invoiceRepo;
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private InvoiceItemsListRepository invoiceItemListRepo;
	
	@Autowired
	private ItemRepositroy itemsRepo;

	@Override
	public ResponseEntity<?> addInvoiceData(InvoiceDetailsDTO invoiceObj) {
		// TODO Auto-generated method stub
		//Customer custObj = custRepo.findByCustEmail(invoiceObj.getCustomer().getCustEmail()).get();
		Customer custObj = invoiceObj.getCustomer();
		
		InvoiceItemsList invcItemList = new InvoiceItemsList();
		invcItemList.setCustId(custObj);
		
		for(ItemDTO it: invoiceObj.getItemsList())
		{
			ItemData itemObj = new ItemData();
			itemObj.setItemCode(it.getItemCode());
			itemObj.setItemName(it.getItemName());
			itemObj.setGstRate(it.getGstRate());
			itemObj.setQuantity(it.getQuantity());
			itemObj.setItemPrice(it.getItemPrice());
			itemObj.setTotalPrice(it.getTotalPrice());
			itemObj.setInvoiceId(invcItemList);
			invcItemList.getItemDataList().add(itemObj);
		
		}
		
		InvoiceItemsList addedItemsObj= invoiceItemListRepo.save(invcItemList);
		
		MainInvoice invcAdd = new MainInvoice();
		invcAdd.setInvoiceListId(addedItemsObj);
		invcAdd.setCustomer(custObj);
		invcAdd.setSubTotal(invoiceObj.getSubTotal());
		invcAdd.setNetTotal(invoiceObj.getNetTotal());
		invcAdd.setAmtReceived(invoiceObj.getAmtReceived());
		invcAdd.setAmtUnpaid(invoiceObj.getAmtUnpaid());
		invcAdd.setRemarkNote(invoiceObj.getRemarkNote());
		
		LocalDate tod = LocalDate.now();
	    String month = (tod.getMonthValue() <10)?"0"+tod.getMonthValue(): String.valueOf(tod.getMonthValue())  ;
	    String date = (tod.getDayOfMonth()<10)?"0"+tod.getDayOfMonth():String.valueOf(tod.getDayOfMonth());
	    int year = tod.getYear() %100;
	    
		String autoInvoiceNumCreation = year+month+date+addedItemsObj.getInvoiceItemId();
		
		invcAdd.setUniqueInvoiceNumber(autoInvoiceNumCreation);
		MainInvoice invoiceCreated= invoiceRepo.save(invcAdd);
		
		return new ResponseEntity<>(invoiceCreated.getUniqueInvoiceNumber(), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> getAllInvoices() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(invoiceRepo.findAll());
	}

	@Override
	public ResponseEntity<?> displayInvoiceNumberAndCustName() {
		// TODO Auto-generated method stub
		List<InvoiceListPageDetailDTO> list= invoiceRepo.findAllInvoiceNumAndCustName();
		return ResponseEntity.ok(list);
	}

	@Override
	public ResponseEntity<?> getInvoiceDetailByUniqueCode(String invoiceNum) {
		// TODO Auto-generated method stub
		MainInvoice fetchInvoice= invoiceRepo.findByUniqueInvoiceNumber(invoiceNum);
		return new ResponseEntity<>(fetchInvoice, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<?> deleteItemList(DeleteItemsDTO delItemsList) {
		// TODO Auto-generated method stub
		itemsRepo.deleteAllById(delItemsList.getItemsIDsList());
		return ResponseEntity.ok("Deleted Successfully");
	}

	@Override
	public ResponseEntity<?> updateInvoiceByUniqueCode(String invoiceNum, InvoiceDetailsDTO invoiceObj) {
		MainInvoice fetchInvoice= invoiceRepo.findByUniqueInvoiceNumber(invoiceNum);
		
		fetchInvoice.setCustomer(invoiceObj.getCustomer());
		List<ItemData> oldList = fetchInvoice.getInvoiceListId().getItemDataList();
		
		int i=0;
		for(ItemDTO it: invoiceObj.getItemsList())
		{
			oldList.get(i).setItemId(it.getItemId());
			oldList.get(i).setItemCode(it.getItemCode());
			oldList.get(i).setItemName(it.getItemName());	
			oldList.get(i).setGstRate(it.getGstRate());
			oldList.get(i).setQuantity(it.getQuantity());
			oldList.get(i).setItemPrice(it.getItemPrice());
			oldList.get(i).setTotalPrice(it.getTotalPrice());
			i++;
		
		}
		
		fetchInvoice.setSubTotal(invoiceObj.getSubTotal());
		fetchInvoice.setNetTotal(invoiceObj.getNetTotal());
		fetchInvoice.setAmtReceived(invoiceObj.getAmtReceived());
		fetchInvoice.setAmtUnpaid(invoiceObj.getAmtUnpaid());
		fetchInvoice.setRemarkNote(invoiceObj.getRemarkNote());
		
		invoiceRepo.save(fetchInvoice);
		
		return new ResponseEntity<>("Invoice Updated Successfully",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteInvoiceByUniqueCode(String invoiceNum) {
		
		MainInvoice fetchInvoice= invoiceRepo.findByUniqueInvoiceNumber(invoiceNum);
		invoiceRepo.delete(fetchInvoice);
		return new ResponseEntity<>("Invoice Deleted Successfully",HttpStatus.OK);
	}
}
