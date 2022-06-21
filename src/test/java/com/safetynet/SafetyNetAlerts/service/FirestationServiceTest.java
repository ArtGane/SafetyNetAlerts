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
        Assert.assertEquals(firestationModelList.toString(), "[FirestationModel(address=1509 Culver St, station=3), FirestationModel(address=29 15th St, station=2), FirestationModel(address=834 Binoc Ave, station=3), FirestationModel(address=644 Gershwin Cir, station=1), FirestationModel(address=748 Townings Dr, station=3), FirestationModel(address=112 Steppes Pl, station=3), FirestationModel(address=489 Manchester St, station=4), FirestationModel(address=892 Downing Ct, station=2), FirestationModel(address=908 73rd St, station=1), FirestationModel(address=112 Steppes Pl, station=4), FirestationModel(address=947 E. Rose Dr, station=1), FirestationModel(address=748 Townings Dr, station=3), FirestationModel(address=951 LoneTree Rd, station=2)]");
    }

    @Test
    void getFirestation() throws IOException, ParseException {
        FirestationModel firestationModel = firestationService.getFirestation(3, "112 Steppes Pl");
        Assert.assertEquals(3, firestationModel.getStation());
        Assert.assertEquals("112 Steppes Pl", firestationModel.getAddress());
    }

    @Test
    void deleteFirestation() throws IOException, ParseException {
        FirestationModel firestationModel = firestationService.getFirestation(4, "112 Steppes Pl");
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

        Assert.assertEquals("FirestationModel(address=3 rue avec du feu, station=1)", firestationModel.toString());

    }
}