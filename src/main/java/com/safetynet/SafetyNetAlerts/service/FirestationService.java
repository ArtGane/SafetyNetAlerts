package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.conf.Constantes;
import com.safetynet.SafetyNetAlerts.db.AccessInfos;
import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.db.Infos;
import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FirestationService {

    private static final Logger logger = LogManager.getLogger("FirestationService");

    private final static String pathReel = Constantes.PATH_REEL;

    private AccessInfos accesInfos = new AccessInfosImpl();

    private Infos infos;


    public List<FirestationModel> getFirestationsList() throws IOException, ParseException {
        infos = accesInfos.getInfos(pathReel);
        return infos.getFirestations();
    }

    public FirestationModel getFirestation(int station, String address) throws IOException, ParseException {
        List<FirestationModel> firestationList = getFirestationsList();
        FirestationModel firestationModel = firestationList.stream()
                .filter(f -> f.getStation() == station && f.getAddress().equals(address)).findFirst().orElse(null);
        return firestationModel;
    }

    public void deleteFirestation(FirestationModel firestation) throws IOException, ParseException {
        List<FirestationModel> firestationModelList = getFirestationsList();
        FirestationModel firestationModel = getFirestation(firestation.getStation(), firestation.getAddress());

        firestationModelList.remove(firestationModel);
        infos.setFirestations(firestationModelList);

        accesInfos.setInfos(pathReel, infos);

        Assert.assertFalse(firestationModelList.contains(firestation));
        logger.info(firestationModelList);
    }

    public void createFirestation(FirestationModel firestation) throws IOException, ParseException {
        List<FirestationModel> firestationModelList = getFirestationsList();
        firestationModelList.add(firestation);
        infos.setFirestations(firestationModelList);
        accesInfos.setInfos(pathReel, infos);

        Assert.assertTrue(firestationModelList.contains(firestation));
        logger.info(firestationModelList);
    }

    public void updateFirestation(FirestationModel firestation) throws IOException, ParseException {
        List<FirestationModel> firestationModelList = getFirestationsList();
        FirestationModel firestationModel = getFirestation(firestation.getStation(), firestation.getAddress());

        firestationModelList.remove(firestationModel);
        firestationModelList.add(firestation);

        infos.setFirestations(firestationModelList);
        accesInfos.setInfos(pathReel, infos);
    }
}