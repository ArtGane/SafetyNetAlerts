package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.service.PersonService;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    JSONObject jsonObject = new JSONObject();

    @BeforeEach
    void setup() {
        jsonObject.put("firstName", "Eric");
        jsonObject.put("lastName", "Cardigan");
        jsonObject.put("address", "951 LoneArbre Rd");
        jsonObject.put("city", "Jupiter");
        jsonObject.put("zip", "97451");
        jsonObject.put("phone", "841-874-7458");
        jsonObject.put("email", "cardiganPatdef@email.com");
    }

    @Test
    void createPersonTest() throws Exception {
        mockMvc.perform(
                        post("/persons")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObject.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void updatePersonTest() throws Exception {
        mockMvc.perform(
                        put("/persons")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObject.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void deletePersonTest() throws Exception {
        mockMvc.perform(
                        delete("/persons")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObject.toString()))
                .andExpect(status().isOk()).andReturn();
    }

}