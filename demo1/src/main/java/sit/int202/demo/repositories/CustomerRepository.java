package sit.int202.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int202.demo.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}