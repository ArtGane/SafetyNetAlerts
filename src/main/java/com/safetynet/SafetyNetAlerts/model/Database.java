package com.safetynet.SafetyNetAlerts.model;

import java.util.List;

public class Database {

    private List<PersonModel> persons;
    private List<FirestationModel> firestations;
    private List<MedicalRecordModel> medicalrecords;

    public List<PersonModel> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonModel> persons) {
        this.persons = persons;
    }

    public List<FirestationModel> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<FirestationModel> firestations) {
        this.firestations = firestations;
    }

    public List<MedicalRecordModel> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<MedicalRecordModel> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }


}
