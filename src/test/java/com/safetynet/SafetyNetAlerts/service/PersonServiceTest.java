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
        Assert.assertEquals(personModelList.toString(), "[PersonModel(firstName=John, lastName=Boyd, address=1509 Culver St, city=Culver, zip=97451, phone=841-874-6512, email=jaboyd@email.com), PersonModel(firstName=Jacob, lastName=Boyd, address=1509 Culver St, city=Culver, zip=97451, phone=841-874-6513, email=drk@email.com), PersonModel(firstName=Tenley, lastName=Boyd, address=1509 Culver St, city=Culver, zip=97451, phone=841-874-6512, email=tenz@email.com), PersonModel(firstName=Roger, lastName=Boyd, address=1509 Culver St, city=Culver, zip=97451, phone=841-874-6512, email=jaboyd@email.com), PersonModel(firstName=Felicia, lastName=Boyd, address=1509 Culver St, city=Culver, zip=97451, phone=841-874-6544, email=jaboyd@email.com), PersonModel(firstName=Jonanathan, lastName=Marrack, address=29 15th St, city=Culver, zip=97451, phone=841-874-6513, email=drk@email.com), PersonModel(firstName=Tessa, lastName=Carman, address=834 Binoc Ave, city=Culver, zip=97451, phone=841-874-6512, email=tenz@email.com), PersonModel(firstName=Peter, lastName=Duncan, address=644 Gershwin Cir, city=Culver, zip=97451, phone=841-874-6512, email=jaboyd@email.com), PersonModel(firstName=Foster, lastName=Shepard, address=748 Townings Dr, city=Culver, zip=97451, phone=841-874-6544, email=jaboyd@email.com), PersonModel(firstName=Tony, lastName=Cooper, address=112 Steppes Pl, city=Culver, zip=97451, phone=841-874-6874, email=tcoop@ymail.com), PersonModel(firstName=Lily, lastName=Cooper, address=489 Manchester St, city=Culver, zip=97451, phone=841-874-9845, email=lily@email.com), PersonModel(firstName=Sophia, lastName=Zemicks, address=892 Downing Ct, city=Culver, zip=97451, phone=841-874-7878, email=soph@email.com), PersonModel(firstName=Warren, lastName=Zemicks, address=892 Downing Ct, city=Culver, zip=97451, phone=841-874-7512, email=ward@email.com), PersonModel(firstName=Zach, lastName=Zemicks, address=892 Downing Ct, city=Culver, zip=97451, phone=841-874-7512, email=zarc@email.com), PersonModel(firstName=Reginold, lastName=Walker, address=908 73rd St, city=Culver, zip=97451, phone=841-874-8547, email=reg@email.com), PersonModel(firstName=Jamie, lastName=Peters, address=908 73rd St, city=Culver, zip=97451, phone=841-874-7462, email=jpeter@email.com), PersonModel(firstName=Ron, lastName=Peters, address=112 Steppes Pl, city=Culver, zip=97451, phone=841-874-8888, email=jpeter@email.com), PersonModel(firstName=Allison, lastName=Boyd, address=112 Steppes Pl, city=Culver, zip=97451, phone=841-874-9888, email=aly@imail.com), PersonModel(firstName=Brian, lastName=Stelzer, address=947 E. Rose Dr, city=Culver, zip=97451, phone=841-874-7784, email=bstel@email.com), PersonModel(firstName=Shawna, lastName=Stelzer, address=947 E. Rose Dr, city=Culver, zip=97451, phone=841-874-7784, email=ssanw@email.com), PersonModel(firstName=Kendrik, lastName=Stelzer, address=947 E. Rose Dr, city=Culver, zip=97451, phone=841-874-7784, email=bstel@email.com), PersonModel(firstName=Clive, lastName=Ferguson, address=748 Townings Dr, city=Culver, zip=97451, phone=841-874-6741, email=clivfd@ymail.com), PersonModel(firstName=Eric, lastName=Cadigan, address=951 LoneArbre Rd, city=Culver, zip=97451, phone=841-874-7458, email=gramps@email.com)]");
    }

    @Test
    void getPersonTest() throws IOException, ParseException {
        PersonModel personModel = personService.getPerson("Boyd", "Felicia");
        Assert.assertEquals("Boyd", personModel.getLastName());
        Assert.assertEquals("Felicia", personModel.getFirstName());
    }

    @Test
    void deletePersonTest() throws IOException, ParseException {
        PersonModel personModelTest = new PersonModel("Fer", "Dit-Nan", "21 rue quelque part", "Labas", "8888", "0404040404", "mail@mail.mail");
        personService.createPerson(personModelTest);

        PersonModel personModel = personService.getPerson("Dit-Nan", "Fer");
        personService.deletePerson(personModel);

        Assert.assertFalse(personService.getPersonsList().contains(personModel));
    }

    @Test
    void createPersonTest() throws IOException, ParseException {
        PersonModel personModel = new PersonModel("Fer", "Dit-Nan", "21 rue quelque part", "Labas", "8888", "0404040404", "mail@mail.mail");
        personService.createPerson(personModel);

        Assert.assertTrue(personService.getPersonsList().contains(personModel));

        personService.deletePerson(personModel);
    }

    @Test
    void updatePersonTest() throws IOException, ParseException {
        PersonModel personModelTest = new PersonModel("Fer", "Dit-Nan", "21 rue quelque part", "Labas", "8888", "0404040404", "mail@mail.mail");
        personService.createPerson(personModelTest);

        PersonModel personModel = personService.getPerson("Dit-Nan", "Fer");
        personModel.setAddress("dans la savane");
        personService.updatePerson(personModel);

        Assert.assertEquals("dans la savane", personModel.getAddress());

        personService.deletePerson(personModel);
    }

}