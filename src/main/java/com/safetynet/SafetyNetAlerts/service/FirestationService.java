package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.db.Infos;
import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {

    @Value("${path.reel}")
    private String pathReel;

    @Autowired
    private AccessInfosImpl accesInfos;

    Infos infos = accesInfos.getInfos(pathReel);

    public List<FirestationModel> getFirestationList() {
        return infos.getFirestations();
    }

    public List<FirestationModel> getFirestation(FirestationModel firestationModel) {
        return infos.getFirestations();
    }

    public void deleteFirestation(FirestationModel firestationModel) {

        List<FirestationModel> firestations = infos.getFirestations();
        List<FirestationModel> foundFirestation = firestations;
        firestations.remove(firestationModel);
        infos.setFirestations(firestations);

        accesInfos.setInfos(pathReel, infos);

    }

    public void createFirestation(FirestationModel firestationModel) {

        List<FirestationModel> firestations = infos.getFirestations();
        firestations.add(firestationModel);
        infos.setFirestations(firestations);
        accesInfos.setInfos(pathReel, infos);
    }

    public void updateFirestation(FirestationModel firestationModel) {

        List<FirestationModel> firestation = infos.getFirestations();
        List<FirestationModel> foundFirestation = firestation;
        firestation.remove(foundFirestation);
        firestation.add(firestationModel);
        infos.setFirestations(firestation);
        accesInfos.setInfos(pathReel, infos);

    }
}