package com.safetynet.SafetyNetAlerts.conf;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void extractYear() {
        int testAge = Utils.extractYear("03/06/1984");

        assertEquals(1984, testAge);
    }

    @Test
    void calculateAgeTest() {
       int testAge = Utils.calculateAge(1982);

       assertEquals(40, testAge);
    }
}