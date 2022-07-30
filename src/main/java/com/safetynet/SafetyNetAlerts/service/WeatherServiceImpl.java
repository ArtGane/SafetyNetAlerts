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
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WeatherServiceImpl implements WeatherService {

    private MedicalRecordService medicalRecordService = new MedicalRecordService();
    private PersonService personService = new PersonService();
    private FirestationService firestationService = new FirestationService();


    @Override
    public StationsPersonsDto getPersonsInfosWithFirestationNum(String stationNum) throws IOException, ParseException {
        List<PersonModel> personModelList = personService.getPersonsList();
        List<FirestationModel> firestationModelList = firestationService.getFirestationsList();

        StationsPersonsDto stationsPersonsDto = new StationsPersonsDto();
        List<StationPersonsDtoObject> stationPersonsDtoObjectList = new ArrayList<>();

        // Get address from number of station
        String address = firestationModelList.stream()
                .filter(f -> f.getStation().equals(stationNum)).map(f -> f.getAddress()).findAny().orElse(null);

            stationsPersonsDto.setAddress(address);

            List<PersonModel> personsAdresses = personModelList.stream()
                    .filter(p -> p.getAddress().equals(address)).collect(Collectors.toList());

            // Get all persons infos
            for (PersonModel person : personsAdresses) {
                ChildAlertDto childAlertDto = getSeparateChildrenFromAdults(person.getAddress());

                StationPersonsDtoObject stationPersonsDtoObject = new StationPersonsDtoObject();

                stationPersonsDtoObject.setLastname(person.getLastName());
                stationPersonsDtoObject.setFirstname(person.getFirstName());
                stationPersonsDtoObject.setAddress(person.getAddress());
                stationPersonsDtoObject.setPhone(person.getPhone());
                stationPersonsDtoObject.setChildAlertDto(childAlertDto);

                stationPersonsDtoObjectList.add(stationPersonsDtoObject);
            }

        stationsPersonsDto.setStationPersonsDtoObjectList(stationPersonsDtoObjectList);

        return stationsPersonsDto;
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
            int age = Utils.getAge(medicalRecord.getBirthdate());

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
    public List<String> getFirestationPhoneAlert(String firestationNumber) {
        List<String> phonePersons = new ArrayList<>();

        try {
            List<FirestationModel> firestationModelList = firestationService.getFirestationsList();
            List<PersonModel> personModelList = personService.getPersonsList();

            List<String> numStationList = firestationModelList.stream()
                    .filter(f -> f.getStation().equals(firestationNumber)).map(f -> f.getAddress()).collect(Collectors.toList());

            for (String address : numStationList) {
                phonePersons = personModelList.stream()
                        .filter(p -> p.getAddress().equals(address)).map(p -> p.getPhone()).collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return phonePersons;
    }

    @Override
    public PersonStationMedicalDto getPersonsAndMedicalRecordFromAddress(String address) throws IOException, ParseException {
        List<PersonModel> personModelList = personService.getPersonsList();

        List<PersonStationMedicalDtoObject> personStationMedicalDtoObjectList = new ArrayList<>();

        // Get station
        FirestationModel firestation = firestationService.getFirestation(address);

        // Get persons infos
        List<PersonModel> livingPersons = personModelList.stream()
                .filter(p -> p.getAddress().equals(address))
                .collect(Collectors.toList());

        //Set station
        PersonStationMedicalDto personStationMedicalDto = new PersonStationMedicalDto();
        personStationMedicalDto.setStation(firestation.getStation());

        for (PersonModel infosPerson : livingPersons) {
            //List of all persons
            PersonStationMedicalDtoObject personStationMedicalDtoObject = new PersonStationMedicalDtoObject();

            //Add person lastname, firstname and phone number
            personStationMedicalDtoObject.setLastname(infosPerson.getLastName());
            personStationMedicalDtoObject.setFirstname(infosPerson.getFirstName());
            personStationMedicalDtoObject.setPhone(infosPerson.getPhone());

            MedicalRecordModel medicalRecordModel = medicalRecordService.getMedicalRecord(infosPerson.getLastName(), infosPerson.getFirstName());

            if (medicalRecordModel != null) {
                int age = Utils.getAge(medicalRecordModel.getBirthdate());

                // Add person age, medications and allergies
                personStationMedicalDtoObject.setAge(age);
                personStationMedicalDtoObject.setMedications(medicalRecordModel.getMedications());
                personStationMedicalDtoObject.setAllergies(medicalRecordModel.getAllergies());
            } else {
                log.error("Medical record not found");
            }
            personStationMedicalDtoObjectList.add(personStationMedicalDtoObject);
        }
        personStationMedicalDto.setPersons(personStationMedicalDtoObjectList);
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
            List<PersonModel> personsAdresses = personModelList.stream()
                    .filter(p -> p.getAddress().equals(address)).collect(Collectors.toList());
            // Set initial values
            HomeDto homeDto = new HomeDto();
            List<HomeDtoObject> persons = new ArrayList<>();

            for (PersonModel person : personsAdresses) {
                MedicalRecordModel medicalRecordModel = medicalRecordService.getMedicalRecord(person.getLastName(), person.getFirstName());
                int age = Utils.getAge(medicalRecordModel.getBirthdate());

                HomeDtoObject homeDtoObject = new HomeDtoObject();

                homeDtoObject.setLastname(person.getLastName());
                homeDtoObject.setFirstname(person.getFirstName());
                homeDtoObject.setPhoneNumber(person.getPhone());
                homeDtoObject.setAge(age);
                homeDtoObject.setAllergies(medicalRecordModel.getAllergies());
                homeDtoObject.setMedications(medicalRecordModel.getMedications());

                persons.add(homeDtoObject);
            }

            // Save address and persons
            homeDto.setAddress(address);
            homeDto.setPersons(persons);

            // Add new item
            homeDtoList.add(homeDto);
        }

        return homeDtoList;

    }

    @Override
    public PersonInfoDto getPersonInfo(String firstname, String lastname) throws IOException, ParseException {
        List<MedicalRecordModel> medicalRecordModelList = medicalRecordService.getMedicalRecordsList();
        List<PersonModel> personModelList = personService.getPersonsList();

        PersonInfoDto personInfoDto = new PersonInfoDto();

        for (PersonModel infosPerson : personModelList) {
            infosPerson = personModelList.stream()
                    .filter(p -> p.getLastName().equals(lastname))
                    .filter(p -> p.getFirstName().equals(firstname))
                    .findAny().orElseThrow();

            for (MedicalRecordModel medicalRecordModel : medicalRecordModelList) {
                medicalRecordModel = medicalRecordModelList.stream()
                        .filter(m -> m.getLastName().equals(lastname))
                        .filter(m -> m.getFirstName().equals(firstname))
                        .findAny().orElseThrow();

                int age = Utils.getAge(medicalRecordModel.getBirthdate());

                personInfoDto.setLastname(infosPerson.getLastName());
                personInfoDto.setFirstname(infosPerson.getFirstName());
                personInfoDto.setAddress(infosPerson.getAddress());
                personInfoDto.setAge(age);
                personInfoDto.setMail(infosPerson.getEmail());
                personInfoDto.setMedications(medicalRecordModel.getMedications());
                personInfoDto.setAllergies(medicalRecordModel.getAllergies());

                log.info("Person with informations well added !");
            }
        }

        return personInfoDto;
    }

    @Override
    public List<String> getEmailByCity(String city) {
        List<String> emailList;
        try {
            List<PersonModel> personModelList = personService.getPersonsList();

            emailList = personModelList.stream()
                    .filter(p -> p.getCity().equals(city)).map(p -> p.getEmail()).toList();

        } catch (IOException e) {
            log.error("");
            throw new RuntimeException(e);
        } catch (ParseException e) {
            log.error("");
            throw new RuntimeException(e);
        }

        return emailList;

    }

}


