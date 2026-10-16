package com.hotel.Hotel.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void clienteNuevoDebeEstarActivo() {
        // crear cliente nuevo
        Cliente cliente = new Cliente("Laura Torres", "laura@gmail.com");

        assertTrue(cliente.isActivo());
        assertEquals(0, cliente.getPenalizaciones());
    }
}
