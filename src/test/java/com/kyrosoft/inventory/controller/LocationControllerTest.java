package com.kyrosoft.inventory.controller;

import com.kyrosoft.inventory.model.Location;
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
public class LocationControllerTest extends BaseControllerTest {

    @Before
    public void setup() throws Exception {
        setupGeneric();
    }

    @Test
    public void createLocation_OK() throws Exception {

        Location location = new Location();
        location.setName(stringTest);
        location.setIsActive(boolTest);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(post("/api/locations"), location))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    public void updateLocation_OK() throws Exception {

        Location location = this.locations.get(0);
        location.setName(stringUpdated);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(
                                put("/api/locations/" + location.getId()),
                                location
                        ))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value(stringUpdated));
    }

    @Test
    public void getLocation_OK() throws Exception {
        Location location = this.locations.get(0);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(
                                get("/api/locations/" + location.getId()),
                                location
                        ))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(location.getId()));
    }

    @Test
    public void searchLocation_OK() throws Exception {
        BaseDTO dto = new BaseDTO();
        dto.setName("test");
        dto.setPage(1);
        dto.setSize(20);

        ResultActions result =
                this.mockMvc.perform(
                        requestBuilderFacade(
                                post("/api/locations/search"),
                                dto
                        ))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data", hasSize(20)));
    }

}
