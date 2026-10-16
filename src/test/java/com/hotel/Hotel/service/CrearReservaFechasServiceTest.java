package com.hotel.Hotel.service;

import com.hotel.Hotel.dto.request.CrearReservaRequest;
import com.hotel.Hotel.exception.ReglaNegocioException;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CrearReservaFechasServiceTest {

    @Mock private ReservaRepository reservaRepository;
    @Mock private ClienteRepository clienteRepository;
    @Mock private HabitacionRepository habitacionRepository;
    @Mock private ReservaMapper reservaMapper;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    void debeLanzarExcepcionSiFechaFinEsAntesDeFechaInicio() {
        // fechaFin antes que fechaInicio → inválido
        CrearReservaRequest request = new CrearReservaRequest(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.of(2027, 5, 10, 14, 0),
                LocalDateTime.of(2027, 5, 5, 14, 0));

        assertThrows(ReglaNegocioException.class, () -> reservaService.crear(request));
    }
}
