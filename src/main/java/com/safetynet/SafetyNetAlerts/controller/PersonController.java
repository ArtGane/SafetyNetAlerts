package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.model.PersonModel;
import com.safetynet.SafetyNetAlerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {


    @Autowired
    private PersonRepository personRepository;

    @PutMapping("persons")
    public String updatePerson(@RequestBody PersonModel personModel) {
        personRepository.updatePerson(personModel);
        return "Well done, we're now a man !";
    }

    @PostMapping("persons")
    public ResponseEntity<String> createPerson(@RequestBody PersonModel personModel) {
        personRepository.createPerson(personModel);
        return ResponseEntity.ok("Person ceated");
    }


    @DeleteMapping("persons")
    public String deletePerson(@RequestBody PersonModel personModel) {
        personRepository.deletePerson(personModel);
        return "This person is probably dead";
    }
}