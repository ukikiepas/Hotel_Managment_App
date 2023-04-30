package com.example.siechoteli.klient.guests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomsTest {

    Rooms room = new Rooms(1,2,3,1,"HIGH","FREE","2001-12-12", "2001-12-12", "Spectra");

    @Test
    void getRoomID() {
        assertEquals(1, room.getRoomID());
    }

    @Test
    void getRoomNumber() {
        assertEquals(2, room.getRoomNumber());
    }

    @Test
    void getPrice_PN() {
        assertEquals(3, room.getPrice_PN());
    }

    @Test
    void getBedQuantity() {
        assertEquals(1, room.getBedQuantity());
    }

    @Test
    void getStandard() {
        assertEquals(1, room.getStandard());
    }

    @Test
    void getAvaliable() {
        assertEquals(1, room.getAvaliable());
    }

    @Test
    void getStartDate() {
        assertEquals("2001-12-12", room.getStartDate());
    }

    @Test
    void getFinishDate() {
        assertEquals("2001-12-12", room.getFinishDate());
    }

    @Test
    void getHotelName() {
        assertEquals("Spectra", room.getHotelName());
    }
}
