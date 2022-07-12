package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.conf.Utils;
import com.safetynet.SafetyNetAlerts.dto.ChildAlertDto;
import com.safetynet.SafetyNetAlerts.dto.ChildDto;
import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    public List<String> getEmailByCity(String city) throws IOException, ParseException {
        PersonService personService = new PersonService();
        List<PersonModel> personModelList = personService.getPersonsList();

        List<String> emailList = personModelList.stream()
                .filter(p -> p.getCity().equals(city)).map(p -> p.getEmail()).toList();

        return emailList;
    }

    public ChildAlertDto getSeparateChildrenFromAdults(String address) throws IOException, ParseException {
        PersonService personService = new PersonService();
        MedicalRecordService medicalRecordService = new MedicalRecordService();
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

        childAlertDto.setChildren(childList);
        childAlertDto.setAdults(adultList);

        return childAlertDto;
    }

    public List<String> getFirestationPhoneAlert(String firestationNumber) throws IOException, ParseException {
        FirestationService firestationService = new FirestationService();
        List<FirestationModel> firestationModelList = firestationService.getFirestationsList();

        List<FirestationModel> numStationLsit = firestationModelList.stream()
                .filter(f -> f.getStation().equals(firestationNumber)).collect(Collectors.toList());

        return null;
    } // doit retourner liste numéro de téléphone en rapport avec num station

    public List<String> getPersonInfo() throws IOException, ParseException {
        MedicalRecordService medicalRecordService = new MedicalRecordService();
        List<MedicalRecordModel> medicalRecordModelList = medicalRecordService.getMedicalRecordsList();
        return null;
    }
}

