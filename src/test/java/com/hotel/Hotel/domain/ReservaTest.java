package com.hotel.Hotel.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    @Test
    void reservaNuevaDebebEstarEnEstadoPendiente() {
        Cliente cliente = new Cliente("Pedro Salinas", "pedro@gmail.com");
        Habitacion hab = new HabitacionEstandar("305", 2, 150.0, 1);

        RangoFechas periodo = new RangoFechas(
                LocalDateTime.of(2027, 6, 1, 14, 0),
                LocalDateTime.of(2027, 6, 4, 14, 0)); // 3 noches

        Reserva reserva = new Reserva(cliente, hab, periodo);

        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado());
        assertEquals(450.0, reserva.getCostoTotal()); // 150 x 3
    }
}
