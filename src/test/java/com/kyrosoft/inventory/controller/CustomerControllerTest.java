package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.model.Customer;
import com.kyrosoft.inventory.model.ServiceContext;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest extends BaseControllerTest {

    @Before
    public void setup() throws Exception {
        seedFactory();
        ServiceContext.setCurrentUser(stringTest);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
        this.accessToken = obtainAccessToken("john", "123");
    }

    @Test
    public void createCustomer_OK() throws Exception {

        Customer customer = new Customer();
        customer.setName(stringTest);
        customer.setIsActive(boolTest);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(post("/api/customers"), customer))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void updateCustomer_OK() throws Exception {

        Customer customer = this.customers.get(0);
        customer.setName(stringUpdated);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(
                                put("/api/customers/" + customer.getId()),
                                customer
                        ))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value(stringUpdated));
    }

    @Test
    public void getCustomer_OK() throws Exception {
        Customer customer = this.customers.get(0);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(
                                get("/api/customers/" + customer.getId()),
                                customer
                        ))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(customer.getId()));
    }

    @Test
    public void searchCustomer_OK() throws Exception {
        BaseDTO dto = new BaseDTO();
        dto.setName("test");
        dto.setPage(1);
        dto.setSize(20);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(
                                post("/api/customers/search"),
                                dto
                        ))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data", hasSize(20)));
    }
}
