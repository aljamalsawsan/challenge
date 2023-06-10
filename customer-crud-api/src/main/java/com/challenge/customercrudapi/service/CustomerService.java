package com.challenge.customercrudapi.service;

import com.challenge.customercrudapi.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<Customer> findById(long paramLong);

    ResponseEntity<Customer> save(Customer paramCustomer);

    ResponseEntity<Customer> updateCustomer(long paramLong, Customer paramCustomer);

    ResponseEntity<HttpStatus> deleteById(long paramLong);
}
