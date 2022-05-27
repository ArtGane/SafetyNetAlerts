package com.safetynet.SafetyNetAlerts.db;

import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccessInfosImplTest {

    private AccessInfos accessInfos = new AccessInfosImpl();

    private String pathReel = "C:\\Users\\argan\\IdeaProjects\\SafetyNetAlerts\\src\\main\\resources\\infos.json";
    private String pathTest = "C:\\Users\\argan\\IdeaProjects\\SafetyNetAlerts\\src\\main\\resources\\data.json";

    @Test
    public void getInfosShouldReturnCompleteListOfPersons() throws IOException, ParseException {
        Infos infos = accessInfos.getInfos(pathReel);
        assertEquals(infos.getPersons().size(), 23);
    }

    @Test
    public void setInfosFirestationTest() throws IOException, ParseException {
        Infos infos = new Infos();
        List<FirestationModel> firestationList = new ArrayList<>();
        List<PersonModel> personList = new ArrayList<>();
        List<MedicalRecordModel> medicalRecordList = new ArrayList<>();
        FirestationModel firestationModel = new FirestationModel();

        firestationModel.setAddress("21 rue des meduses");
        firestationModel.setStation(2);

        firestationList.add(firestationModel);
        infos.setFirestations(firestationList);
        infos.setPersons(personList);
        infos.setMedicalrecords(medicalRecordList);

        accessInfos.setInfos(pathTest, infos);
        Infos getInfos = accessInfos.getInfos(pathTest);
        Assert.assertEquals(getInfos.getFirestations().size(), 1);
    }

    @Test
    public void setInfosPersonTest() throws IOException, ParseException {
        Infos infos = new Infos();
        List<FirestationModel> firestationList = new ArrayList<>();
        List<PersonModel> personList = new ArrayList<>();
        List<MedicalRecordModel> medicalRecordList = new ArrayList<>();
        PersonModel personModel = new PersonModel();

        personModel.setFirstName("Mia");
        personModel.setLastName("Ou");
        personModel.setAddress("21 rue des meduses");
        personModel.setCity("Felinegrad");
        personModel.setZip("8888");
        personModel.setPhone("0606060606");
        personModel.setEmail("mia.ou@truc.fr");

        personList.add(personModel);
        infos.setFirestations(firestationList);
        infos.setPersons(personList);
        infos.setMedicalrecords(medicalRecordList);

        accessInfos.setInfos(pathTest, infos);
        Infos getInfos = accessInfos.getInfos(pathTest);
        Assert.assertEquals(getInfos.getPersons().get(0).getFirstName(), "Mia");
    }

//    @Test
//    public void setInfosMedicalRecordsTest() throws IOException, ParseException {
//        Infos infos = new Infos();
//        List<FirestationModel> firestationList = new ArrayList<>();
//        List<PersonModel> personList = new ArrayList<>();
//        List<MedicalRecordModel> medicalRecordList = new ArrayList<>();
//        MedicalRecordModel medicalRecordModel = new MedicalRecordModel();
//
//        medicalRecordModel.setFirstName("Mia");
//        medicalRecordModel.setLastName("Ou");
//        medicalRecordModel.setBirthdate("12/12/1986");
//        medicalRecordModel.setMedications(new ArrayList<String>("diabete", "nanisme"));
//        medicalRecordModel.setAllergies(new ArrayList<String>("bees", "jellyfish"));
//
//        medicalRecordList.add(medicalRecordModel);
//        infos.setFirestations(firestationList);
//        infos.setPersons(personList);
//        infos.setMedicalrecords(medicalRecordList);
//
//        accessInfos.setInfos(pathTest, infos);
//        Infos getInfos = accessInfos.getInfos(pathTest);
//        Assert.assertEquals(getInfos.getPersons().get(0).getFirstName(), "Mia");
//    }
}