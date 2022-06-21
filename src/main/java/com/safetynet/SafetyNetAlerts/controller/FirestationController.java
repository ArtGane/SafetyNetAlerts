package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.service.FirestationService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    @PutMapping("firestations")
    public String updateFirestations(@RequestBody FirestationModel firestationModel) throws IOException, ParseException {
        firestationService.updateFirestation(firestationModel);
        return "Well done, you're house is burn !";
    }

    @PostMapping("firestations")
    public ResponseEntity<String> createFirestations(@RequestBody FirestationModel firestationModel) throws IOException, ParseException {
        firestationService.createFirestation(firestationModel);
        return ResponseEntity.ok("New Firestation, belong the calendar");
    }

    @DeleteMapping("firestations")
    public String deleteFirestations(@RequestBody FirestationModel firestationModel) throws IOException, ParseException {
        firestationService.deleteFirestation(firestationModel);
        return "This firestation is under estimated";
    }
}