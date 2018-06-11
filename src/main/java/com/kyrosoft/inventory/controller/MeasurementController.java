package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.InventoryException;
import com.kyrosoft.inventory.model.Measurement;
import com.kyrosoft.inventory.model.SearchResult;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import com.kyrosoft.inventory.model.dto.MeasurementDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("api/measurements")
public class MeasurementController extends BaseController {

    @PostConstruct
    public void construct() throws Exception {
        api = "api/measurements";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Measurement create(@RequestBody Measurement measurement)
            throws InventoryException {

        logExceptionWrapper(
                measurementService,
                measurement,
                (service, entity) -> {
                    service.create(entity);
                });

        return measurement;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Measurement update(@PathVariable Long id, @RequestBody Measurement measurement)
            throws InventoryException {

        logExceptionWrapper(
                measurementService,
                measurement,
                (service, entity) -> {
                    entity.setId(id);
                    service.update(entity);
                });

        return measurement;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Measurement get(@PathVariable Long id)
            throws InventoryException {

        Measurement measurement = null;

        measurement = getEntity(measurementService, id);

        return measurement;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public SearchResult<Measurement> search(@RequestBody MeasurementDTO dto)
            throws InventoryException {
        SearchResult<Measurement> searchResult = null;

        searchResult = searchEntities(measurementService, dto);

        return searchResult;
    }
}
