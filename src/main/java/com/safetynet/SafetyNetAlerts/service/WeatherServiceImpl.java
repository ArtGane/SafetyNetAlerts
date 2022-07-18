package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.conf.Utils;
import com.safetynet.SafetyNetAlerts.dto.*;
import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {

    private MedicalRecordService medicalRecordService = new MedicalRecordService();
    private PersonService personService = new PersonService();
    private FirestationService firestationService = new FirestationService();

    @Override
    public List<String> getPersonsInfosWithFirestationNum(String stationNum) {
//        Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante.
//                Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste
//        doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus,
//        elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
//                moins) dans la zone desservie.

        log.info("");

        return null;
    }

    @Override
    public ChildAlertDto getSeparateChildrenFromAdults(String address) throws IOException, ParseException {
        List<PersonModel> personModelList = personService.getPersonsList();
        ChildAlertDto childAlertDto = new ChildAlertDto();
        List<PersonModel> adultList = new ArrayList<>();
        List<ChildDto> childList = new ArrayList<>();

        List<PersonModel> allPersonsAddress = personModelList.stream()
                .filter(p -> p.getAddress().equals(address)).collect(Collectors.toList());

        for (PersonModel personModel : allPersonsAddress) {
            MedicalRecordModel medicalRecord = medicalRecordService.getMedicalRecord(personModel.getLastName(), personModel.getFirstName());
            int year = Utils.extractYear(medicalRecord.getBirthdate());
            int age = Utils.calculateAge(year);

            if (age <= 18) {
                ChildDto child = new ChildDto();
                child.setLastname(personModel.getLastName());
                child.setFirstname(personModel.getFirstName());
                child.setAge(age);
                childList.add(child);
            } else {
                adultList.add(personModel);
            }
        }

        log.info("");
        childAlertDto.setChildren(childList);
        childAlertDto.setAdults(adultList);

        return childAlertDto;
    }

    @Override
    public List<String> getFirestationPhoneAlert(String firestationNumber) throws IOException, ParseException {
        List<FirestationModel> firestationModelList = firestationService.getFirestationsList();
        List<PersonModel> personModelList = personService.getPersonsList();
        List<String> addressPersons = new ArrayList<>();

        List<String> numStationList = firestationModelList.stream()
                .filter(f -> f.getStation().equals(firestationNumber)).map(f -> f.getAddress()).collect(Collectors.toList());

        for (String address : numStationList) {
            addressPersons = personModelList.stream()
                    .filter(p -> p.getAddress().equals(address)).map(p -> p.getPhone()).collect(Collectors.toList());
        }

        return addressPersons;
    }

    @Override
    public PersonStationMedicalDto getPersonsAndMedicalRecordFromAddress(String address) throws IOException, ParseException {
        PersonStationMedicalDto personStationMedicalDto = new PersonStationMedicalDto();

        List<PersonModel> personModelList = personService.getPersonsList();
        List<FirestationModel> firestationModelList = firestationService.getFirestationsList();
        List<MedicalRecordModel> medicalRecordModel = medicalRecordService.getMedicalRecordsList();

        List<PersonModel> livingPersons = personModelList.stream()
                .filter(p -> p.getAddress().equals(address))
                .collect(Collectors.toList());

        List<String> stationNumber = firestationModelList.stream()
                .filter(f -> f.getAddress().equals(address)).map(f -> f.getStation()).toList();

        for (PersonModel personModel : livingPersons) {
            List<MedicalRecordModel> medicalRecordsPersons = medicalRecordModel.stream()
                    .filter(m -> m.getLastName().equals(personModel.getLastName()))
                    .filter(m -> m.getFirstName().equals(personModel.getFirstName()))
                    .collect(Collectors.toList());
            personStationMedicalDto.setMedicalRecordModelList(medicalRecordsPersons);

        }

        personStationMedicalDto.setListStation(stationNumber);
        personStationMedicalDto.setPersonModelList(livingPersons);

        return personStationMedicalDto;
    }

    @Override
    public List<HomeDto> getHomeStationNumber(List<String> stations) throws IOException, ParseException {
        List<FirestationModel> firestationModelList = firestationService.getFirestationsList();
        List<PersonModel> personModelList = personService.getPersonsList();

        List<String> addresses = new ArrayList<>();
        List<HomeDto> homeDtoList = new ArrayList<>();


        for (String station : stations) {
            addresses.addAll(firestationModelList.stream()
                    .filter(f -> f.getStation().equals(station)).map(f -> f.getAddress()).collect(Collectors.toList()));
        }

        for (String address : addresses) {
            HomeDto homeDto = new HomeDto();
            List<PersonModel> personsAdresses = personModelList.stream()
                    .filter(p -> p.getAddress().equals(address)).collect(Collectors.toList());

            for (PersonModel person : personsAdresses) {
                MedicalRecordModel medicalRecordModel = medicalRecordService.getMedicalRecord(person.getLastName(), person.getFirstName());
                int year = Utils.extractYear(medicalRecordModel.getBirthdate());
                int age = Utils.calculateAge(year);

                homeDto.setName(person.getLastName());
                homeDto.setPhoneNumber(person.getPhone());
                homeDto.setAge(age);
                homeDto.setAllergies(medicalRecordModel.getAllergies());
                homeDto.setMedications(medicalRecordModel.getMedications());

                homeDtoList.add(homeDto);
            }
        }

        return homeDtoList;

        //TODO : regrouper par foyer (addresse)
    }

    @Override
    public PersonInfoDto getPersonInfo(String lastname, String firstname) throws IOException, ParseException {
        List<MedicalRecordModel> medicalRecordModelList = medicalRecordService.getMedicalRecordsList();
        List<PersonModel> personModelList = personService.getPersonsList();
        HashMap<PersonModel, MedicalRecordModel> finalList = new HashMap<>();

        PersonInfoDto personInfoDto = new PersonInfoDto();

        for (PersonModel infosPerson : personModelList) {
            infosPerson = personModelList.stream()
                    .filter(p -> p.getLastName().equals(lastname))
                    .findAny().orElseThrow();
            for (MedicalRecordModel medicalRecordModel : medicalRecordModelList) {
                medicalRecordModel = medicalRecordModelList.stream()
                        .filter(m -> m.getLastName().equals(lastname))
                        .filter(m -> m.getFirstName().equals(firstname))
                        .findAny().orElseThrow();

                finalList.put(infosPerson, medicalRecordModel);
            }
        }

        personInfoDto.setListPersonsInfo(finalList);

        return personInfoDto;
    }

    @Override
    public List<String> getEmailByCity(String city) throws IOException, ParseException {
        List<PersonModel> personModelList = personService.getPersonsList();

        List<String> emailList = personModelList.stream()
                .filter(p -> p.getCity().equals(city)).map(p -> p.getEmail()).toList();

        return emailList;
    }

}

