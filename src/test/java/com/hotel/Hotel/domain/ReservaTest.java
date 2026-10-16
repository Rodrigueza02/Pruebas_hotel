package com.hotel.Hotel.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Reserva - estado inicial y cálculo de costo")
class ReservaTest {

    @Test
    @DisplayName("Reserva nueva nace en estado PENDIENTE y calcula costoTotal correctamente")
    void reservaNuevaPendienteConCostoCorrecto() {
        Cliente cliente = new Cliente("Isabella Mora", "isabella@hotel.com");
        Habitacion hab  = new HabitacionEstandar("201", 2, 200.0, 1);

        LocalDateTime inicio = LocalDateTime.of(2030, 5, 10, 14, 0);
        LocalDateTime fin    = LocalDateTime.of(2030, 5, 14, 14, 0); // 4 días exactos
        RangoFechas periodo  = new RangoFechas(inicio, fin);

        Reserva reserva = new Reserva(cliente, hab, periodo);

        assertThat(reserva.getEstado()).isEqualTo(EstadoReserva.PENDIENTE);
        assertThat(reserva.getCostoTotal()).isEqualTo(800.0); // 200 * 4
    }
}
