package com.safetynet.SafetyNetAlerts.db;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.SafetyNetAlerts.model.FirestationModel;
import com.safetynet.SafetyNetAlerts.model.MedicalRecordModel;
import com.safetynet.SafetyNetAlerts.model.PersonModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;

public class AccessInfosImpl implements AccessInfos {

    private final Logger logger = LogManager.getLogger(AccessInfosImpl.class);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Infos getInfos(String path) {

        JSONParser jsonParser = new JSONParser();

        try {
            FileReader reader = new FileReader(path);
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            Infos infoTest = objectMapper.readValue(jsonObject.toString(), Infos.class);

            return infoTest;
        } catch (ParseException | IOException e) {
            logger.error("Error while reading database file." + e);
        }

        return null;
    }

    @Override
    public void setInfos(String path, Infos infos) {

        JSONArray personArray = new JSONArray();
        JSONArray firestationArray = new JSONArray();
        JSONArray medicalRecordsArray = new JSONArray();
        JSONObject jsonObjectList = new JSONObject();


        try {
            FileWriter writer = new FileWriter(path);

            for (PersonModel personObject : infos.getPersons()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("firstName", personObject.getFirstName());
                jsonObject.put("lastName", personObject.getLastName());
                jsonObject.put("address", personObject.getAddress());
                jsonObject.put("city", personObject.getCity());
                jsonObject.put("zip", personObject.getZip());
                jsonObject.put("phone", personObject.getPhone());
                jsonObject.put("email", personObject.getEmail());
                personArray.add(jsonObject);
            }
            for (FirestationModel firestationObject : infos.getFirestations()) {
                JSONObject jsonObject = new JSONObject();
                        jsonObject.put("address", firestationObject.getAddress());
                        jsonObject.put("station", firestationObject.getStation());
                firestationArray.add(jsonObject);
            }
            for (MedicalRecordModel medicalRecordObject : infos.getMedicalrecords()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("firstName", medicalRecordObject.getFirstName());
                jsonObject.put("lastName", medicalRecordObject.getLastName());
                jsonObject.put("birthdate", medicalRecordObject.getBirthdate());
                jsonObject.put("medications", medicalRecordObject.getMedications());
                jsonObject.put("allergies", medicalRecordObject.getAllergies());
                medicalRecordsArray.add(jsonObject);
            }

            jsonObjectList.put("persons", personArray);
            jsonObjectList.put("firestations", firestationArray);
            jsonObjectList.put("medicalrecords", medicalRecordsArray);
            writer.write(jsonObjectList.toString());
            writer.flush();
        } catch (IOException e) {
            logger.error("Error while writing in database file. ", e);
        }
    }

}

