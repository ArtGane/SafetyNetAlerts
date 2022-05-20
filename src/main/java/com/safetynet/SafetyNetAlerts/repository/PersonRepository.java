package com.safetynet.SafetyNetAlerts.repository;

import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.db.Infos;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository {

    @Autowired
    private AccessInfosImpl accesInfos;

    Infos infos = accesInfos.getInfos();

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

        accesInfos.setInfos(infos);

    }

    public void createPerson(PersonModel person) {

        List<PersonModel> persons = infos.getPersons();

        persons.add(person);
        infos.setPersons(persons);
        accesInfos.setInfos(infos);
    }

    public void updatePerson(PersonModel personModel) {

        List<PersonModel> persons = infos.getPersons();

        List<PersonModel> foundPerson = persons;
        persons.remove(foundPerson);
        persons.add(personModel);
        infos.setPersons(persons);
        accesInfos.setInfos(infos);

    }
}