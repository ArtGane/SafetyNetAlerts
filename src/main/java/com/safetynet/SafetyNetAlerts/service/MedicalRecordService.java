package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.db.Infos;
import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    @Value("${path.reel}")
    private String pathReel;

    @Autowired
    private AccessInfosImpl accesInfos;

    Infos infos = accesInfos.getInfos(pathReel);

    public List<MedicalRecordModel> getMedicalRecordList(){
        return infos.getMedicalrecords();
    }

    public List<MedicalRecordModel> getMedicalRecord(MedicalRecordModel medicalRecordModel) {
        return infos.getMedicalrecords();
    }

    public void deleteMedicalRecord(MedicalRecordModel medicalRecordModel) {

        List<MedicalRecordModel> medicalrecords = infos.getMedicalrecords();
        List<MedicalRecordModel> foundMedicalRecord = medicalrecords;
        medicalrecords.remove(medicalRecordModel);
        infos.setMedicalrecords(medicalrecords);

        accesInfos.setInfos(pathReel, infos);

    }

    public void createMedicalRecord(MedicalRecordModel medicalRecordModel) {

        List<MedicalRecordModel> medicalrecords = infos.getMedicalrecords();

        medicalrecords.add(medicalRecordModel);
        infos.setMedicalrecords(medicalrecords);
        accesInfos.setInfos(pathReel, infos);
    }

    public void updateMedicalRecord(MedicalRecordModel medicalRecordModel) {

        List<MedicalRecordModel> medicalrecords = infos.getMedicalrecords();

        List<MedicalRecordModel> foundMedicalRecord = medicalrecords;
        medicalrecords.remove(foundMedicalRecord);
        medicalrecords.add(medicalRecordModel);
        infos.setMedicalrecords(medicalrecords);
        accesInfos.setInfos(pathReel, infos);

    }
}