package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.db.AccessInfos;
import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.db.AccessInfosImplTest;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import com.safetynet.SafetyNetAlerts.service.PersonService;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonControllerTest {

    private PersonController personController = new PersonController(new PersonService());

    @Test
    void updatePersonTest() throws IOException, ParseException {
        PersonModel personModel = new PersonModel("Eric", "Cadigan", "951 LoneArbre Rd", "Culver", "97451", "841-874-7458", "gramps@email.com");
        personController.updatePerson(personModel);
        Assert.assertEquals("PersonModel(firstName=Eric, lastName=Cadigan, address=951 LoneArbre Rd, city=Culver, zip=97451, phone=841-874-7458, email=gramps@email.com)", personModel.toString());
    }

    @Test
    void createPersonTest() throws IOException, ParseException {
        PersonModel personModel = new PersonModel("Eric", "Cadigan", "951 LoneArbre Rd", "Culver", "97451", "841-874-7458", "gramps@email.com");
        personController.createPerson(personModel);
        //Assert.assertTrue();
    }

    @Test
    void deletePersonTest() throws IOException, ParseException {
        PersonModel personModel = new PersonModel("Eric", "Cadigan", "951 LoneArbre Rd", "Culver", "97451", "841-874-7458", "gramps@email.com");
        personController.deletePerson(personModel);
    }

}