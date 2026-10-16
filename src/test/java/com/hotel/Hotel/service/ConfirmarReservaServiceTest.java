package com.hotel.Hotel.service;

import com.hotel.Hotel.domain.*;
import com.hotel.Hotel.dto.response.ReservaResponse;
import com.hotel.Hotel.mapper.ReservaMapper;
import com.hotel.Hotel.repository.ClienteRepository;
import com.hotel.Hotel.repository.HabitacionRepository;
import com.hotel.Hotel.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfirmarReservaServiceTest {

    @Mock private ReservaRepository reservaRepository;
    @Mock private ClienteRepository clienteRepository;
    @Mock private HabitacionRepository habitacionRepository;
    @Mock private ReservaMapper reservaMapper;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    void debeConfirmarReservaYCambiarEstado() {
        UUID id = UUID.randomUUID();

        Cliente cliente = new Cliente("Jorge Pena", "jorge@gmail.com");
        Habitacion hab = new HabitacionEstandar("410", 2, 200.0, 1);
        RangoFechas periodo = new RangoFechas(
                LocalDateTime.of(2027, 8, 1, 14, 0),
                LocalDateTime.of(2027, 8, 3, 14, 0));

        Reserva reserva = new Reserva(cliente, hab, periodo);

        ReservaResponse responseMock = new ReservaResponse(
                id, "Jorge Pena", "410",
                periodo.fechaInicio(), periodo.fechaFin(), 400.0, "CONFIRMADA");

        when(reservaRepository.findById(id)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(inv -> inv.getArgument(0));
        when(reservaMapper.toResponse(any(Reserva.class))).thenReturn(responseMock);

        ReservaResponse resultado = reservaService.confirmar(id);

        assertEquals("CONFIRMADA", resultado.estado());
        assertEquals(EstadoReserva.CONFIRMADA, reserva.getEstado());
    }
}
