package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.InventoryException;
import com.kyrosoft.inventory.model.Location;
import com.kyrosoft.inventory.model.SearchResult;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("api/locations")
public class LocationController extends BaseController {

    @PostConstruct
    public void construct() throws Exception {
        api = "api/locations";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Location create(@RequestBody Location location)
            throws InventoryException {

        logExceptionWrapper(
                locationService,
                location,
                (service, entity) -> {
                    service.create(entity);
                });

        return location;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Location update(@PathVariable Long id, @RequestBody Location location)
            throws InventoryException {

        logExceptionWrapper(
                locationService,
                location,
                (service, entity) -> {
                    entity.setId(id);
                    service.update(entity);
                });

        return location;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Location get(@PathVariable Long id)
            throws InventoryException {

        Location location = null;

        location = getEntity(locationService, id);

        return location;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public SearchResult<Location> search(@RequestBody BaseDTO dto)
            throws InventoryException {
        SearchResult<Location> searchResult = null;

        searchResult = searchEntities(locationService, dto);

        return searchResult;
    }
}
