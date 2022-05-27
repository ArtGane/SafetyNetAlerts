package com.safetynet.SafetyNetAlerts.db;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface AccessInfos {

    Infos getInfos(String path) throws IOException, ParseException;
    void setInfos(String path, Infos info);

}

