package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.conf.Constantes;
import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class FirestationServiceTest {

    private FirestationService firestationService = new FirestationService();

    @Test
    void createFirestationTest() throws IOException, ParseException {
        FirestationModel firestationModel = new FirestationModel("3", "3 rue avec du feu");
        firestationService.createFirestation(firestationModel);

        Assert.assertTrue(firestationService.getFirestationsList().contains(firestationModel));

        firestationService.deleteFirestation(firestationModel);
    }

    @Test
    void getFirestationsListTest() throws IOException, ParseException {
        List<FirestationModel> firestationModelList = firestationService.getFirestationsList();
        Assert.assertEquals(13, firestationModelList.size());
    }

    @Test
    void getFirestationAddressTest() throws IOException, ParseException {
        FirestationModel firestationModel = firestationService.getFirestation("1509 Culver St");

        Assert.assertEquals("1509 Culver St", firestationModel.getAddress());
    }

    @Test
    void deleteFirestationTest() throws IOException, ParseException {
        FirestationModel firestationModelTest = new FirestationModel("3", "3 rue avec du feu");
        firestationService.createFirestation(firestationModelTest);

        FirestationModel firestationModelDelete = firestationService.getFirestation("3 rue avec du feu");
        firestationService.deleteFirestation(firestationModelDelete);

        Assert.assertFalse(firestationService.getFirestationsList().contains(firestationModelDelete));
    }


    @Test
    void updateFirestationTest() throws IOException, ParseException {
        FirestationModel firestationModelTest = new FirestationModel("3", "3 rue avec du feu");
        firestationService.createFirestation(firestationModelTest);

        FirestationModel firestationModel = firestationService.getFirestation("3 rue avec du feu");
        firestationModel.setAddress("746 Tonwtonw Dr Wings");
        firestationService.updateFirestation(firestationModel);

        Assert.assertEquals("746 Tonwtonw Dr Wings", firestationModel.getAddress());

    }
}