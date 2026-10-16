package com.hotel.Hotel.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PenalizacionClienteTest {

    @Test
    void clienteDebeQuedarInactivoCon3Penalizaciones() {
        Cliente cliente = new Cliente("Carlos Ruiz", "carlos@gmail.com");

        // aplicar 3 penalizaciones
        cliente.registrarPenalizacion();
        cliente.registrarPenalizacion();
        cliente.registrarPenalizacion();

        assertFalse(cliente.isActivo());
        assertEquals(3, cliente.getPenalizaciones());
        assertFalse(cliente.puedeRealizarReservas());
    }
}
