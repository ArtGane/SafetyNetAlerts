package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.db.Infos;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private Environment environment;

    private String pathReel = environment.getProperty("path.reel");

    private AccessInfosImpl accesInfos = new AccessInfosImpl();

    private Infos infos = accesInfos.getInfos("");

    public List<PersonModel> getPersonsList(){
        return infos.getPersons();
    }

    public List<PersonModel> getPerson(PersonModel person) {
        return infos.getPersons();
    }

    public void deletePerson(PersonModel personModel) {
        List<PersonModel> persons = infos.getPersons();
        List<PersonModel> foundPerson = persons;
        persons.remove(foundPerson);
        infos.setPersons(persons);

        accesInfos.setInfos(pathReel, infos);
    }

    public void createPerson(PersonModel person) {
        List<PersonModel> persons = infos.getPersons();

        persons.add(person);
        infos.setPersons(persons);
        accesInfos.setInfos(pathReel, infos);
    }

    public void updatePerson(PersonModel personModel) {
        List<PersonModel> persons = infos.getPersons();

        List<PersonModel> foundPerson = persons;
        persons.remove(foundPerson);
        persons.add(personModel);
        infos.setPersons(persons);
        accesInfos.setInfos(pathReel, infos);

    }

}