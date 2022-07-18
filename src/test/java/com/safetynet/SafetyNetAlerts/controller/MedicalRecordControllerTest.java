package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.db.AccessInfos;
import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.service.MedicalRecordService;
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

@WebMvcTest(controllers = MedicalRecordController.class)
class MedicalRecordControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    JSONObject jsonObject = new JSONObject();

    @BeforeEach
    public void setup() {
        jsonObject.put("firstName", "John");
        jsonObject.put("lastName", "Boyd");
        jsonObject.put("birthdate", "03/06/1984");
        jsonObject.put("medications", "");
        jsonObject.put("allergies", "");
    }

    @Test
    void updateMedicalRecordTest() throws Exception {
        mockMvc.perform(
                        post("/medicalrecords")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObject.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void createMedicalRecordTest() throws Exception {
        mockMvc.perform(
                        put("/medicalrecords")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObject.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMedicalRecordTest() throws Exception {
        mockMvc.perform(
                        delete("/medicalrecords")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonObject.toString()))
                .andExpect(status().isOk()).andReturn();
    }
}