package com.safetynet.SafetyNetAlerts.dto;

import lombok.Data;

import java.util.List;


@Data
public class HomeDto {

    String address;
    List<HomeDtoObject> persons;

}
