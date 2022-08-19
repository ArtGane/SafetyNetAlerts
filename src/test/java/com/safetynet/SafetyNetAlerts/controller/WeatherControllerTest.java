package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.service.FirestationService;
import com.safetynet.SafetyNetAlerts.service.MedicalRecordService;
import com.safetynet.SafetyNetAlerts.service.PersonService;
import com.safetynet.SafetyNetAlerts.service.WeatherServiceImpl;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WeatherController.class)
class WeatherControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private WeatherServiceImpl weatherService;

    @Test
    void getPersonsInfosWithFirestationNumTest() throws Exception {
        mockMvc.perform(
                        get("/firestation").param("stationNumber", "3")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getSeparateChildrenFromAdultsTest() throws Exception {
        mockMvc.perform(
                        get("/childAlert")
                                .param("address", "951 LoneArbre Rd")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getFirestationPhoneAlertTest() throws Exception {
        mockMvc.perform(
                        get("/phoneAlert")
                                .param("firestation", "3")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPersonsAndMedicalRecordFromAddressTest() throws Exception {
        mockMvc.perform(
                        get("/fire")
                                .param("address", "834 Binoc Ave")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getHomeStationNumberTest() throws Exception {
        String[] stations = {"1", "2"};
        mockMvc.perform(
                        get("/flood/stations")
                                .param("stations", stations)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPersonsInfosTest() throws Exception {
        mockMvc.perform(
                        get("/personInfo")
                                .param("firstName", "Felicia")
                                .param("lastName", "Boyd")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getEmailByCityTest() throws Exception {
        mockMvc.perform(
                        get("/communityEmail")
                                .param("city", "Culver")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}