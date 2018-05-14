package com.kyrosoft.inventory.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends BaseControllerTest {

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void do_authentication_OK() throws Exception {
        final String accessToken = obtainAccessToken("john", "123");

        ResultActions result =
                this.mockMvc.perform(
                        get("/api/me").header("Authorization", "Bearer " + accessToken))
                        .andDo(print()).andExpect(status().isOk());

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        Map<String, Object> retMap = jsonParser.parseMap(resultString);
        LinkedHashMap<String,Object> principalMap = (LinkedHashMap<String,Object>) retMap.get("principal");
        String username = principalMap.get("username").toString();
        Assert.assertEquals("john", username);
        System.out.println("exit");
    }
}
