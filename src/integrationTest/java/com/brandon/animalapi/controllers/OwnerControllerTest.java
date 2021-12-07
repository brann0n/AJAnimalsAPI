package com.brandon.animalapi.controllers;

import com.brandon.animalapi.AnimalApiConfig;
import com.brandon.animalapi.TestApplicationContext;
import com.brandon.animalapi.dto.AnimalDto;
import com.brandon.animalapi.dto.OwnerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(classes = TestApplicationContext.class)
public class OwnerControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
    }

    @Test
    @WithMockUser(username = "bacon")
    public void getOwners() throws Exception {
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "bacon")
    public void getOwner() throws Exception {
        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(jsonPath("$.id").value(1), jsonPath("$.name").exists())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "bacon")
    public void deleteOwner() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(delete("/owners/2")).andReturn();
        MockHttpServletResponse resultResponse = mvcResult.getResponse();
        assertThat(resultResponse)
                .extracting(MockHttpServletResponse::getStatus)
                .isEqualTo(new Object[]{204});
    }

    @Test
    @WithMockUser(username = "bacon")
    public void postOwner() throws Exception {
        OwnerDto dto = new OwnerDto();
        dto.setName("Kees");
        dto.setAddress("Henk straat 5");
        dto.setFamilySize(5);

        mockMvc.perform(post("/owners")
                        .content(new ObjectMapper().writeValueAsString(dto))
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "bacon")
    public void putOwner() throws Exception {
        OwnerDto dto = new OwnerDto();
        dto.setName("Kees");
        dto.setAddress("Henk straat 5");
        dto.setFamilySize(5);

        mockMvc.perform(put("/owners/3")
                        .content(new ObjectMapper().writeValueAsString(dto))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
