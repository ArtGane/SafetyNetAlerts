package com.safetynet.SafetyNetAlerts.dto;

import lombok.Data;

@Data
public class StationsPersonsDto {

    String lastname;
    String firstname;
    String address;
    String phone;
    ChildAlertDto childAlertDto;

}
