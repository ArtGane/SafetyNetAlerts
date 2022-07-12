package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.dto.ChildAlertDto;
import com.safetynet.SafetyNetAlerts.dto.ChildDto;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class WeatherServiceTest {

    WeatherService weatherService = new WeatherService();

    @Test
    void getEmailByCityTest() throws IOException, ParseException {
        List<String> listEmail = weatherService.getEmailByCity("Culver");
        Assert.assertEquals(23, listEmail.size());
    }

    @Test
    void getAllChildrenOnlyTest() throws IOException, ParseException {
        ChildAlertDto listPeople = weatherService.getSeparateChildrenFromAdults("1509 Culver St");
        Assert.assertEquals(2, listPeople.getChildren().size());
        Assert.assertEquals(3, listPeople.getAdults().size());
    }
}