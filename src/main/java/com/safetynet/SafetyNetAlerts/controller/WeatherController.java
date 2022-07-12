package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.dto.ChildAlertDto;
import com.safetynet.SafetyNetAlerts.dto.ChildDto;
import com.safetynet.SafetyNetAlerts.service.WeatherService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping(value = "/communityEmail", params = "city")
    List<String> getEmailByCity(@RequestParam String city) throws IOException, ParseException {
        return weatherService.getEmailByCity(city);
    }

    @GetMapping(value = "/childAlert", params = "address")
    ChildAlertDto getAllChildrenOnly(@RequestParam String address) throws IOException, ParseException {
        return weatherService.getSeparateChildrenFromAdults(address);
    }

    @GetMapping(value = "/phoneAlert", params = "firestationNumber")
    List<String> getFirestationPhoneAlert(@RequestParam String firestationNumber) throws IOException, ParseException {
        return weatherService.getFirestationPhoneAlert(firestationNumber);
    }

}
