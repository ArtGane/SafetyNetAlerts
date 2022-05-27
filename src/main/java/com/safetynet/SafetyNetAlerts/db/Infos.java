package com.safetynet.SafetyNetAlerts.db;

import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class Infos {

    private List<PersonModel> persons;
    private List<FirestationModel> firestations;
    private List<MedicalRecordModel> medicalrecords;

}
