package com.challenge.customercrudapi;

import com.challenge.customercrudapi.controller.CustomerController;
import com.challenge.customercrudapi.model.Customer;
import com.challenge.customercrudapi.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CustomerControllerTest {
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createCustomerTest() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        Mockito.when(this.customerService.save(customer)).thenReturn(new ResponseEntity(customer, HttpStatus.CREATED));
        ResponseEntity<Customer> response = this.customerController.createCustomer(customer);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(customer, response.getBody());
        ((CustomerService) Mockito.verify(this.customerService, Mockito.times(1))).save(customer);
    }

    @Test
    public void getCustomerByIdTest() {
        long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("John Doe");
        Mockito.when(this.customerService.findById(customerId)).thenReturn(new ResponseEntity(customer, HttpStatus.CREATED));
        ResponseEntity<Customer> response = this.customerController.getCustomerById(customerId);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(customer, response.getBody());
        Mockito.verify(this.customerService, Mockito.times(1)).findById(customerId);
    }

    @Test
    public void updateCustomerTest() {
        long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("John Doe");
        Mockito.when(this.customerService.updateCustomer(customerId, customer)).thenReturn(new ResponseEntity(customer, HttpStatus.OK));
        ResponseEntity<Customer> response = this.customerController.updateCustomer(customerId, customer);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(customer, response.getBody());
        Mockito.verify(this.customerService, Mockito.times(1)).updateCustomer(customerId, customer);
    }

    @Test
    public void deleteCustomerTest() {
        long customerId = 1L;
        Mockito.when(this.customerService.deleteById(customerId)).thenReturn(new ResponseEntity(HttpStatus.NO_CONTENT));
        ResponseEntity<HttpStatus> response = this.customerController.deleteCustomer(customerId);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Mockito.verify(this.customerService, Mockito.times(1)).deleteById(customerId);
    }
}
