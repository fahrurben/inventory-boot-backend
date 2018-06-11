package com.kyrosoft.inventory.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyrosoft.inventory.InventoryException;
import com.kyrosoft.inventory.ServiceException;
import com.kyrosoft.inventory.model.Customer;
import com.kyrosoft.inventory.model.IdentifiableEntity;
import com.kyrosoft.inventory.model.SearchResult;
import com.kyrosoft.inventory.model.ServiceContext;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import com.kyrosoft.inventory.service.BaseService;
import com.kyrosoft.inventory.service.CustomerService;
import com.kyrosoft.inventory.service.VendorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public abstract class BaseController {

    @Autowired
    CustomerService customerService;

    @Autowired
    VendorService vendorService;

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

    protected <T extends IdentifiableEntity,U extends BaseService> void
        logExceptionWrapper(
            U service,
            T entity,
            CrudService crudService)
        throws InventoryException
    {

        try {
            logEnter(entity);
            crudService.doCrud(service,entity);
        }
        catch(ServiceException|JsonProcessingException e) {
            throw logException(new InventoryException("Error",e));
        }

        logExit();
    }

    protected <T extends IdentifiableEntity,U extends BaseService> T getEntity(U service, Long id)
            throws InventoryException {

        T entity = null;

        try {
            logEnter(null);
            entity = (T) service.get(id);
        }
        catch(ServiceException |JsonProcessingException e) {
            throw logException(new InventoryException("Error get user",e));
        }

        logExit();

        return entity;
    }

    protected <T extends IdentifiableEntity,U extends BaseService, V extends BaseDTO>
        SearchResult<T> searchEntities(U service, V dto)
            throws InventoryException {

        SearchResult<T> searchResult = null;

        try {
            logEnter(dto);
            searchResult = service.search(dto);
        }
        catch(ServiceException|JsonProcessingException e) {
            throw logException(new InventoryException("Error search user", e));
        }

        logExit();

        return searchResult;
    }
}
