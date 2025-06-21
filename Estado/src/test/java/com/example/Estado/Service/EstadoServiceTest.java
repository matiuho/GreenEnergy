package com.example.Estado.Service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Estado.model.Estado;
import com.example.Estado.repository.EstadoRepository;
import com.example.Estado.service.EstadoService;

@ExtendWith(MockitoExtension.class)
public class EstadoServiceTest {

    @Mock 
    private EstadoRepository estadoRepository;
    @InjectMocks
    private EstadoService estadoService;

    @Test
    void findAll_returnsListFromRepository(){
        List<Estado> mockList = Arrays.asList(new Estado (1L, "Activo"));

        when(estadoRepository.findAll()).thenReturn(mockList);

        List<Estado> result = estadoService.getEstados();

        assertThat(result).isEqualTo(mockList);

    }

    @Test
    void findEstadoById_returnEstadoFromRepository() {
        Estado mockEstado = new Estado(1L, "Activo");

        when(estadoRepository.findById(1L)).thenReturn(java.util.Optional.of(mockEstado));
        Estado result = estadoService.getEstadoPorId(1L);
        assertThat(result).isEqualTo(mockEstado);
    }


}
