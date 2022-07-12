package com.safetynet.SafetyNetAlerts.conf;

import java.time.LocalDate;
import java.util.Date;

public abstract class Utils {

    public static int extractYear(String birthdate) {
        String[] listBirthdate = birthdate.split("/");
        return Integer.parseInt(listBirthdate[2]);
    }

    public static int calculateAge(int year) {
        int dateToday = LocalDate.now().getYear();
        int calcul = dateToday - year;
        return calcul;
    }

}
