package com.backinformal.BackInFormal_Backend.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.backinformal.BackInFormal_Backend.entity.Customer;

public interface ICustomerService {

	ResponseEntity<?> addNewCustomerDetails(Customer custObj);

	List<Customer> customerList();

}
