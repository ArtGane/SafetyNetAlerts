package com.safetynet.SafetyNetAlerts.conf;

import com.safetynet.SafetyNetAlerts.dto.HomeDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Utils {

    private static int extractYear(String birthdate) {
        String[] listBirthdate = birthdate.split("/");
        return Integer.parseInt(listBirthdate[2]);
    }

    private static int calculateAge(int year) {
        int dateToday = LocalDate.now().getYear();
        return dateToday - year;
    }

    public static int getAge(String birthdate) {
        return calculateAge(extractYear(birthdate));
    }

}
