package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

class PersonServiceTest {

    private PersonService personService = new PersonService();

    @Test
    void getPersonsListTest() throws IOException, ParseException {
        List<PersonModel> personModelList = personService.getPersonsList();
        Assert.assertEquals(personModelList.get(5).toString(), "PersonModel(firstName=Jonanathan, lastName=Marrack, address=29 15th St, city=Culver, zip=97451, phone=841-874-6513, email=drk@email.com)");
    }

    @Test
    void getPersonTest() throws IOException, ParseException {
        PersonModel personModel = personService.getPerson("Boyd", "Felicia");
        Assert.assertEquals("Boyd", personModel.getLastName());
        Assert.assertEquals("Felicia", personModel.getFirstName());
    }

    @Test
    void deletePersonTest() throws IOException, ParseException {
        PersonModel personModel = personService.getPerson("Marrack", "Jonanathan");
        personService.deletePerson(personModel);
    }

    @Test
    void createPersonTest() throws IOException, ParseException {
        PersonModel personModel = new PersonModel("Lily", "Cooper", "21 rue quelque part", "Labas", "8888", "0404040404", "mail@mail.mail");
        personService.createPerson(personModel);
    }

    @Test
    void updatePerson() throws IOException, ParseException {
        PersonModel personModel = new PersonModel("Ya", "Bou", "dans la savane", "Jura-zik", "8888", "0404040404", "mail@mail.mail");
        personService.updatePerson(personModel);

        Assert.assertEquals("PersonModel(firstName=Ya, lastName=Bou, address=dans la savane, city=Jura-zik, zip=8888, phone=0404040404, email=mail@mail.mail)", personModel.toString());
    }

}