package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class FirestationServiceTest {

    private FirestationService firestationService = new FirestationService();

    @Test
    void getFirestationsListTest() throws IOException, ParseException {
        List<FirestationModel> firestationModelList = firestationService.getFirestationsList();
        Assert.assertEquals(firestationModelList.toString(), "[FirestationModel(address=1509 Culver St, station=3), FirestationModel(address=29 15th St, station=2), FirestationModel(address=834 Binoc Ave, station=3), FirestationModel(address=644 Gershwin Cir, station=1), FirestationModel(address=746 Tonwtonw Dr Wings, station=3), FirestationModel(address=112 Steppes Pl, station=3), FirestationModel(address=489 Manchester St, station=4), FirestationModel(address=892 Downing Ct, station=2), FirestationModel(address=908 73rd St, station=1), FirestationModel(address=112 Steppes Pl, station=4), FirestationModel(address=947 E. Rose Dr, station=1), FirestationModel(address=748 Townings Dr, station=3), FirestationModel(address=951 LoneTree Rd, station=2)]");
    }

    @Test
    void getFirestationTest() throws IOException, ParseException {
        FirestationModel firestationModel = firestationService.getFirestation("3", "112 Steppes Pl");
        Assert.assertEquals("3", firestationModel.getStation());
        Assert.assertEquals("112 Steppes Pl", firestationModel.getAddress());
    }

    @Test
    void deleteFirestationTest() throws IOException, ParseException {
        FirestationModel firestationModelTest = new FirestationModel("3", "3 rue avec du feu");
        firestationService.createFirestation(firestationModelTest);

        FirestationModel firestationModel = firestationService.getFirestation("3", "3 rue avec du feu");
        firestationService.deleteFirestation(firestationModel);

        Assert.assertFalse(firestationService.getFirestationsList().contains(firestationModel));
    }

    @Test
    void createFirestationTest() throws IOException, ParseException {
        FirestationModel firestationModel = new FirestationModel("3", "3 rue avec du feu");
        firestationService.createFirestation(firestationModel);

        Assert.assertTrue(firestationService.getFirestationsList().contains(firestationModel));

        firestationService.deleteFirestation(firestationModel);
    }

    @Test
    void updateFirestationTest() throws IOException, ParseException {
        FirestationModel firestationModelTest = new FirestationModel("3", "3 rue avec du feu");
        firestationService.createFirestation(firestationModelTest);

        FirestationModel firestationModel = firestationService.getFirestation("3", "3 rue avec du feu");
        firestationModel.setAddress("746 Tonwtonw Dr Wings");
        firestationService.updateFirestation(firestationModel);

        Assert.assertEquals("746 Tonwtonw Dr Wings", firestationModel.getAddress());

        firestationService.deleteFirestation(firestationModel);
    }
}