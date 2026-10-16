package com.hotel.Hotel.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class HabitacionEstadoTest {

    @Test
    void habitacionDebeQuedarOcupadaAlConfirmarReserva() {
        Cliente cliente = new Cliente("Ana Gomez", "ana@gmail.com");
        Habitacion hab = new HabitacionEstandar("202", 2, 120.0, 2);

        RangoFechas periodo = new RangoFechas(
                LocalDateTime.of(2027, 3, 10, 14, 0),
                LocalDateTime.of(2027, 3, 15, 14, 0));

        Reserva reserva = new Reserva(cliente, hab, periodo);
        reserva.confirmar();

        assertEquals(EstadoHabitacion.OCUPADA, hab.getEstado());
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
    }
}
