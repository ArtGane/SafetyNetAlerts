package com.safetynet.SafetyNetAlerts.repository;

import com.safetynet.SafetyNetAlerts.db.AccessInfosImpl;
import com.safetynet.SafetyNetAlerts.db.Infos;
import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirestationRepository {

    @Autowired
    private AccessInfosImpl accesInfos;

    Infos infos = accesInfos.getInfos();

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

        accesInfos.setInfos(infos);

    }

    public void createFirestation(FirestationModel firestationModel) {

        List<FirestationModel> firestations = infos.getFirestations();
        firestations.add(firestationModel);
        infos.setFirestations(firestations);
        accesInfos.setInfos(infos);
    }

    public void updateFirestation(FirestationModel firestationModel) {

        List<FirestationModel> firestation = infos.getFirestations();
        List<FirestationModel> foundFirestation = firestation;
        firestation.remove(foundFirestation);
        firestation.add(firestationModel);
        infos.setFirestations(firestation);
        accesInfos.setInfos(infos);

    }
}