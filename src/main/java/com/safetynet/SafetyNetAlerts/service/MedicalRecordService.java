package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.conf.Constantes;
import com.safetynet.SafetyNetAlerts.db.AccessInfos;
import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.db.Infos;
import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MedicalRecordService {

    private static final Logger logger = LogManager.getLogger("MedicalRecordService");

    private final static String pathReel = Constantes.PATH_REEL;

    private AccessInfos accesInfos = new AccessInfosImpl();

    private Infos infos;


    public List<MedicalRecordModel> getMedicalRecordsList() throws IOException, ParseException {
        infos = accesInfos.getInfos(pathReel);
        return infos.getMedicalrecords();
    }

    public MedicalRecordModel getMedicalRecord(String lastname, String firstname) throws IOException, ParseException {
        List<MedicalRecordModel> medicalRecordModelList = getMedicalRecordsList();
        MedicalRecordModel medicalrecord = medicalRecordModelList.stream()
                .filter(mr -> mr.getLastName().equals(lastname) && mr.getFirstName().equals(firstname)).findFirst().orElse(null);
        return medicalrecord;
    }

    public void deleteMedicalRecord(MedicalRecordModel medicalrecord) throws IOException, ParseException {
        List<MedicalRecordModel> medicalRecordModelList = getMedicalRecordsList();
        MedicalRecordModel medicalRecordModel = getMedicalRecord(medicalrecord.getLastName(), medicalrecord.getFirstName());

        medicalRecordModelList.remove(medicalRecordModel);
        infos.setMedicalrecords(medicalRecordModelList);

        accesInfos.setInfos(pathReel, infos);

        logger.info(medicalRecordModelList);
    }

    public void createMedicalRecord(MedicalRecordModel medicalrecord) throws IOException, ParseException {
        List<MedicalRecordModel> medicalRecordModelList = getMedicalRecordsList();
        medicalRecordModelList.add(medicalrecord);
        infos.setMedicalrecords(medicalRecordModelList);
        accesInfos.setInfos(pathReel, infos);

        logger.info(medicalRecordModelList);
    }

    public void updateMedicalRecord(MedicalRecordModel medicalrecord) throws IOException, ParseException {
        List<MedicalRecordModel> medicalRecordModelList = getMedicalRecordsList();
        MedicalRecordModel medicalRecordModel = getMedicalRecord(medicalrecord.getLastName(), medicalrecord.getFirstName());

        medicalRecordModelList.remove(medicalRecordModel);
        medicalRecordModelList.add(medicalrecord);

        infos.setMedicalrecords(medicalRecordModelList);
        accesInfos.setInfos(pathReel, infos);
    }
}