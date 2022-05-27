package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.db.AccessInfos;
import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.db.AccessInfosImplTest;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PersonControllerTest {

    @Value("${path.reel}")
    private String pathReel;

    private AccessInfos accessInfos = new AccessInfosImpl();
    private PersonController personController = new PersonController();
    private PersonModel personModel = new PersonModel();

    @Test
    void updatePerson() throws IOException, ParseException {
        accessInfos.getInfos(pathReel);
        personController.updatePerson(personModel);
    }

    @Test
    void createPerson() {
    }

    @Test
    void deletePerson() {
    }

}