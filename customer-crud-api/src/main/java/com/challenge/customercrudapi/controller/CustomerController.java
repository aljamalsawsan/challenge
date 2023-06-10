package com.challenge.customercrudapi.controller;

import com.challenge.customercrudapi.model.Customer;
import com.challenge.customercrudapi.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @CrossOrigin(origins = {"http://localhost:3000"})
    @GetMapping({"/customers/{id}"})
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
        log.debug("START getCustomerById : {}", Long.valueOf(id));
        return this.customerService.findById(id);
    }

    @CrossOrigin(origins = {"http://localhost:3000"})
    @PostMapping({"/customers"})
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer createCustomer) {
        log.debug("START createCustomer : {}", createCustomer);
        return this.customerService.save(createCustomer);
    }

    @CrossOrigin(origins = {"*"})
    @PutMapping({"/customers/{id}"})
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        log.debug("START updateCustomer, id : {}, entity: {}", Long.valueOf(id), customer);
        return this.customerService.updateCustomer(id, customer);
    }

    @CrossOrigin(origins = {"*"})
    @DeleteMapping({"/customers/{id}"})
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id) {
        log.debug("START deleteCustomer : {}", Long.valueOf(id));
        return this.customerService.deleteById(id);
    }
}

