package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HenkilotunnusTest {

    // https://dvv.fi/henkilotunnus
    // All tests are run by example person Maija Mäkinotkelma with "131052-308T" as social security number.
    // This person is not real and her social security number is free to use with tests.

    @Test
    @DisplayName("validate finnish social security number")
    void validateSocialSecurityNumber() {
        assertEquals(true, Henkilotunnus.validateSocialSecurityNumber("131052-308T"));
    }

    @Test
    @DisplayName("get age from finnish social security number")
    void getAge() {
        Henkilotunnus hetu = new Henkilotunnus("131052-308T");
        assertEquals(70, hetu.getAge());
    }

    @Test
    @DisplayName("get gender from finnish social security number")
    void getGender() {
        Henkilotunnus hetu = new Henkilotunnus("131052-308T");
        assertEquals("female", hetu.getGender());
    }
}