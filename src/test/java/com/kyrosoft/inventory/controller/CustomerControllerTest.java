package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest extends BaseControllerTest {

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
        this.accessToken = obtainAccessToken("john", "123");
    }

    @Test
    public void createCustomer_OK() throws Exception {

        Customer customer = new Customer();
        customer.setName(stringTest);
        customer.setIsActive(boolTest);
        objectMapper.writeValueAsString(customer);

        ResultActions result =
                this.mockMvc.perform(
                        post("/api/customers")
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(objectMapper.writeValueAsString(customer)))
                        .andDo(print()).andExpect(status().isOk());
    }


}
