package com.elassriyoussef.customerqueryside.services;

import com.elassriyoussef.customerqueryside.entities.Customer;
import com.elassriyoussef.customerqueryside.repositories.CustomerRepository;
import com.youssefelassri.coreapi.events.CustomerCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class CustomerEventHandler {

    private CustomerRepository customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event){
        customerRepository.save(new Customer(event.getId(), event.getName(), event.getEmail()));
        log.info("************* customer created");
    }

}
