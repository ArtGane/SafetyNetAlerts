package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.db.AccessInfos;
import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.service.FirestationService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FirestationControllerTest {

    private FirestationController firestationController = new FirestationController(new FirestationService());

    @Test
    void updateFirestationsTest() throws IOException, ParseException {
        FirestationModel firestationModel = new FirestationModel();
        firestationController.updateFirestations(firestationModel);
    }

    @Test
    void createFirestationsTest() throws IOException, ParseException{
        FirestationModel firestationModel = new FirestationModel();
        firestationController.createFirestations(firestationModel);
    }

    @Test
    void deleteFirestationsTest() throws IOException, ParseException {
        FirestationModel firestationModel = new FirestationModel();
        firestationController.deleteFirestations(firestationModel);
    }
}