package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.InventoryException;
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

        logExceptionWrapper(
                customerService,
                customer,
                (service, entity) -> {
                    service.create(entity);
                });

        return customer;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Customer update(@PathVariable Long id, @RequestBody Customer customer)
        throws InventoryException {

        logExceptionWrapper(
                customerService,
                customer,
                (service, entity) -> {
                    entity.setId(id);
                    service.update(entity);
                });

        return customer;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Customer get(@PathVariable Long id)
        throws InventoryException {

        Customer customer = null;

        customer = getEntity(customerService, id);

        return customer;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public SearchResult<Customer> search(@RequestBody BaseDTO dto)
            throws InventoryException {
        SearchResult<Customer> searchResult = null;

        searchResult = searchEntities(customerService, dto);

        return searchResult;
    }
}
