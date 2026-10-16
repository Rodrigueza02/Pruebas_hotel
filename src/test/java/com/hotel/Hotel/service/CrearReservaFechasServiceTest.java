package com.hotel.Hotel.service;

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
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ReservaService - validación de fechas")
class CrearReservaFechasServiceTest {

    @Mock private ReservaRepository reservaRepository;
    @Mock private ClienteRepository clienteRepository;
    @Mock private HabitacionRepository habitacionRepository;
    @Mock private ReservaMapper reservaMapper;

    @InjectMocks private ReservaService reservaService;

    @Test
    @DisplayName("Crear reserva con fechaFin antes que fechaInicio lanza ReglaNegocioException")
    void crearReservaFechasInvertidasLanzaExcepcion() {
        CrearReservaRequest request = new CrearReservaRequest(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.now().plusDays(5),  // inicio posterior
                LocalDateTime.now().plusDays(2)); // fin anterior

        assertThatThrownBy(() -> reservaService.crear(request))
                .isInstanceOf(ReglaNegocioException.class)
                .hasMessageContaining("fecha");
    }
}
