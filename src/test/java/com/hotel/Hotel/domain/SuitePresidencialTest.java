package com.hotel.Hotel.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SuitePresidencialTest {

    @Test
    void suiteDebebEstarDisponibleAlCrearla() {
        SuitePresidencial suite = new SuitePresidencial("101", 4, 500.0, true, false);

        assertEquals(EstadoHabitacion.DISPONIBLE, suite.getEstado());
        assertTrue(suite.isIncluyeMayordomo());
        assertFalse(suite.isJacuzziPrivado());
    }
}
