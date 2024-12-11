package com.backinformal.BackInFormal_Backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backinformal.BackInFormal_Backend.entity.Customer;
import com.backinformal.BackInFormal_Backend.service.ICustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	private ICustomerService custService;
	
	@PostMapping("/add-customer")
	ResponseEntity<?> addCustomer(@RequestBody Customer custObj)
	{
		return custService.addNewCustomerDetails(custObj);
	}
	
	@GetMapping("/all-customers")
	List<Customer> allCustomers()
	{
		return custService.customerList();
	}
}
