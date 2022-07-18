package com.safetynet.SafetyNetAlerts.dto;

import lombok.Data;

import java.util.List;

@Data
public class HomeDto {

    String name;
    String phoneNumber;
    int age;
    List<String> medications;
    List<String> allergies;

}
