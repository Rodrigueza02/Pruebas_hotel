package com.hotel.Hotel.service;

import com.hotel.Hotel.domain.*;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrearReservaClienteInactivoServiceTest {

    @Mock private ReservaRepository reservaRepository;
    @Mock private ClienteRepository clienteRepository;
    @Mock private HabitacionRepository habitacionRepository;
    @Mock private ReservaMapper reservaMapper;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    void debeLanzarExcepcionSiClienteEstaInactivo() {
        UUID clienteId = UUID.randomUUID();
        UUID habitacionId = UUID.randomUUID();

        // cliente con 3 penalizaciones queda inactivo
        Cliente cliente = new Cliente("Sofia Rios", "sofia@gmail.com");
        cliente.registrarPenalizacion();
        cliente.registrarPenalizacion();
        cliente.registrarPenalizacion();

        Habitacion hab = new HabitacionEstandar("108", 2, 100.0, 1);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
        when(habitacionRepository.findById(habitacionId)).thenReturn(Optional.of(hab));

        CrearReservaRequest request = new CrearReservaRequest(
                clienteId, habitacionId,
                LocalDateTime.of(2027, 4, 1, 14, 0),
                LocalDateTime.of(2027, 4, 5, 14, 0));

        assertThrows(ReglaNegocioException.class, () -> reservaService.crear(request));
    }
}
