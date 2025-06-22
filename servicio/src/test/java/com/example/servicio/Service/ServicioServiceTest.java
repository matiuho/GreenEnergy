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
                .asList(new Servicio(1L, "Servicio prueba", "prueba", 10000, "Disponible"));
        when(servicioRepository.findAll()).thenReturn(listaServicios);

        List<Servicio> result = servicioService.obtenerTodos();
        assertThat(result).isEqualTo(listaServicios);
    }

    @Test
    void findById_returnsServicionById() {
        Long id = 1L;
        Servicio servicio = new Servicio(1L, "prueba", "descripcion", 10000, "Disponible");
        when(servicioRepository.findById(id)).thenReturn(Optional.of(servicio));
        Servicio result = servicioService.getServicioPorId(id);
        assertThat(result).isEqualTo(servicio);
    }

    @Test
    void saveServicio_returnsSavedServicio() {
        Servicio mockServicio = new Servicio(1L, "prueba", "descripcion", 10000, "Disponible");
        when(servicioRepository.save(mockServicio)).thenReturn(mockServicio);
        Servicio result = servicioService.saveServicio(mockServicio);
        assertThat(result).isEqualTo(mockServicio);
    }

    @Test
    void deleteById_deletesServicio() {
        Long id = 1L;
        Servicio servicio = new Servicio(id, "Servicio de prueba", "Descripción", 10000, "Disponible");
        when(servicioRepository.findById(id)).thenReturn(Optional.of(servicio));

        servicioService.eliminarservicio(id);

        when(servicioRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Servicio> resultadoPostEliminacion = servicioRepository.findById(id);

        assertThat(resultadoPostEliminacion).isEmpty();
        verify(servicioRepository).deleteById(id);
    }

    @Test
    void updateServicio_updatesAndReturnsServicio() {
        Long id = 1L;

        Servicio existente = new Servicio(id, "Servicio viejo", "Descripción vieja", 5000, "Inactivo");
        Servicio modificacion = new Servicio(id, "Servicio nuevo", "Descripción nueva", 10000, "Disponible");

        when(servicioRepository.findById(id)).thenReturn(Optional.of(existente));
        when(servicioRepository.save(any(Servicio.class))).thenReturn(modificacion);

        Servicio resultado = servicioService.actualizarServicio(id, modificacion);

        assertThat(resultado.getNombre()).isEqualTo("Servicio nuevo");
        assertThat(resultado.getDescripcion()).isEqualTo("Descripción nueva");
        assertThat(resultado.getPrecio()).isEqualTo(10000);
        assertThat(resultado.getDisponibilidad()).isEqualTo("Disponible");
    }

}
