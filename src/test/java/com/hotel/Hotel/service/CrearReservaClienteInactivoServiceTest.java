package com.hotel.Hotel.service;

import com.hotel.Hotel.domain.*;
import com.hotel.Hotel.dto.request.CrearReservaRequest;
import com.hotel.Hotel.exception.ReglaNegocioException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ReservaService - cliente inactivo no puede reservar")
class CrearReservaClienteInactivoServiceTest {

    @Mock private ReservaRepository reservaRepository;
    @Mock private ClienteRepository clienteRepository;
    @Mock private HabitacionRepository habitacionRepository;
    @Mock private ReservaMapper reservaMapper;

    @InjectMocks private ReservaService reservaService;

    @Test
    @DisplayName("Crear reserva con cliente penalizado (inactivo) lanza ReglaNegocioException")
    void crearReservaClienteInactivoLanzaExcepcion() {
        UUID clienteId    = UUID.randomUUID();
        UUID habitacionId = UUID.randomUUID();

        Cliente clientePenalizado = new Cliente("Camilo Peña", "camilo@hotel.com");
        clientePenalizado.registrarPenalizacion();
        clientePenalizado.registrarPenalizacion();
        clientePenalizado.registrarPenalizacion(); // 3 penalizaciones → inactivo

        Habitacion habitacion = new HabitacionEstandar("102", 2, 100.0, 1);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clientePenalizado));
        when(habitacionRepository.findById(habitacionId)).thenReturn(Optional.of(habitacion));

        CrearReservaRequest request = new CrearReservaRequest(
                clienteId, habitacionId,
                LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusDays(4));

        assertThatThrownBy(() -> reservaService.crear(request))
                .isInstanceOf(ReglaNegocioException.class);
    }
}
