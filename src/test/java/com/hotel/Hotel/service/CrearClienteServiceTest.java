package com.hotel.Hotel.service;

import com.hotel.Hotel.domain.Cliente;
import com.hotel.Hotel.dto.request.CrearClienteRequest;
import com.hotel.Hotel.dto.response.ClienteResponse;
import com.hotel.Hotel.mapper.ClienteMapper;
import com.hotel.Hotel.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrearClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void debeGuardarClienteYRetornarResponse() {
        CrearClienteRequest request = new CrearClienteRequest("Luis Mora", "luis@gmail.com");
        Cliente clienteGuardado = new Cliente("Luis Mora", "luis@gmail.com");
        ClienteResponse response = new ClienteResponse(clienteGuardado.getId(), "Luis Mora", "luis@gmail.com", true, 0);

        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteGuardado);
        when(clienteMapper.toResponse(clienteGuardado)).thenReturn(response);

        ClienteResponse resultado = clienteService.crear(request);

        assertEquals("Luis Mora", resultado.nombre());
        assertTrue(resultado.activo());
        verify(clienteRepository).save(any(Cliente.class));
    }
}
