package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.InventoryException;
import com.kyrosoft.inventory.model.Product;
import com.kyrosoft.inventory.model.SearchResult;
import com.kyrosoft.inventory.model.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("api/products")
public class ProductController extends BaseController {

    @PostConstruct
    public void construct() throws Exception {
        api = "api/measurements";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Product create(@RequestBody Product product)
            throws InventoryException {

        logExceptionWrapper(
                productService,
                product,
                (service, entity) -> {
                    service.create(entity);
                });

        return product;
    }
}
