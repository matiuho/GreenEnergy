package com.example.direccion.Service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.direccion.model.Comuna;
import com.example.direccion.model.Region;
import com.example.direccion.repository.ComunaRepository;
import com.example.direccion.service.ComunaService;

@ExtendWith(MockitoExtension.class)
public class ComunaServiceTest {

    // simulo el repositorio de la comuna
    @Mock
    private ComunaRepository comunaRepository;

    @InjectMocks
    private ComunaService comunaService;

    @Test
    void findAll_returnsListFromRepository() {
        List<Comuna> ListaComuna = Arrays.asList(new Comuna(1L, "Comuna de prueba", new Region(1, "Región de prueba")));

        // definir el comportamiento del mock (repositorio)
        when(comunaRepository.findAll()).thenReturn(ListaComuna);

        // ejecutar el metodo a probar
        List<Comuna> result = comunaService.getComunas();

        // verificar el resultado (criterios de aceptacion)
        assertThat(result).isEqualTo(ListaComuna);

    }

    @Test
    void findById_returnsComunaById(){
        Long id = 1L;
        Comuna comuna = new Comuna(id, "Comuna de prueba", new Region(1, "Región de prueba"));

        //simular el repositorio
        when(comunaRepository.findById(id)).thenReturn(java.util.Optional.of(comuna));

        Comuna result = comunaService.obtenerComunaPorId(id);

        // verificar el resultado (criterios de aceptacion)
        assertThat(result).isEqualTo(comuna);
    }



}
