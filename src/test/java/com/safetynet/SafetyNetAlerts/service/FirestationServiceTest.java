package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FirestationServiceTest {

    private FirestationService firestationService = new FirestationService();

    @Test
    void getFirestationsList() throws IOException, ParseException {
        List<FirestationModel> firestationModelList = firestationService.getFirestationsList();
        Assert.assertEquals(firestationModelList.get(5).toString(), "");
    }

    @Test
    void getFirestation() throws IOException, ParseException {
        FirestationModel firestationModel = firestationService.getFirestation(2, "");
        Assert.assertEquals("", firestationModel.getAddress());
        Assert.assertEquals("", firestationModel.getStation());
    }

    @Test
    void deleteFirestation() throws IOException, ParseException {
        FirestationModel firestationModel = firestationService.getFirestation(1, "");
        firestationService.deleteFirestation(firestationModel);
    }

    @Test
    void createFirestation() throws IOException, ParseException {
        FirestationModel firestationModel = new FirestationModel(3, "3 rue avec du feu");
        firestationService.createFirestation(firestationModel);
    }

    @Test
    void updateFirestation() throws IOException, ParseException {
        FirestationModel firestationModel = new FirestationModel(1, "3 rue avec du feu");
        firestationService.updateFirestation(firestationModel);

        Assert.assertEquals("", firestationModel.toString());

    }
}