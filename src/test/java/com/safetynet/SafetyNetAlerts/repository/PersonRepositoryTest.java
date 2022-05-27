package com.safetynet.SafetyNetAlerts.repository;

import com.safetynet.SafetyNetAlerts.db.AccessInfos;
import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.db.Infos;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryTest {


    private PersonRepository personRepository = new PersonRepository();

    @Test
    void getPersonsList() throws IOException, ParseException {
        List<PersonModel> personModelList = personRepository.getPersonsList();
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
}