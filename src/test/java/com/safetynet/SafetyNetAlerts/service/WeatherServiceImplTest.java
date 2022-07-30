package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.dto.*;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class WeatherServiceImplTest {

    WeatherService weatherService = new WeatherServiceImpl();

    @Test
    void getPersonsInfosWithFirestationNumTest() throws IOException, ParseException {
        StationsPersonsDto stationsPersonsDtoList = weatherService.getPersonsInfosWithFirestationNum("2");
        Assert.assertEquals("[]", stationsPersonsDtoList.toString());
    }

    @Test
    void getAllChildrenOnlyTest() throws IOException, ParseException {
        ChildAlertDto listPeople = weatherService.getSeparateChildrenFromAdults("1509 Culver St");
        Assert.assertEquals(2, listPeople.getChildren().size());
        Assert.assertEquals(3, listPeople.getAdults().size());
    }

    @Test
    void getFirestationPhoneAlertTest() throws IOException, ParseException {
        List<String> listNum = weatherService.getFirestationPhoneAlert("1");
        Assert.assertEquals("[841-874-7784, 841-874-7784, 841-874-7784]", listNum.toString());
    }

    @Test
    void getPersonsAndMedicalRecordFromAddressTest() throws IOException, ParseException {
        PersonStationMedicalDto personStationMedicalDto = weatherService.getPersonsAndMedicalRecordFromAddress("748 Townings Dr");
        Assert.assertEquals(2, personStationMedicalDto.getPersons().size());
    }

    @Test
    void getHomeStationNumberTest() throws IOException, ParseException {
        List<String> stations = new ArrayList<>();
        stations.add("1");
        stations.add("2");

        List<HomeDto> homeDtoList = weatherService.getHomeStationNumber(stations);
        Assert.assertEquals(6, homeDtoList.size());
    }

    @Test
    void getPersonInfoTest() throws IOException, ParseException {
        PersonInfoDto listPersons = weatherService.getPersonInfo("Felicia", "Boyd");
        Assert.assertEquals("PersonInfoDto(lastname=Boyd, firstname=Felicia, address=1509 Culver St, age=36, mail=jaboyd@email.com, medications=[tetracyclaz:650mg], allergies=[xilliathal])", listPersons.toString());
    }

    @Test
    void getEmailByCityTest() throws IOException, ParseException {
        List<String> listEmail = weatherService.getEmailByCity("Culver");
        Assert.assertEquals(23, listEmail.size());
    }

}