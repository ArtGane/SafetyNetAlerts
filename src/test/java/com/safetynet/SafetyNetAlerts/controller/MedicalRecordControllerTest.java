package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.db.AccessInfos;
import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.service.MedicalRecordService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordControllerTest {

    private MedicalRecordController medicalRecordController = new MedicalRecordController(new MedicalRecordService());

    @Test
    void updateMedicalRecordTest() throws IOException, ParseException {
        MedicalRecordModel medicalRecordModel = new MedicalRecordModel();
        medicalRecordController.updateMedicalRecord(medicalRecordModel);
    }

    @Test
    void createMedicalRecordTest() throws IOException, ParseException {
        MedicalRecordModel medicalRecordModel = new MedicalRecordModel();
        medicalRecordController.createMedicalRecord(medicalRecordModel);
    }

    @Test
    void deleteMedicalRecordTest() throws IOException, ParseException {
    MedicalRecordModel medicalRecordModel = new MedicalRecordModel();
    medicalRecordController.deleteMedicalRecord(medicalRecordModel);
    }
}