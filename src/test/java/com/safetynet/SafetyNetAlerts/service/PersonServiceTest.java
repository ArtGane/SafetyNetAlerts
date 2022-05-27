package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    private PersonService personService = new PersonService();

    @Test
    void getPersonsList() {
        List<PersonModel> personModelList = personService.getPersonsList();
        Assert.assertEquals(personModelList, 23);
    }

    @Test
    void getPerson() {
    }

    @Test
    void deletePerson() {
    }

    @Test
    void createPerson() {
    }

    @Test
    void updatePerson() {
    }

    @Test
    void getAccesInfos() {
    }

    @Test
    void getInfos() {
    }
}