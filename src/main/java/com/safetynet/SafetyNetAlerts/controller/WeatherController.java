package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.dto.*;
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

    @GetMapping("/firestation")
    StationsPersonsDto getPersonsInfosWithFirestationNum(@RequestParam String stationNumber) throws IOException, ParseException {
        return weatherService.getPersonsInfosWithFirestationNum(stationNumber);
    }

    @GetMapping("/childAlert")
    ChildAlertDto getSeparateChildrenFromAdults(@RequestParam String address) throws IOException, ParseException {
        return weatherService.getSeparateChildrenFromAdults(address);
    }

    @GetMapping("/phoneAlert")
    List<String> getFirestationPhoneAlert(@RequestParam String firestation) throws IOException, ParseException {
        return weatherService.getFirestationPhoneAlert(firestation);
    }

    @GetMapping("/fire")
    PersonStationMedicalDto getPersonsAndMedicalRecordFromAddress(@RequestParam String address) throws IOException, ParseException {
        return weatherService.getPersonsAndMedicalRecordFromAddress(address);
    }

    @GetMapping("/flood/stations")
    List<HomeDto> getHomeStationNumber(@RequestParam List<String> stations) throws IOException, ParseException {
        return weatherService.getHomeStationNumber(stations);
    }

    @GetMapping("/personInfo")
    PersonInfoDto getPersonsInfos(@RequestParam String firstName, @RequestParam String lastName) throws IOException, ParseException {
        return weatherService.getPersonInfo(firstName,lastName);
    }

    @GetMapping("/communityEmail")
    List<String> getEmailByCity(@RequestParam String city) throws IOException, ParseException {
        return weatherService.getEmailByCity(city);
    }

}
