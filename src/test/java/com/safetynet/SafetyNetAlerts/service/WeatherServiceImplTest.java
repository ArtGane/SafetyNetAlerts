package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.dto.ChildAlertDto;
import com.safetynet.SafetyNetAlerts.dto.HomeDto;
import com.safetynet.SafetyNetAlerts.dto.PersonInfoDto;
import com.safetynet.SafetyNetAlerts.dto.PersonStationMedicalDto;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class WeatherServiceImplTest {

    WeatherService weatherService = new WeatherServiceImpl();

    @Test
    void getPersonsInfosWithFirestationNumTest() {
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
        PersonStationMedicalDto personStationMedicalDto = weatherService.getPersonsAndMedicalRecordFromAddress("29 15th St");
        Assert.assertEquals("PersonStationMedical(personModelList=[PersonModel(firstName=Jonanathan, lastName=Marrack, address=29 15th St, city=Culver, zip=97451, phone=841-874-6513, email=drk@email.com)], medicalRecordModelList=[MedicalRecordModel(firstName=Jonanathan, lastName=Marrack, birthdate=01/03/1989, medications=[], allergies=[])], listStation=[2])", personStationMedicalDto.toString());
    }

    @Test
    void getHomeStationNumberTest() throws IOException, ParseException {
        List<String> stations = new ArrayList<>();
        stations.add("1");
        stations.add("2");

        List<HomeDto> homeDtoList = weatherService.getHomeStationNumber(stations);
        Assert.assertEquals(11, homeDtoList.size());
    }

    @Test
    void getPersonInfoTest() throws IOException, ParseException {
        PersonInfoDto listPersons = weatherService.getPersonInfo("Boyd", "Felicia");
        Assert.assertEquals("PersonInfoDto(listPersonsInfo={PersonModel(firstName=John, lastName=Boyd, address=1509 Culver St, city=Culver, zip=97451, phone=841-874-6512, email=jaboyd@email.com)=MedicalRecordModel(firstName=Felicia, lastName=Boyd, birthdate=01/08/1986, medications=[tetracyclaz:650mg], allergies=[xilliathal])})", listPersons.toString());
    }

    @Test
    void getEmailByCityTest() throws IOException, ParseException {
        List<String> listEmail = weatherService.getEmailByCity("Culver");
        Assert.assertEquals(23, listEmail.size());
    }

}