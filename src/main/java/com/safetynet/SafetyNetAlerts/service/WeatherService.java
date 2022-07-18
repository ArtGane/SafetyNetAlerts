package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.dto.ChildAlertDto;
import com.safetynet.SafetyNetAlerts.dto.HomeDto;
import com.safetynet.SafetyNetAlerts.dto.PersonInfoDto;
import com.safetynet.SafetyNetAlerts.dto.PersonStationMedicalDto;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface WeatherService {

    List<String> getPersonsInfosWithFirestationNum(String stationNum);
    ChildAlertDto getSeparateChildrenFromAdults(String address) throws IOException, ParseException;
    List<String> getFirestationPhoneAlert(String firestationNumber) throws IOException, ParseException;
    PersonStationMedicalDto getPersonsAndMedicalRecordFromAddress(String address) throws IOException, ParseException;
    List<HomeDto> getHomeStationNumber(List<String> stations) throws IOException, ParseException;
    PersonInfoDto getPersonInfo(String lastname, String firstname) throws IOException, ParseException;
    List<String> getEmailByCity(String city) throws IOException, ParseException;

}
