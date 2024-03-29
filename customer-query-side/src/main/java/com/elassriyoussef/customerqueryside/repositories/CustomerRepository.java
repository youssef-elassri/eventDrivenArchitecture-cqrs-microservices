package com.elassriyoussef.customerqueryside.repositories;

import com.elassriyoussef.customerqueryside.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
