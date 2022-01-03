package com.elassriyoussef.customerqueryside.contorllers;

import com.elassriyoussef.customerqueryside.entities.Customer;
import com.youssefelassri.coreapi.query.GetAllCustomersQuery;
import com.youssefelassri.coreapi.query.GetCustomerByIdQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/customers/query")
public class CustomerQueryController {
    private QueryGateway queryGateway;

    @GetMapping("/all")
    public CompletableFuture<List<Customer>> allCustomers(){
        return queryGateway.query(new GetAllCustomersQuery(), ResponseTypes.multipleInstancesOf(Customer.class));
    }

    @GetMapping("/byId/{id}")
    public CompletableFuture<Customer> getCustomer(@PathVariable String id){
        return queryGateway.query(new GetCustomerByIdQuery(id), ResponseTypes.instanceOf(Customer.class));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> response =
                new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
