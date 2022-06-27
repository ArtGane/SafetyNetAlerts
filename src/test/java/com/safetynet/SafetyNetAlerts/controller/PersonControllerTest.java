package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.model.PersonModel;
import com.safetynet.SafetyNetAlerts.service.PersonService;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private PersonController personController = new PersonController();

    @Test
    public void mocktest() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", "Eric");
        jsonObject.put("lastName", "Cadigan");
        jsonObject.put("address", "951 LoneArbre Rd");
        jsonObject.put("city", "Culver");
        jsonObject.put("zip", "97451");
        jsonObject.put("phone", "841-874-7458");
        jsonObject.put("email", "gramps@email.com");
        mockMvc.perform(
                post("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonObject.toString()))
                .andExpect(status().isOk());

    }

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