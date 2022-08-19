package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

class PersonServiceTest {

    private PersonService personService = new PersonService();

    @Test
    void getPersonsListTest() throws IOException, ParseException {
        List<PersonModel> personModelList = personService.getPersonsList();
        assertEquals(23, personModelList.size());
    }

    @Test
    void getPersonTest() throws IOException, ParseException {
        PersonModel personModel = personService.getPerson("Boyd", "Felicia");
        assertEquals("Boyd", personModel.getLastName());
        assertEquals("Felicia", personModel.getFirstName());
    }

    @Test
    void deletePersonTest() throws IOException, ParseException {
        PersonModel personModelTest = new PersonModel("Fer", "Dit-Nan", "21 rue quelque part", "Labas", "8888", "0404040404", "mail@mail.mail");
        personService.createPerson(personModelTest);

        PersonModel personModel = personService.getPerson("Dit-Nan", "Fer");
        personService.deletePerson(personModel);

        assertFalse(personService.getPersonsList().contains(personModel));
    }

    @Test
    void createPersonTest() throws IOException, ParseException {
        PersonModel personModel = new PersonModel("Fer", "Dit-Nan", "21 rue quelque part", "Labas", "8888", "0404040404", "mail@mail.mail");
        personService.createPerson(personModel);

        assertTrue(personService.getPersonsList().contains(personModel));

        personService.deletePerson(personModel);
    }

    @Test
    void updatePersonTest() throws IOException, ParseException {
        PersonModel personModelTest = new PersonModel("Fer", "Dit-Nan", "21 rue quelque part", "Labas", "8888", "0404040404", "mail@mail.mail");
        personService.createPerson(personModelTest);

        PersonModel personModel = personService.getPerson("Dit-Nan", "Fer");
        personModel.setAddress("dans la savane");
        personService.updatePerson(personModel);

        assertEquals("dans la savane", personModel.getAddress());

        personService.deletePerson(personModel);
    }

}