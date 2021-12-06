package com.brandon.animalapi.controllers;

import com.brandon.animalapi.AnimalApiConfig;
import com.brandon.animalapi.TestApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringJUnitWebConfig(classes = TestApplicationContext.class)
public class OwnerControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getOwners() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/owners")).andReturn();

        assertThat(mvcResult.getResponse())
                .extracting(MockHttpServletResponse::getStatus, MockHttpServletResponse::getContentType)
                .isEqualTo(new Object[]{200, "application/json"});
    }

    @Test
    public void getOwner() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/owners/1")).andReturn();
        MockHttpServletResponse resultResponse = mvcResult.getResponse();
        assertThat(resultResponse)
                .extracting(MockHttpServletResponse::getStatus, MockHttpServletResponse::getContentType)
                .isEqualTo(new Object[]{200, "application/json"});

        assertThat(resultResponse.getContentAsString()).isEqualTo("{\"id\":1,\"name\":\"Hans\",\"address\":\"Shell Corp 3\",\"familySize\":1}");
    }

    @Test
    public void deleteOwner() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(delete("/owners/2")).andReturn();
        MockHttpServletResponse resultResponse = mvcResult.getResponse();
        assertThat(resultResponse)
                .extracting(MockHttpServletResponse::getStatus)
                .isEqualTo(new Object[]{204});
    }
}
