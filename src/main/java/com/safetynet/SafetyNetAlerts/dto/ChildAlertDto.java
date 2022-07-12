package com.safetynet.SafetyNetAlerts.dto;

import com.safetynet.SafetyNetAlerts.model.PersonModel;
import lombok.Data;

import java.util.List;

@Data
public class ChildAlertDto {

    List<PersonModel> adults;
    List<ChildDto> children;
}
