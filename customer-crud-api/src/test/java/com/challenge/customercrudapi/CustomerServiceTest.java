package com.challenge.customercrudapi;

import com.challenge.customercrudapi.model.Customer;
import com.challenge.customercrudapi.model.NumVerifyResponse;
import com.challenge.customercrudapi.repository.CustomerRepository;
import com.challenge.customercrudapi.service.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class CustomerServiceTest {
    private final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    private final CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);

    @InjectMocks
    private CustomerServiceImpl customerService = new CustomerServiceImpl(this.customerRepository, "http://localhost:8999/api/verifyNumber/", this.restTemplate);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createCustomerTest() {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setNumber("123456789");
        NumVerifyResponse response = new NumVerifyResponse();
        response.setValid(true);
        response.setCountryCode("CC");
        response.setCountryName("CN");
        Mockito.when(this.restTemplate.getForObject("http://localhost:8999/api/verifyNumber/123456789", NumVerifyResponse.class, new Object[0])).thenReturn(response);
        Mockito.when(this.customerRepository.save(customer)).thenReturn(customer);
        ResponseEntity<Customer> createdCustomer = this.customerService.save(customer);
        Assertions.assertEquals(HttpStatus.CREATED, createdCustomer.getStatusCode());
        Assertions.assertEquals("John Doe", ((Customer) createdCustomer.getBody()).getName());
        Mockito.verify(this.customerRepository, Mockito.times(1));
    }

    @Test
    public void getCustomerByIdTest() {
        long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("John Doe");
        customer.setNumber("123456789");
        NumVerifyResponse response = new NumVerifyResponse();
        response.setValid(true);
        response.setCountryCode("CC");
        response.setCountryName("CN");
        Mockito.when(this.restTemplate.getForObject("http://localhost:8999/api/verifyNumber/123456789", NumVerifyResponse.class, new Object[0])).thenReturn(response);
        Mockito.when(this.customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        ResponseEntity<Customer> retrievedCustomer = this.customerService.findById(customerId);
        Assertions.assertEquals(customerId, retrievedCustomer.getBody().getId());
        Assertions.assertEquals("John Doe", retrievedCustomer.getBody().getName());
        Mockito.verify(this.customerRepository, Mockito.times(1)).findById(customerId);
    }

    @Test
    public void updateCustomerTest() {
        long customerId = 1L;
        Customer existingCustomer = new Customer();
        existingCustomer.setId(customerId);
        existingCustomer.setName("John Doe");
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(customerId);
        updatedCustomer.setName("Jane Smith");
        Mockito.when(this.customerRepository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        Mockito.when(this.customerRepository.save(updatedCustomer)).thenReturn(updatedCustomer);
        ResponseEntity<Customer> modifiedCustomer = this.customerService.updateCustomer(customerId, updatedCustomer);
        Assertions.assertEquals(HttpStatus.OK, modifiedCustomer.getStatusCode());
        Mockito.verify(this.customerRepository, Mockito.times(2));
    }

    @Test
    public void deleteCustomerTest() {
        long customerId = 1L;
        this.customerService.deleteById(customerId);
        Mockito.verify(this.customerRepository, Mockito.times(1)).deleteById(customerId);
    }
}

