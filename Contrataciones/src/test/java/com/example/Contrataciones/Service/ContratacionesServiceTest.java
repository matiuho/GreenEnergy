package com.example.Contrataciones.Service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Contrataciones.model.Contrataciones;
import com.example.Contrataciones.repository.ContratacionesRepository;
import com.example.Contrataciones.service.ContratacionesService;
import com.example.Contrataciones.webclient.ClienteClient;
import com.example.Contrataciones.webclient.DireccionClient;
import com.example.Contrataciones.webclient.UsuarioClient;

import static org.assertj.core.api.Assertions.assertThat;

//habilitamos la extension de mockito
@ExtendWith(MockitoExtension.class)
public class ContratacionesServiceTest {

    // simulo el repositorio de la contratacion
    @Mock
    private ContratacionesRepository contratacionesRepository;
    @Mock
    private ClienteClient clienteClient;
    @Mock
    private DireccionClient direccionClient;
    @Mock
    private UsuarioClient usuarioClient;

    // genera el mock de inyeccion del servicio
    @InjectMocks
    private ContratacionesService contratacionesService;

    @Test
    void findAll_returnsListFromRepository() {
        // crear un elemento que simule la respuesta del repositorio
        List<Contrataciones> listaContrataciones = Arrays.asList(new Contrataciones(
                1L,
                LocalDate.of(2025, 10, 10),
                LocalDate.of(2025, 10, 27),
                LocalDate.of(2026, 10, 11),
                null, null, null));

        // definir el comportamiento del mock (repositorio)
        when(contratacionesRepository.findAll()).thenReturn(listaContrataciones);

        // ejecutar el metodo a probar
        List<Contrataciones> result = contratacionesService.getContrataciones();

        // verificar el resultado (criterios de aceptacion)
        assertThat(result).isEqualTo(listaContrataciones);
    }

    @Test
    void save_returnsSavedContratacion() {
        
        Contrataciones nuevaContratacion = new Contrataciones(
                1L,
                LocalDate.of(2025, 10, 10),
                LocalDate.of(2025, 10, 27),
                LocalDate.of(2026, 10, 11),
                1L, 1L, 1L);

        // Simular las respuestas de los microservicios 
        Map<String, Object> servicioMock = Map.of("idServicio", 1L);
        Map<String, Object> direccionMock = Map.of("idDireccion", 1L);
        Map<String, Object> usuarioMock = Map.of("idUsuario", 1L);

        when(clienteClient.getServicioById(1L)).thenReturn(servicioMock);
        when(direccionClient.getDireccionById(1L)).thenReturn(direccionMock);
        when(usuarioClient.getUsuarioById(1L)).thenReturn(usuarioMock);

        // Simular el repositorio
        when(contratacionesRepository.save(nuevaContratacion)).thenReturn(nuevaContratacion);

        Contrataciones resultado = contratacionesService.saveContrataciones(nuevaContratacion);

        
        assertThat(resultado).isSameAs(nuevaContratacion);
    }

    @Test
    void findByid_returnsContratacionById() {
        Long id = 1L;
        Contrataciones contratacion = new Contrataciones(
                id,
                LocalDate.of(2025, 10, 10),
                LocalDate.of(2025, 10, 27),
                LocalDate.of(2026, 10, 11),
                null, null, null);

        // Simular el repositorio
        when(contratacionesRepository.findById(id)).thenReturn(java.util.Optional.of(contratacion));

        Contrataciones resultado = contratacionesService.getContrtacionPorId(id);

        assertThat(resultado).isSameAs(contratacion);

    }

    @Test
    void findByIdUsuario_returnsContratacionesList() {
        Long idUsuario = 1L;

        Contrataciones contratacion1 = new Contrataciones(
                1L,
                LocalDate.of(2025, 10, 10),
                LocalDate.of(2025, 10, 27),
                LocalDate.of(2026, 10, 11),
                2L, 3L, idUsuario);
        Contrataciones contratacion2 = new Contrataciones(
                2L,
                LocalDate.of(2025, 11, 1),
                LocalDate.of(2025, 11, 15),
                LocalDate.of(2026, 5, 20),
                4L, 5L, idUsuario);
        List<Contrataciones> contrataciones = Arrays.asList(contratacion1, contratacion2);

        when(contratacionesRepository.findByIdUsuario(idUsuario)).thenReturn(contrataciones);

        List<Contrataciones> resultado = contratacionesService.obtenerContratacionByUsuario(idUsuario);
        // Verificar que el resultado es el esperado
        assertThat(resultado).containsExactlyInAnyOrder(contratacion1, contratacion2);
    }

    
}