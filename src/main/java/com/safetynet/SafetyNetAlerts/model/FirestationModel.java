package com.safetynet.SafetyNetAlerts.model;

import lombok.Data;

@Data
public class FirestationModel {

    private String address;
    private int station;

    public FirestationModel(int station, String address) {
        this.station = station;
        this.address = address;
    }

    public FirestationModel() {
    }
}
