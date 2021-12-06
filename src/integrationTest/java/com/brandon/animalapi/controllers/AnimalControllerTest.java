package com.brandon.animalapi.controllers;

import com.brandon.animalapi.TestApplicationContext;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
        MvcResult mvcResult = this.mockMvc.perform(get("/animals")).andReturn();

        assertThat(mvcResult.getResponse())
                .extracting(MockHttpServletResponse::getStatus, MockHttpServletResponse::getContentType)
                .isEqualTo(new Object[]{200, "application/json"});
    }

    @Test
    public void getAnimal() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/animals/1")).andReturn();
        MockHttpServletResponse resultResponse = mvcResult.getResponse();
        assertThat(resultResponse)
                .extracting(MockHttpServletResponse::getStatus, MockHttpServletResponse::getContentType)
                .isEqualTo(new Object[]{200, "application/json"});
        System.out.println(resultResponse.getContentAsString());
        assertThat(resultResponse.getContentAsString()).isEqualTo("{\"id\":1,\"name\":\"Stairs\",\"type\":\"GrootHuis\",\"age\":3,\"ownerId\":2}");
    }
}
