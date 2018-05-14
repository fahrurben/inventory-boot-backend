package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.InventoryException;
import com.kyrosoft.inventory.model.Customer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customers")
public class CustomerController extends BaseController {

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Customer create(@RequestBody Customer customer)
            throws InventoryException {
        customerService.create(customer);
        return customer;
    }
}
