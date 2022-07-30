package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.dto.*;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface WeatherService {

    StationsPersonsDto getPersonsInfosWithFirestationNum(String stationNum) throws IOException, ParseException;
    ChildAlertDto getSeparateChildrenFromAdults(String address) throws IOException, ParseException;
    List<String> getFirestationPhoneAlert(String firestationNumber) throws IOException, ParseException;
    PersonStationMedicalDto getPersonsAndMedicalRecordFromAddress(String address) throws IOException, ParseException;
    List<HomeDto> getHomeStationNumber(List<String> stations) throws IOException, ParseException;
    PersonInfoDto getPersonInfo(String firstname, String lastname) throws IOException, ParseException;
    List<String> getEmailByCity(String city) throws IOException, ParseException;

}
