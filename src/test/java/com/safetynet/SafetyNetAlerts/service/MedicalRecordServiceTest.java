package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordServiceTest {

    private MedicalRecordService medicalRecordService = new MedicalRecordService();
    @Test
    void getMedicalRecordsListTest() throws IOException, ParseException {
        List<MedicalRecordModel> medicalRecordModelList = medicalRecordService.getMedicalRecordsList();
        Assert.assertEquals("[MedicalRecordModel(firstName=John, lastName=Boyd, birthdate=03/06/1984, medications=[aznol:350mg, hydrapermazol:100mg], allergies=[nillacilan]), MedicalRecordModel(firstName=Jacob, lastName=Boyd, birthdate=03/06/1989, medications=[pharmacol:5000mg, terazine:10mg, noznazol:250mg], allergies=[]), MedicalRecordModel(firstName=Tenley, lastName=Boyd, birthdate=02/18/2012, medications=[], allergies=[peanut]), MedicalRecordModel(firstName=Roger, lastName=Boyd, birthdate=09/06/2017, medications=[], allergies=[]), MedicalRecordModel(firstName=Felicia, lastName=Boyd, birthdate=01/08/1986, medications=[tetracyclaz:650mg], allergies=[xilliathal]), MedicalRecordModel(firstName=Jonanathan, lastName=Marrack, birthdate=01/03/1989, medications=[], allergies=[]), MedicalRecordModel(firstName=Tessa, lastName=Carman, birthdate=02/18/2012, medications=[], allergies=[]), MedicalRecordModel(firstName=Peter, lastName=Duncan, birthdate=09/06/2000, medications=[], allergies=[shellfish]), MedicalRecordModel(firstName=Foster, lastName=Shepard, birthdate=01/08/1980, medications=[], allergies=[]), MedicalRecordModel(firstName=Tony, lastName=Cooper, birthdate=03/06/1994, medications=[hydrapermazol:300mg, dodoxadin:30mg], allergies=[shellfish]), MedicalRecordModel(firstName=Lily, lastName=Cooper, birthdate=03/06/1994, medications=[], allergies=[]), MedicalRecordModel(firstName=Sophia, lastName=Zemicks, birthdate=03/06/1988, medications=[aznol:60mg, hydrapermazol:900mg, pharmacol:5000mg, terazine:500mg], allergies=[peanut, shellfish, aznol]), MedicalRecordModel(firstName=Warren, lastName=Zemicks, birthdate=03/06/1985, medications=[], allergies=[]), MedicalRecordModel(firstName=Zach, lastName=Zemicks, birthdate=03/06/2017, medications=[], allergies=[]), MedicalRecordModel(firstName=Reginold, lastName=Walker, birthdate=08/30/1979, medications=[thradox:700mg], allergies=[illisoxian]), MedicalRecordModel(firstName=Jamie, lastName=Peters, birthdate=03/06/1982, medications=[], allergies=[]), MedicalRecordModel(firstName=Ron, lastName=Peters, birthdate=04/06/1965, medications=[], allergies=[]), MedicalRecordModel(firstName=Allison, lastName=Boyd, birthdate=03/15/1965, medications=[aznol:200mg], allergies=[nillacilan]), MedicalRecordModel(firstName=Brian, lastName=Stelzer, birthdate=12/06/1975, medications=[ibupurin:200mg, hydrapermazol:400mg], allergies=[nillacilan]), MedicalRecordModel(firstName=Shawna, lastName=Stelzer, birthdate=07/08/1980, medications=[], allergies=[]), MedicalRecordModel(firstName=Kendrik, lastName=Stelzer, birthdate=03/06/2014, medications=[noxidian:100mg, pharmacol:2500mg], allergies=[]), MedicalRecordModel(firstName=Clive, lastName=Ferguson, birthdate=03/06/1994, medications=[], allergies=[]), MedicalRecordModel(firstName=Eric, lastName=Cadigan, birthdate=08/06/1945, medications=[tradoxidine:400mg], allergies=[])]", medicalRecordModelList.toString());
    }

    @Test
    void getMedicalRecordTest() throws IOException, ParseException {
        MedicalRecordModel medicalRecordModel = medicalRecordService.getMedicalRecord("Stelzer", "Brian");
        Assert.assertEquals("Stelzer", medicalRecordModel.getLastName());
        Assert.assertEquals("Brian", medicalRecordModel.getFirstName());
    }

    @Test
    void deleteMedicalRecordTest() throws IOException, ParseException {
        MedicalRecordModel medicalRecordModel = medicalRecordService.getMedicalRecord("Stelzer", "Shawna");
        medicalRecordService.deleteMedicalRecord(medicalRecordModel);
    }

    @Test
    void createMedicalRecordTest() throws IOException, ParseException {
        List<String> medications = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        MedicalRecordModel medicalRecordModel = new MedicalRecordModel("Stelzer", "Shawna", "03/05/1974", medications, allergies);
        medicalRecordService.createMedicalRecord(medicalRecordModel);
    }

    @Test
    void updateMedicalRecordTest() throws IOException, ParseException {
        List<String> medications = new ArrayList<>();
        medications.add("doliprane 1000mg");

        List<String> allergies = new ArrayList<>();
        allergies.add("nillacilan");

        MedicalRecordModel medicalRecordModel = new MedicalRecordModel("Marrack", "Jonanathan", "13/13/1666", medications, allergies);
        medicalRecordService.updateMedicalRecord(medicalRecordModel);

        Assert.assertEquals("MedicalRecordModel(firstName=Jonanathan, lastName=Marrack, birthdate=13/13/1666, medications=[doliprane 1000mg], allergies=[nillacilan])", medicalRecordModel.toString());

    }
}