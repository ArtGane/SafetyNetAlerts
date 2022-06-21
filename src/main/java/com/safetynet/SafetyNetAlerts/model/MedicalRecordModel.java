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

    public MedicalRecordModel(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public MedicalRecordModel() {
    }
}