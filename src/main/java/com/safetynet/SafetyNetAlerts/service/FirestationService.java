package com.safetynet.SafetyNetAlerts.service;

import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.db.Infos;
import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
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

    public List<FirestationModel> getFirestationList() {
        Infos infos = accesInfos.getInfos(pathReel);
        return infos.getFirestations();
    }

    public FirestationModel getFirestation(int station, String address) {
        List<FirestationModel> fireStationList = getFirestationList();
        FirestationModel firestationModel = fireStationList.stream()
                .filter(f -> f.getStation() == station && f.getAddress().equals(address))
                .findFirst().orElse(null);
        return firestationModel;
    }

    public void deleteFirestation(FirestationModel firestationModel) {
        Infos infos = accesInfos.getInfos(pathReel);

        List<FirestationModel> firestations = infos.getFirestations();
        List<FirestationModel> foundFirestation = firestations;
        firestations.remove(firestationModel);
        infos.setFirestations(firestations);

        accesInfos.setInfos(pathReel, infos);

    }

    public void createFirestation(FirestationModel firestationModel) {
        Infos infos = accesInfos.getInfos(pathReel);

        List<FirestationModel> firestations = infos.getFirestations();
        firestations.add(firestationModel);
        infos.setFirestations(firestations);
        accesInfos.setInfos(pathReel, infos);
    }

    public void updateFirestation(FirestationModel firestationModel) {
        Infos infos = accesInfos.getInfos(pathReel);

        List<FirestationModel> firestation = infos.getFirestations();
        List<FirestationModel> foundFirestation = firestation;
        firestation.remove(foundFirestation);
        firestation.add(firestationModel);
        infos.setFirestations(firestation);
        accesInfos.setInfos(pathReel, infos);

    }
}