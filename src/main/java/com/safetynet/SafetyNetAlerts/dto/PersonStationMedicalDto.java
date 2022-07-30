package com.safetynet.SafetyNetAlerts.dto;

import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import lombok.Data;

import java.util.List;

@Data
public class PersonStationMedicalDto {

    String station;
    List<PersonStationMedicalDtoObject> persons;

}
