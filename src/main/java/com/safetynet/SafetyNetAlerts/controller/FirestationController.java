package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.repository.FirestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FirestationController {

    @Autowired
    private FirestationRepository firestationRepository;

    @PutMapping("firestations")
    public String updateFirestations(@RequestBody FirestationModel firestationModel) {
        firestationRepository.updateFirestation(firestationModel);
        return "Well done, you're house is burn !";
    }

    @PostMapping("firestations")
    public ResponseEntity<String> createFirestations(@RequestBody FirestationModel firestationModel) {
        firestationRepository.createFirestation(firestationModel);
        return ResponseEntity.ok("New Firestation, belong the calendar");
    }

    @DeleteMapping("firestations")
    public String deleteFirestations(@RequestBody FirestationModel firestationModel) {
        firestationRepository.deleteFirestation(firestationModel);
        return "This firestation is under estimated";
    }
}