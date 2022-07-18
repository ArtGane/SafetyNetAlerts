package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.db.AccessInfos;
import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.service.FirestationService;
import com.safetynet.SafetyNetAlerts.service.PersonService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FirestationController.class)
class FirestationControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private FirestationService firestationService;

    JSONObject jsonObject = new JSONObject();

    @BeforeEach
    public void setup() {
        jsonObject.put("address", "834 Binoc Ave");
        jsonObject.put("station", "3");
    }

    @Test
    void updateFirestationsTest() throws Exception {
        mockMvc.perform(
                        post("/firestations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObject.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void createFirestationsTest() throws Exception {
        mockMvc.perform(
                        put("/persons")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObject.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteFirestationsTest() throws Exception {
        mockMvc.perform(
                        delete("/persons")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObject.toString()))
                .andExpect(status().isOk()).andReturn();
    }
}