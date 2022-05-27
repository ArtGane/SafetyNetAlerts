package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FirestationController {

    @Autowired
    private FirestationService firestationService;

    @PutMapping("firestations")
    public String updateFirestations(@RequestBody FirestationModel firestationModel) {
        firestationService.updateFirestation(firestationModel);
        return "Well done, you're house is burn !";
    }

    @PostMapping("firestations")
    public ResponseEntity<String> createFirestations(@RequestBody FirestationModel firestationModel) {
        firestationService.createFirestation(firestationModel);
        return ResponseEntity.ok("New Firestation, belong the calendar");
    }

    @DeleteMapping("firestations")
    public String deleteFirestations(@RequestBody FirestationModel firestationModel) {
        firestationService.deleteFirestation(firestationModel);
        return "This firestation is under estimated";
    }
}