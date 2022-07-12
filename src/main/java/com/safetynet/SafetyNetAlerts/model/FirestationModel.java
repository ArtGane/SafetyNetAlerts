package com.safetynet.SafetyNetAlerts.model;

import lombok.Data;

@Data
public class FirestationModel {

    private String address;
    private String station;

    public FirestationModel(String station, String address) {
        this.station = station;
        this.address = address;
    }

    public FirestationModel() {
    }
}
