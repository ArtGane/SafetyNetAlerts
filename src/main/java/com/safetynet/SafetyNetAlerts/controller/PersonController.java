package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.model.PersonModel;
import com.safetynet.SafetyNetAlerts.service.PersonService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/persons")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PutMapping
    public PersonModel updatePerson(@RequestBody PersonModel personModel) throws IOException, ParseException {
        personService.updatePerson(personModel);
        return personModel;
    }

    @PostMapping
    public PersonModel createPerson(@RequestBody PersonModel personModel) throws IOException, ParseException {
        personService.createPerson(personModel);
        return personModel;
    }

    @DeleteMapping
    public String deletePerson(@RequestBody PersonModel personModel) throws IOException, ParseException {
        personService.deletePerson(personModel);
        return "This person is probably dead";
    }

}