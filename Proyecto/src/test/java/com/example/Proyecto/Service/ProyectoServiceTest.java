package com.example.Proyecto.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Proyecto.Model.Proyecto;
import com.example.Proyecto.Repository.ProyectoRepository;
import com.example.Proyecto.WebClient.ContratacionClient;
import com.example.Proyecto.WebClient.EstadoClient;
import com.example.Proyecto.WebClient.UsuarioClient;

@ExtendWith(MockitoExtension.class)
public class ProyectoServiceTest {
    @Mock
    private ProyectoRepository proyectoRepository;
    @Mock
    ContratacionClient contratacionClient;
    @Mock
    EstadoClient estadoClient;
    @Mock
    UsuarioClient usuarioClient;

    @InjectMocks
    private ProyectosService proyectosService;

    @Test
    void findAll_returnsListFromRepository() {
        List<Proyecto> listaProyectos = Arrays.asList(new Proyecto(
                1L,
                "Prueba",
                null,
                null,
                null));
        when(proyectoRepository.findAll()).thenReturn(listaProyectos);
        List<Proyecto> result = proyectosService.getProyectos();
        assertThat(result).isEqualTo(listaProyectos);
    }

    @Test
    void getProyectoPorId_returnsProyectoById() {
        Proyecto proyecto = new Proyecto(1L, "Test", 1L, 1L, 1L);
        when(proyectoRepository.findById(1L)).thenReturn(Optional.of(proyecto));

        Proyecto result = proyectosService.getProyectoPorId(1L);

        assertThat(result).isEqualTo(proyecto);
    }

    @Test
    void save_returnsSavedProyecto() {
        Proyecto nuevoProyecto = new Proyecto(
            1L,
            "Nuevo Proyecto",
            1L, 1L, 1L);

        //simulas las respuestas de los microservicios
        Map<String, Object> estado = Map.of("id", 1L, "nombre", "Activo");
        Map<String, Object> usuario = Map.of("id", 1L, "idRol", 3, "nombre", "Usuario Test");   
        Map<String, Object> contratacion = Map.of("id", 1L, "nombre", "Contratación Test");

        when(estadoClient.getEstadoById(nuevoProyecto.getEstadoId())).thenReturn(estado);
        when(usuarioClient.getUsuarioById(nuevoProyecto.getUsuarioId())).thenReturn(usuario);
        when(contratacionClient.getContratacionById(nuevoProyecto.getContratacionId())).thenReturn(contratacion);


        when(proyectoRepository.save(nuevoProyecto)).thenReturn(nuevoProyecto);

        Proyecto result = proyectosService.saveProyecto(nuevoProyecto);

        assertThat(result).isSameAs(nuevoProyecto);
    }

    @Test
    void getProyectoByUsuarioId_returnsListOfProyectos() {
        Long usuarioId = 1L;
        List<Proyecto> listaProyectos = Arrays.asList(new Proyecto(
                1L,
                "Proyecto de prueba",
                usuarioId,
                1L,
                1L));

        when(proyectoRepository.findByIdUsuario(usuarioId)).thenReturn(listaProyectos);

        List<Proyecto> result = proyectosService.obtenerProByUsuarioo(usuarioId);

        assertThat(result).isSameAs(listaProyectos);
    }



}
