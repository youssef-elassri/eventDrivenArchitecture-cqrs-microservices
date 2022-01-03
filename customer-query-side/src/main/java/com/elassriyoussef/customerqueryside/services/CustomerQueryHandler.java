package com.elassriyoussef.customerqueryside.services;

import com.elassriyoussef.customerqueryside.entities.Customer;
import com.elassriyoussef.customerqueryside.repositories.CustomerRepository;
import com.youssefelassri.coreapi.events.CustomerCreatedEvent;
import com.youssefelassri.coreapi.query.GetAllCustomersQuery;
import com.youssefelassri.coreapi.query.GetCustomerByIdQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerQueryHandler {

    private CustomerRepository customerRepository;

    @QueryHandler
    public List<Customer> Allcustomers(GetAllCustomersQuery query){
        return  customerRepository.findAll();
    }

    @QueryHandler
    public Customer customerById(GetCustomerByIdQuery query){
        return  customerRepository.findById(query.getId()).orElseThrow(()-> new RuntimeException("Customer Not found"));
    }

}
