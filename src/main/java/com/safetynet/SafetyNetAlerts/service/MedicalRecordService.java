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

    public List<MedicalRecordModel> getMedicalRecordList(){
        Infos infos = accesInfos.getInfos(pathReel);

        return infos.getMedicalrecords();
    }

    public List<MedicalRecordModel> getMedicalRecord(MedicalRecordModel medicalRecordModel) {
        Infos infos = accesInfos.getInfos(pathReel);

        return infos.getMedicalrecords();
    }

    public void deleteMedicalRecord(MedicalRecordModel medicalRecordModel) {
        Infos infos = accesInfos.getInfos(pathReel);

        List<MedicalRecordModel> medicalrecords = infos.getMedicalrecords();
        List<MedicalRecordModel> foundMedicalRecord = medicalrecords;
        medicalrecords.remove(medicalRecordModel);
        infos.setMedicalrecords(medicalrecords);

        accesInfos.setInfos(pathReel, infos);

    }

    public void createMedicalRecord(MedicalRecordModel medicalRecordModel) {
        Infos infos = accesInfos.getInfos(pathReel);

        List<MedicalRecordModel> medicalrecords = infos.getMedicalrecords();

        medicalrecords.add(medicalRecordModel);
        infos.setMedicalrecords(medicalrecords);
        accesInfos.setInfos(pathReel, infos);
    }

    public void updateMedicalRecord(MedicalRecordModel medicalRecordModel) {
        Infos infos = accesInfos.getInfos(pathReel);

        List<MedicalRecordModel> medicalrecords = infos.getMedicalrecords();

        List<MedicalRecordModel> foundMedicalRecord = medicalrecords;
        medicalrecords.remove(foundMedicalRecord);
        medicalrecords.add(medicalRecordModel);
        infos.setMedicalrecords(medicalrecords);
        accesInfos.setInfos(pathReel, infos);

    }
}