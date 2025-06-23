package com.example.servicio.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.servicio.model.Servicio;
import com.example.servicio.repository.ServicioRepository;
import com.example.servicio.service.ServicioService;

@ExtendWith(MockitoExtension.class)
public class ServicioServiceTest {
    @Mock
    private ServicioRepository servicioRepository;

    @InjectMocks
    private ServicioService servicioService;

    @Test
    void findAll_returnsListFromRepository() {
        List<Servicio> listaServicios = Arrays
                .asList(new Servicio(1L, "Servicio prueba", "prueba", 10000, true));
        when(servicioRepository.findByActivoTrue()).thenReturn(listaServicios);

        List<Servicio> result = servicioService.listarServiciosActivos();
        assertThat(result).isEqualTo(listaServicios);
    }

    @Test
    void findById_returnsServicionById() {
        Long id = 1L;
        Servicio servicio = new Servicio(1L, "prueba", "descripcion", 10000, true);
        when(servicioRepository.findById(id)).thenReturn(Optional.of(servicio));
        Servicio result = servicioService.getServicioPorId(id);
        assertThat(result).isEqualTo(servicio);
    }

    @Test
    void saveServicio_returnsSavedServicio() {
        Servicio mockServicio = new Servicio(1L, "prueba", "descripcion", 10000, true);

        when(servicioRepository.save(mockServicio)).thenReturn(mockServicio);

        Servicio result = servicioService.saveServicio(mockServicio);

        assertThat(result).isEqualTo(mockServicio);
    }

    @Test
    void deleteById_deletesServicio() {
        Long id = 1L;
        Servicio servicio = new Servicio(id, "Servicio de prueba", "Descripción", 10000, true);
        when(servicioRepository.findById(id)).thenReturn(Optional.of(servicio));

        servicioService.eliminarservicio(id);

        when(servicioRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Servicio> resultadoPostEliminacion = servicioRepository.findById(id);

        assertThat(resultadoPostEliminacion).isEmpty();
    }

    @Test
    void updateServicio_updatesAndReturnsServicio() {
        Long id = 1L;

        Servicio existente = new Servicio(id, "Servicio viejo", "Descripción vieja", 5000, true);
        Servicio modificacion = new Servicio(id, "Servicio nuevo", "Descripción nueva", 10000, true);

        when(servicioRepository.findById(id)).thenReturn(Optional.of(existente));
        when(servicioRepository.save(any(Servicio.class))).thenReturn(modificacion);

        Servicio resultado = servicioService.actualizarServicio(id, modificacion);

        assertThat(resultado).isEqualTo(modificacion);
    }

    @Test
    void desactivarServicio() {
        Servicio activo = new Servicio(1L, "Paneles Solares", "Instalación", 17990, true);
        Servicio desactivado = new Servicio(1L, "Paneles Solares", "Instalación", 17990, false);

        when(servicioRepository.findById(1L)).thenReturn(Optional.of(activo));

        when(servicioRepository.save(any(Servicio.class))).thenReturn(desactivado);

        Servicio resultado = servicioService.desactivarServicio(1L);

        assertThat(resultado).isEqualTo(desactivado);
        verify(servicioRepository).findById(1L);
        verify(servicioRepository).save(activo);
    }


    @Test
    void activarServicio() {
         Servicio desactivado = new Servicio(1L, "Paneles Solares", "Instalación", 17990, false);
        Servicio activo = new Servicio(1L, "Paneles Solares", "Instalación", 17990, true);
       

        when(servicioRepository.findById(1L)).thenReturn(Optional.of(desactivado));

        when(servicioRepository.save(any(Servicio.class))).thenReturn(activo);

        Servicio resultado = servicioService.activarServicio(1L);

        assertThat(resultado).isEqualTo(activo);
        verify(servicioRepository).findById(1L);
        verify(servicioRepository).save(desactivado);
    }

}
