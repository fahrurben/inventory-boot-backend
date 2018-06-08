package com.kyrosoft.inventory.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kyrosoft.inventory.InventoryException;
import com.kyrosoft.inventory.ServiceException;
import com.kyrosoft.inventory.model.Customer;
import com.kyrosoft.inventory.model.SearchResult;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Customer update(@PathVariable Long id, @RequestBody Customer customer)
        throws InventoryException {

        try {
            logEnter(customer);
            customer.setId(id);
            customer = customerService.update(customer);
        }
        catch(ServiceException|JsonProcessingException e) {
            throw logException(new InventoryException("Error updating user",e));
        }

        logExit();
        return customer;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Customer get(@PathVariable Long id)
        throws InventoryException {

        Customer customer = null;
        try {
            logEnter(customer);
            customer = customerService.get(id);
        }
        catch(ServiceException|JsonProcessingException e) {
            throw logException(new InventoryException("Error get user", e));
        }

        logExit();
        return customer;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public SearchResult<Customer> search(@RequestBody BaseDTO dto)
            throws InventoryException {
        SearchResult<Customer> searchResult = null;

        try {
            logEnter(dto);
            searchResult = customerService.search(dto);
        }
        catch(ServiceException|JsonProcessingException e) {
            throw logException(new InventoryException("Error search user", e));
        }

        logExit();
        return searchResult;
    }
}
