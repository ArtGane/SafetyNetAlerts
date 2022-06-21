package com.safetynet.SafetyNetAlerts.controller;

import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.service.MedicalRecordService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PutMapping("medicalrecords")
    public String updateMedicalRecord(@RequestBody MedicalRecordModel medicalRecordModel) throws IOException, ParseException {
        medicalRecordService.updateMedicalRecord(medicalRecordModel);
        return "Well done, you're more sick now !";
    }

    @PostMapping("medicalrecords")
    public ResponseEntity<String> createMedicalRecord(@RequestBody MedicalRecordModel medicalRecordModel) throws IOException, ParseException {
        medicalRecordService.createMedicalRecord(medicalRecordModel);
        return ResponseEntity.ok("Medical records ceated");
    }

    @DeleteMapping("medicalrecords")
    public String deleteMedicalRecord(@RequestBody MedicalRecordModel medicalRecordModel) throws IOException, ParseException {
        medicalRecordService.deleteMedicalRecord(medicalRecordModel);
        return "This medical records is probably nothing";
    }
}