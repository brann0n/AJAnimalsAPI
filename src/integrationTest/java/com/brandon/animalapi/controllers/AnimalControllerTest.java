package com.brandon.animalapi.controllers;

import com.brandon.animalapi.TestApplicationContext;
import com.brandon.animalapi.dto.AnimalDto;
import com.brandon.animalapi.models.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(classes = TestApplicationContext.class)
public class AnimalControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getAnimals() throws Exception {
        mockMvc.perform(get("/animals"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andReturn();
    }

    @Test
    public void getAnimal() throws Exception {
        mockMvc.perform(get("/animals/2"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json"))
                .andExpect(jsonPath("$.id").value(3))
                .andReturn();
    }

    @Test
    public void deleteAnimal() throws Exception {
        mockMvc.perform(delete("/animals/3"))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void postAnimal() throws Exception {
        AnimalDto dto = new AnimalDto();
        dto.setName("Kees");
        dto.setType("Dog");
        dto.setAge(5);
        dto.setOwnerId(1L);

        mockMvc.perform(post("/animals")
                        .content(new ObjectMapper().writeValueAsString(dto))
                        .contentType("application/json"))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

    }
}
