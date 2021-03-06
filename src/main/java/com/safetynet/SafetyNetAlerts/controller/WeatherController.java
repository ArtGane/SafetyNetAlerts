package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.dto.ChildAlertDto;
import com.safetynet.SafetyNetAlerts.dto.HomeDto;
import com.safetynet.SafetyNetAlerts.dto.PersonInfoDto;
import com.safetynet.SafetyNetAlerts.dto.PersonStationMedicalDto;
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

    @GetMapping(value = "/firestation", params = "stationNumber")
    List<String> getPersonsInfosWithFirestationNum(@RequestParam String stationNumber) {
        return weatherService.getPersonsInfosWithFirestationNum(stationNumber);
    }

    @GetMapping(value = "/childAlert", params = "address")
    ChildAlertDto getSeparateChildrenFromAdults(@RequestParam String address) throws IOException, ParseException {
        return weatherService.getSeparateChildrenFromAdults(address);
    }

    @GetMapping(value = "/phoneAlert", params = "firestationNumber")
    List<String> getFirestationPhoneAlert(@RequestParam String firestationNumber) throws IOException, ParseException {
        return weatherService.getFirestationPhoneAlert(firestationNumber);
    }

    @GetMapping(value = "/fire", params = "address")
    PersonStationMedicalDto getPersonsAndMedicalRecordFromAddress(@RequestParam String address) throws IOException, ParseException {
        return weatherService.getPersonsAndMedicalRecordFromAddress(address);
    }

    @GetMapping(value = "/flood/stations", params = "stations")
    List<HomeDto> getHomeStationNumber(@RequestParam List<String> stations) throws IOException, ParseException {
        return weatherService.getHomeStationNumber(stations);
    }

    @GetMapping(value = "personInfos", params = "lastname" + "firstname")
    PersonInfoDto getPersonsInfos(@RequestParam String lastname, String firstname) throws IOException, ParseException {
        return weatherService.getPersonInfo(lastname,firstname);
    }

    @GetMapping(value = "/communityEmail", params = "city")
    List<String> getEmailByCity(@RequestParam String city) throws IOException, ParseException {
        return weatherService.getEmailByCity(city);
    }

}
