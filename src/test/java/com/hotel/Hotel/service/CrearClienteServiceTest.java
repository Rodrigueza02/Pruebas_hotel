package com.hotel.Hotel.service;

import com.hotel.Hotel.domain.Cliente;
import com.hotel.Hotel.dto.request.CrearClienteRequest;
import com.hotel.Hotel.dto.response.ClienteResponse;
import com.hotel.Hotel.mapper.ClienteMapper;
import com.hotel.Hotel.repository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ClienteService - crear cliente")
class CrearClienteServiceTest {

    @Mock private ClienteRepository clienteRepository;
    @Mock private ClienteMapper clienteMapper;

    @InjectMocks private ClienteService clienteService;

    @Test
    @DisplayName("Crear cliente: persiste y retorna ClienteResponse con nombre y email correctos")
    void crearClienteRetornaResponseCorrecto() {
        CrearClienteRequest request = new CrearClienteRequest("Valentina Rios", "valentina@hotel.com");
        Cliente clienteGuardado = new Cliente("Valentina Rios", "valentina@hotel.com");
        ClienteResponse responseEsperado = new ClienteResponse(
                clienteGuardado.getId(), "Valentina Rios", "valentina@hotel.com", true, 0);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteGuardado);
        when(clienteMapper.toResponse(clienteGuardado)).thenReturn(responseEsperado);

        ClienteResponse resultado = clienteService.crear(request);

        assertThat(resultado.nombre()).isEqualTo("Valentina Rios");
        assertThat(resultado.email()).isEqualTo("valentina@hotel.com");
        assertThat(resultado.activo()).isTrue();
        verify(clienteRepository).save(any(Cliente.class));
    }
}
