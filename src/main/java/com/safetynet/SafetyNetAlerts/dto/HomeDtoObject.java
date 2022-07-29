package com.safetynet.SafetyNetAlerts.dto;

import lombok.Data;

import java.util.List;

@Data
public class HomeDtoObject {

    String lastname;
    String firstname;
    String phoneNumber;
    int age;
    List<String> medications;
    List<String> allergies;

}
