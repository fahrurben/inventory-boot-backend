package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.model.Measurement;
import com.kyrosoft.inventory.model.Product;
import com.kyrosoft.inventory.model.dto.MeasurementDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest extends BaseControllerTest {

    @Before
    public void setup() throws Exception {
        setupGeneric();
    }

    @Test
    public void createProduct_OK() throws Exception {

        Product product = new Product();
        product.setName(stringTest);
        product.setCode(stringTest);
        product.setCategory(this.productCategories.get(0));
        product.setRemarks(stringTest);
        product.setBasicUnit(this.measurements.get(0));
        product.setIsActive(boolTest);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(post("/api/products"), product))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").isNotEmpty());
    }
}
