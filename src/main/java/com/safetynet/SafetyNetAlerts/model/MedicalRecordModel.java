package com.safetynet.SafetyNetAlerts.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class MedicalRecordModel {

    private String firstName;
    private String lastName;
    private String birthdate;
    private List<String> medications;
    private List<String> allergies;

}