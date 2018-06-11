package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.model.Vendor;
import com.kyrosoft.inventory.model.dto.BaseDTO;
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
public class VendorControllerTest extends BaseControllerTest {

    @Before
    public void setup() throws Exception {
        setupGeneric();
    }

    @Test
    public void createVendor_OK() throws Exception {

        Vendor vendor = new Vendor();
        vendor.setName(stringTest);
        vendor.setIsActive(boolTest);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(post("/api/vendors"), vendor))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void updateVendor_OK() throws Exception {

        Vendor vendor = this.vendors.get(0);
        vendor.setName(stringUpdated);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(
                                put("/api/vendors/" + vendor.getId()),
                                vendor
                        ))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value(stringUpdated));
    }

    @Test
    public void getVendor_OK() throws Exception {
        Vendor vendor = this.vendors.get(0);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(
                                get("/api/vendors/" + vendor.getId()),
                                vendor
                        ))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(vendor.getId()));
    }

    @Test
    public void searchVendor_OK() throws Exception {
        BaseDTO dto = new BaseDTO();
        dto.setName("test");
        dto.setPage(1);
        dto.setSize(20);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(
                                post("/api/vendors/search"),
                                dto
                        ))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data", hasSize(20)));
    }
}
