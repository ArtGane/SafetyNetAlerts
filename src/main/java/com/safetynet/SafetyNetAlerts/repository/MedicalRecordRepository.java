package com.safetynet.SafetyNetAlerts.repository;

import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.db.Infos;
import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordRepository {

    @Autowired
    private AccessInfosImpl accesInfos;

    Infos infos = accesInfos.getInfos();

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

        accesInfos.setInfos(infos);

    }

    public void createMedicalRecord(MedicalRecordModel medicalRecordModel) {

        List<MedicalRecordModel> medicalrecords = infos.getMedicalrecords();

        medicalrecords.add(medicalRecordModel);
        infos.setMedicalrecords(medicalrecords);
        accesInfos.setInfos(infos);
    }

    public void updateMedicalRecord(MedicalRecordModel medicalRecordModel) {

        List<MedicalRecordModel> medicalrecords = infos.getMedicalrecords();

        List<MedicalRecordModel> foundMedicalRecord = medicalrecords;
        medicalrecords.remove(foundMedicalRecord);
        medicalrecords.add(medicalRecordModel);
        infos.setMedicalrecords(medicalrecords);
        accesInfos.setInfos(infos);

    }
}