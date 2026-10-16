package com.hotel.Hotel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Habitacion - cambios de estado al confirmar y cancelar reserva")
class HabitacionEstadoTest {

    @Test
    @DisplayName("Confirmar una reserva ocupa la habitación; cancelarla la deja DISPONIBLE")
    void confirmarYCancelarCambianEstadoDeHabitacion() {
        Cliente cliente = new Cliente("Santiago Gil", "santiago@hotel.com");
        Habitacion hab  = new HabitacionEstandar("305", 3, 150.0, 2);

        RangoFechas periodo = new RangoFechas(
                LocalDateTime.of(2030, 7, 1, 14, 0),
                LocalDateTime.of(2030, 7, 4, 14, 0));

        Reserva reserva = new Reserva(cliente, hab, periodo);

        reserva.confirmar();
        assertThat(reserva.getEstado()).isEqualTo(EstadoReserva.CONFIRMADA);
        assertThat(hab.getEstado()).isEqualTo(EstadoHabitacion.OCUPADA);

        reserva.cancelar();
        assertThat(reserva.getEstado()).isEqualTo(EstadoReserva.CANCELADA);
        assertThat(hab.getEstado()).isEqualTo(EstadoHabitacion.DISPONIBLE);
    }
}
