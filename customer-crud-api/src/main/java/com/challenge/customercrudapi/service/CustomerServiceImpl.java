package com.challenge.customercrudapi.service;

import com.challenge.customercrudapi.model.Customer;
import com.challenge.customercrudapi.model.NumVerifyResponse;
import com.challenge.customercrudapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    private final String thirdPartyURL;

    private final RestTemplate restTemplate;

    public CustomerServiceImpl(CustomerRepository customerRepository, @Value("${thirdParty.url}") String thirdPartyURL, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.thirdPartyURL = thirdPartyURL;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Customer> findById(long id) {
        Customer customer = getCustomer(id);
        try {
            NumVerifyResponse response = getNumberInfo(customer.getNumber());
            if (response.isValid()) {
                customer.setCountryName(response.getCountryName());
                customer.setCountryCode(response.getCountryCode());
                customer.setCarrier(response.getCarrier());
                return new ResponseEntity<>(customer, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customer);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Customer> save(Customer customer) {
        try {
            NumVerifyResponse response = getNumberInfo(customer.getNumber());
            if (response.isValid()) {
                Customer _customer = new Customer(customer.getName(), customer.getAddress(), customer.getNumber());
                this.customerRepository.save(_customer);
                _customer.setCountryCode(response.getCountryCode());
                _customer.setCountryName(response.getCountryName());
                _customer.setCarrier(response.getCarrier());
                return new ResponseEntity<>(_customer, HttpStatus.CREATED);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customer);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private NumVerifyResponse getNumberInfo(String number) {
        return this.restTemplate.getForObject(this.thirdPartyURL + number, NumVerifyResponse.class, new Object[0]);
    }

    public ResponseEntity<Customer> updateCustomer(long id, Customer customer) {
        Customer customerData = getCustomer(id);
        try {
            NumVerifyResponse response = getNumberInfo(customer.getNumber());
            if (response.isValid()) {
                customerData.setName(customer.getName());
                customerData.setAddress(customer.getAddress());
                customerData.setNumber(customer.getNumber());
                customerData.setCountryName(response.getCountryName());
                customerData.setCountryCode(response.getCountryCode());
                customerData.setCarrier(response.getCarrier());
                return new ResponseEntity<>(this.customerRepository.save(customerData), HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customer);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Customer getCustomer(long id) {
        return this.customerRepository.findById(id).get();
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteById(long id) {
        try {
            this.customerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
