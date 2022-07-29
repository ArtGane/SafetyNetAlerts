package com.safetynet.SafetyNetAlerts.dto;

import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class PersonInfoDto {

    String lastname;
    String firstname;
    String address;
    int age;
    String mail;
    List<String> medications;
    List<String> allergies;

}
