package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseController {

    @Autowired
    CustomerService customerService;
}
