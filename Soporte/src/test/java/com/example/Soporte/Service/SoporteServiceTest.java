package com.example.Soporte.Service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Soporte.model.Soporte;
import com.example.Soporte.repository.SoporteRepository;
import com.example.Soporte.service.SoporteService;
import com.example.Soporte.webclient.CategoriaClient;
import com.example.Soporte.webclient.EstadoClient;
import com.example.Soporte.webclient.UserClient;

@ExtendWith(MockitoExtension.class)
public class SoporteServiceTest {
    @Mock
    private SoporteRepository soporteRepository;

    @Mock
    private EstadoClient estadoClient;

    @Mock
    private UserClient usuarioClient;

    @Mock
    private CategoriaClient categoriaClient;

    @InjectMocks
    private SoporteService soporteService;

    @Test
    void findAll_returnsListFromRepository() {
        List<Soporte> mockSoportes = List.of(
                new Soporte(1L, LocalDate.now(), "Problema con el sistema", null, null, null));
        when(soporteRepository.findAll()).thenReturn(mockSoportes);
        List<Soporte> soportes = soporteService.getSoporte();

        assertThat(soportes).isEqualTo(mockSoportes);
    }

}