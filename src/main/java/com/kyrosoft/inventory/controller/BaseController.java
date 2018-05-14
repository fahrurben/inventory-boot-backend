package com.kyrosoft.inventory.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyrosoft.inventory.InventoryException;
import com.kyrosoft.inventory.model.ServiceContext;
import com.kyrosoft.inventory.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public abstract class BaseController {

    @Autowired
    CustomerService customerService;

    protected static final Logger logger = Logger.getLogger("InventoryBackend");

    protected String api = "";

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected <T> void logEnter(T bodyObj) throws JsonProcessingException {
        String username = ServiceContext.getCurrentUser();
        String body = bodyObj != null ? objectMapper.writeValueAsString(bodyObj) : "";

        String log = String.format("[enter]POST:%s;user:%s,body:%s", api, username, body);
        logger.info(log);
    }

    protected <T> void logExit() {
        String username = ServiceContext.getCurrentUser();

        String log = String.format("[exit]POST:%s;user:%s", api, username);
        logger.info(log);
    }

    protected <T> InventoryException logException(InventoryException e) {
        String username = ServiceContext.getCurrentUser();

        String log = String.format("[error]POST:%s;user:%s", api, username);
        logger.error(log, e);
        return e;
    }
}
