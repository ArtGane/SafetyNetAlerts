package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class PersonServiceTest {

    private PersonService personService = new PersonService();

    @Test
    void getPersonsListTest() throws IOException, ParseException {
        List<PersonModel> personModelList = personService.getPersonsList();
        Assert.assertEquals(personModelList.get(5).toString(), "PersonModel(firstName=Jonanathan, lastName=Marrack, address=29 15th St, city=Culver, zip=97451, phone=841-874-6513, email=drk@email.com)");
    }

    @Test
    void getPersonTest() throws IOException, ParseException {
        PersonModel personModel = personService.getPerson("Cooper", "Lily");
        Assert.assertEquals("Cooper", personModel.getLastName());
        Assert.assertEquals("Lily", personModel.getFirstName());
    }

    @Test
    void deletePersonTest() throws IOException, ParseException {
        personService.deletePerson(personModel);
    }

    @Test
    void createPersonTest() throws IOException, ParseException {
        PersonModel personModel = new PersonModel("Bou", "Ya", "21 rue quelque part", "Labas", "8888", "0404040404", "mail@mail.mail");
        personService.createPerson(personModel);
    }

    @Test
    void updatePerson() throws IOException, ParseException {
        PersonModel personModel = new PersonModel("Ya", "Bou", "21 rue quelque part", "Labas", "8888", "0404040404", "mail@mail.mail");
        personService.updatePerson(personModel);

        Assert.assertEquals(personModel, "");
    }

}