package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.conf.Constantes;
import com.safetynet.SafetyNetAlerts.db.AccessInfos;
import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.db.Infos;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PersonService {

    private static final Logger logger = LogManager.getLogger("PersonService");

    private final static String pathReel = Constantes.PATH_REEL;

    private AccessInfos accesInfos = new AccessInfosImpl();

    private Infos infos;


    public List<PersonModel> getPersonsList() throws IOException, ParseException {
        infos = accesInfos.getInfos(pathReel);
        return infos.getPersons();
    }

    public PersonModel getPerson(String lastname, String firstname) throws IOException, ParseException {
        List<PersonModel> personModelList = getPersonsList();
        PersonModel personModel = personModelList.stream()
                .filter(p -> p.getLastName().equals(lastname) && p.getFirstName().equals(firstname))
                .findFirst().orElse(null);
        return personModel;
    }

    public void deletePerson(PersonModel personModel) throws IOException, ParseException {
        List<PersonModel> personModelList = getPersonsList();
        PersonModel person = getPerson(personModel.getLastName(), personModel.getFirstName());
        personModelList.remove(person);
        infos.setPersons(personModelList);

        accesInfos.setInfos(pathReel, infos);

        Assert.assertFalse(personModelList.contains(person));
        logger.info(personModelList);
    }

    public void createPerson(PersonModel person) throws IOException, ParseException {
        List<PersonModel> persons = getPersonsList();
        persons.add(person);
        infos.setPersons(persons);
        accesInfos.setInfos(pathReel, infos);

        Assert.assertTrue(persons.contains(person));
        logger.info(persons);
    }

    public void updatePerson(PersonModel person) throws IOException, ParseException {
        List<PersonModel> persons = getPersonsList();
        PersonModel personModel = getPerson(person.getLastName(), person.getFirstName());

        persons.remove(personModel);
        persons.add(person);

        infos.setPersons(persons);
        accesInfos.setInfos(pathReel, infos);

    }

}