package com.hotel.Hotel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Cliente - reglas de negocio")
class ClienteTest {

    @Test
    @DisplayName("Cliente nuevo nace activo, sin penalizaciones y con id generado")
    void clienteNuevoNaceActivoSinPenalizaciones() {
        Cliente cliente = new Cliente("Valentina Rios", "valentina@hotel.com");

        assertThat(cliente.getId()).isNotNull();
        assertThat(cliente.getNombre()).isEqualTo("Valentina Rios");
        assertThat(cliente.isActivo()).isTrue();
        assertThat(cliente.getPenalizaciones()).isZero();
        assertThat(cliente.puedeRealizarReservas()).isTrue();
    }
}
