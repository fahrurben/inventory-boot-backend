package com.kyrosoft.inventory.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kyrosoft.inventory.InventoryException;
import com.kyrosoft.inventory.ServiceException;
import com.kyrosoft.inventory.model.Customer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("api/customers")
public class CustomerController extends BaseController {

    @PostConstruct
    public void construct() throws Exception {
        api = "api/customers";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Customer create(@RequestBody Customer customer)
            throws InventoryException {

        try {
            logEnter(customer);
            customerService.create(customer);
        }
        catch(ServiceException|JsonProcessingException e) {
            throw logException(new InventoryException("Error creating user",e));
        }

        logExit();
        return customer;
    }
}
