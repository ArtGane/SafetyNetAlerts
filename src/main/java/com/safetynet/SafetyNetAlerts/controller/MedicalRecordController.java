package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicalRecordController {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @PutMapping("medicalrecords")
    public String updateMedicalRecord(@RequestBody MedicalRecordModel medicalRecordModel) {
        medicalRecordRepository.updateMedicalRecord(medicalRecordModel);
        return "Well done, you're more sick now !";
    }

    @PostMapping("medicalrecords")
    public ResponseEntity<String> createMedicalRecord(@RequestBody MedicalRecordModel medicalRecordModel) {
        medicalRecordRepository.createMedicalRecord(medicalRecordModel);
        return ResponseEntity.ok("Medical records ceated");
    }

    @DeleteMapping("medicalrecords")
    public String deleteMedicalRecord(@RequestBody MedicalRecordModel medicalRecordModel) {
        medicalRecordRepository.deleteMedicalRecord(medicalRecordModel);
        return "This medical records is probably nothing";
    }
}