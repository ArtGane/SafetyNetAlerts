package com.safetynet.SafetyNetAlerts.conf;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void getAgeTest() {
        int testAge = Utils.getAge("03/06/1984");
        assertEquals(38, testAge);
    }
}