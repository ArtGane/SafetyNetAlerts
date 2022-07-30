package com.safetynet.SafetyNetAlerts.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonStationMedicalDtoObject {

    String lastname;
    String firstname;
    String phone;
    int age;
    List<String> medications;
    List<String> allergies;

}
