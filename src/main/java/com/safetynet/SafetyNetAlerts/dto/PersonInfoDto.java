package com.safetynet.SafetyNetAlerts.dto;

import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import lombok.Data;

import java.util.HashMap;

@Data
public class PersonInfoDto {

    HashMap<PersonModel, MedicalRecordModel> listPersonsInfo;

}
