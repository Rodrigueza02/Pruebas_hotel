package com.hotel.Hotel.service;

import com.hotel.Hotel.domain.*;
import com.hotel.Hotel.dto.response.ReservaResponse;
import com.hotel.Hotel.mapper.ReservaMapper;
import com.hotel.Hotel.repository.ClienteRepository;
import com.hotel.Hotel.repository.HabitacionRepository;
import com.hotel.Hotel.repository.ReservaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ReservaService - confirmar reserva")
class ConfirmarReservaServiceTest {

    @Mock private ReservaRepository reservaRepository;
    @Mock private ClienteRepository clienteRepository;
    @Mock private HabitacionRepository habitacionRepository;
    @Mock private ReservaMapper reservaMapper;

    @InjectMocks private ReservaService reservaService;

    @Test
    @DisplayName("Confirmar reserva existente cambia estado a CONFIRMADA")
    void confirmarReservaExistenteRetornaConfirmada() {
        UUID reservaId  = UUID.randomUUID();
        Cliente cliente = new Cliente("Isabella Mora", "isabella@hotel.com");
        Habitacion hab  = new HabitacionEstandar("305", 2, 200.0, 1);
        RangoFechas periodo = new RangoFechas(
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(4));
        Reserva reserva = new Reserva(cliente, hab, periodo);

        ReservaResponse responseEsperado = new ReservaResponse(
                reservaId, "Isabella Mora", "305",
                periodo.fechaInicio(), periodo.fechaFin(), 600.0, "CONFIRMADA");

        when(reservaRepository.findById(reservaId)).thenReturn(Optional.of(reserva));
        when(reservaRepository.save(any(Reserva.class))).thenAnswer(inv -> inv.getArgument(0));
        when(reservaMapper.toResponse(any(Reserva.class))).thenReturn(responseEsperado);

        ReservaResponse resultado = reservaService.confirmar(reservaId);

        assertThat(resultado.estado()).isEqualTo("CONFIRMADA");
        assertThat(reserva.getEstado()).isEqualTo(EstadoReserva.CONFIRMADA);
        verify(reservaRepository).save(reserva);
    }
}
