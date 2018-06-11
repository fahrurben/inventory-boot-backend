package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.InventoryException;
import com.kyrosoft.inventory.model.Vendor;
import com.kyrosoft.inventory.model.SearchResult;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("api/vendors")
public class VendorController extends BaseController {

    @PostConstruct
    public void construct() throws Exception {
        api = "api/vendors";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Vendor create(@RequestBody Vendor vendor)
            throws InventoryException {

        logExceptionWrapper(
                vendorService,
                vendor,
                (service, entity) -> {
                    service.create(entity);
                });

        return vendor;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Vendor update(@PathVariable Long id, @RequestBody Vendor vendor)
            throws InventoryException {

        logExceptionWrapper(
                vendorService,
                vendor,
                (service, entity) -> {
                    entity.setId(id);
                    service.update(entity);
                });

        return vendor;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Vendor get(@PathVariable Long id)
            throws InventoryException {

        Vendor vendor = null;

        vendor = getEntity(vendorService, id);

        return vendor;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public SearchResult<Vendor> search(@RequestBody BaseDTO dto)
            throws InventoryException {
        SearchResult<Vendor> searchResult = null;

        searchResult = searchEntities(vendorService, dto);

        return searchResult;
    }
}
