package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.model.PersonModel;
import com.safetynet.SafetyNetAlerts.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {


    @Autowired
    private PersonService personService;

    @PutMapping("persons")
    public String updatePerson(@RequestBody PersonModel personModel) {
        personService.updatePerson(personModel);
        return "Well done, we're now a man !";
    }

    @PostMapping("persons")
    public ResponseEntity<String> createPerson(@RequestBody PersonModel personModel) {
        personService.createPerson(personModel);
        return ResponseEntity.ok("Person ceated");
    }


    @DeleteMapping("persons")
    public String deletePerson(@RequestBody PersonModel personModel) {
        personService.deletePerson(personModel);
        return "This person is probably dead";
    }
}