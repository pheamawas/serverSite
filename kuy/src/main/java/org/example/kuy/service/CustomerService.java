package org.example.kuy.service;

import org.example.kuy.entity.Customer;
import org.example.kuy.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Customer getCustomer(Integer customerNumber) {
        return customerRepository.findById(customerNumber).orElse(null);
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
    public Customer createCustomer(Customer customer) {
        Customer existingCustomer = getCustomer(customer.getId());
        if (existingCustomer != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Customer ID %s already exists.", customer.getId()));
        }
        return customerRepository.save(customer);
    }
    public Page<Customer> findCustomerByName(Pageable pageable, String param) {
        return customerRepository.findCustomerByCustomerNameContaining(pageable, param);
    }
    public Customer deleteCustomer(Integer customerNumber) {
        Customer existingCustomer = getCustomer(customerNumber);
        if (existingCustomer == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Customer ID %s not found.", customerNumber));
        }
        customerRepository.delete(existingCustomer);
        return existingCustomer;
    }
    public Customer updateCustomer(Customer customer) {
        Customer existingCustomer = getCustomer(customer.getId());
        if (existingCustomer == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Customer ID %s not found.", customer.getId()));
        }
        customerRepository.save(customer);
        return existingCustomer;
    }}
