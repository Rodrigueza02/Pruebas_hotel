package com.hotel.Hotel.service;

import com.hotel.Hotel.exception.RecursoNoEncontradoException;
import com.hotel.Hotel.mapper.ClienteMapper;
import com.hotel.Hotel.repository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ClienteService - buscar cliente por id")
class ObtenerClienteServiceTest {

    @Mock private ClienteRepository clienteRepository;
    @Mock private ClienteMapper clienteMapper;

    @InjectMocks private ClienteService clienteService;

    @Test
    @DisplayName("Buscar cliente con id inexistente lanza RecursoNoEncontradoException")
    void obtenerClientePorIdInexistenteLanzaExcepcion() {
        UUID idInexistente = UUID.randomUUID();
        when(clienteRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> clienteService.obtenerPorId(idInexistente))
                .isInstanceOf(RecursoNoEncontradoException.class);
    }
}
