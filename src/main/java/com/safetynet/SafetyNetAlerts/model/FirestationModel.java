package com.safetynet.SafetyNetAlerts.model;

import lombok.Data;

@Data
public class FirestationModel {

    private String address;
    private int station;

    public String getAddress() {
        return address;
    }
    public void setAddress(String adress) {
        this.address = adress;
    }
    public int getStation() {
        return station;
    }
    public void setStation(int station) {
        this.station = station;
    }
}
