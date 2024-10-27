package org.example.kuy.repository;

import org.example.kuy.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Page<Customer> findCustomerByCustomerNameContaining(Pageable pageable, String param);
}