package com.hotel.Hotel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Cliente - penalizaciones y desactivación")
class PenalizacionClienteTest {

    @Test
    @DisplayName("Con 3 penalizaciones el cliente queda inactivo y no puede hacer reservas")
    void tresPenalizacionesDesactivanAlCliente() {
        Cliente cliente = new Cliente("Camilo Peña", "camilo@hotel.com");

        cliente.registrarPenalizacion();
        cliente.registrarPenalizacion();
        cliente.registrarPenalizacion();

        assertThat(cliente.isActivo()).isFalse();
        assertThat(cliente.getPenalizaciones()).isEqualTo(3);
        assertThat(cliente.puedeRealizarReservas()).isFalse();
    }
}
