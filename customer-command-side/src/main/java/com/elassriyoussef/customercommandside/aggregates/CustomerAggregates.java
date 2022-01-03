package com.elassriyoussef.customercommandside.aggregates;

import com.youssefelassri.coreapi.commands.CreateCustomerCommand;
import com.youssefelassri.coreapi.events.CustomerCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;



@Aggregate
@Slf4j
public class CustomerAggregates {
    @AggregateIdentifier
    private String customerId;
    private String name;
    private String email;

    public CustomerAggregates() {

    }

    @CommandHandler
    public CustomerAggregates(CreateCustomerCommand command) {
        log.info("CreateCustomerCommand received ==> "+command.toString());
        AggregateLifecycle.apply(new CustomerCreatedEvent(
                command.getId(),
                command.getName(),
                command.getEmail()
        ));
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event){
        log.info("CustomerCreatedEvent occurred ==> "+event.toString());
        this.customerId=event.getId();
        this.name = event.getName();
        this.email = event.getEmail();
    }

}
