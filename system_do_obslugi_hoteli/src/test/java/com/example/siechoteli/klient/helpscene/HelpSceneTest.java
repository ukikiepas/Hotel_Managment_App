package com.example.siechoteli.klient.helpscene;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelpSceneTest {

    HelpScene helpScene = new HelpScene("Spectra", "Lukasz", 123,1234,"LALALLAL", 1);

    @Test
    void getHotelName() {
        assertEquals("Spectra", helpScene.getHotelName());
    }

    @Test
    void getYourName() {
        assertEquals("Lukasz", helpScene.getYourName());

    }

    @Test
    void getEmployeeID() {
        assertEquals(123, helpScene.getEmployeeID());

    }

    @Test
    void getContactNumber() {
        assertEquals(1234, helpScene.getContactNumber());
    }

    @Test
    void getIssue() {
        assertEquals("LALALLAL", helpScene.getIssue());
    }


}