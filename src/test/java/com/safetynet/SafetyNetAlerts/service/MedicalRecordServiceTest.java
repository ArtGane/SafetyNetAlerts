package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordServiceTest {

    private MedicalRecordService medicalRecordService = new MedicalRecordService();
    @Test
    void getMedicalRecordsListTest() throws IOException, ParseException {
        List<MedicalRecordModel> medicalRecordModelList = medicalRecordService.getMedicalRecordsList();
        Assert.assertEquals("", medicalRecordModelList.get(5).toString());
    }

    @Test
    void getMedicalRecordTest() throws IOException, ParseException {
        MedicalRecordModel medicalRecordModel = medicalRecordService.getMedicalRecord("Boyd", "Felicia");
        Assert.assertEquals("Boyd", medicalRecordModel.getLastName());
        Assert.assertEquals("Felicia", medicalRecordModel.getFirstName());
    }

    @Test
    void deleteMedicalRecordTest() throws IOException, ParseException {
        MedicalRecordModel medicalRecordModel = medicalRecordService.getMedicalRecord("Marrack", "Jonanathan");
        medicalRecordService.deleteMedicalRecord(medicalRecordModel);
    }

    @Test
    void createMedicalRecordTest() throws IOException, ParseException {
        MedicalRecordModel medicalRecordModel = new MedicalRecordModel("Lily", "Cooper");
        medicalRecordService.createMedicalRecord(medicalRecordModel);
    }

    @Test
    void updateMedicalRecordTest() throws IOException, ParseException {
        MedicalRecordModel medicalRecordModel = new MedicalRecordModel("Ya", "Bou");
        medicalRecordService.updateMedicalRecord(medicalRecordModel);

        Assert.assertEquals("", medicalRecordModel.toString());

    }
}