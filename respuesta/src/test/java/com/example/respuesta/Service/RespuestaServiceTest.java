package com.example.respuesta.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.respuesta.model.Respuesta;
import com.example.respuesta.repository.RespuestaRepository;
import com.example.respuesta.service.RespuestaService;
import com.example.respuesta.webclient.SoporteClient;

@ExtendWith(MockitoExtension.class)
public class RespuestaServiceTest {
    // simulo el repositorio de la respuesta
    @Mock
    private RespuestaRepository respuestaRepository;

    @Mock
    private SoporteClient soporteClient;

    @InjectMocks
    private RespuestaService respuestaService;

    @Test
    void findAll_returnsListFromRepository() {
        // crear un elemento que simule la respuesta del repositorio
        List<Respuesta> listaRespuesta = Arrays.asList(new Respuesta(
                1L,
                LocalDate.now(),
                "Comentario de Pueba",
                "Usuario",
                1L));

        // definir el comportamiento del mock (repositorio)
        when(respuestaRepository.findAll()).thenReturn(listaRespuesta);

        // ejecutar el metodo a probar
        List<Respuesta> result = respuestaService.obtenerRespuestas();
        // verificar el resultado (criterios de aceptacion)
        assertThat(result).isEqualTo(listaRespuesta);
    }

    @Test
    void save_returnsSavedRespuesta() {
        // crear un elemento que simule la respuesta del repositorio
        Respuesta respuesta = new Respuesta(
                1L,
                LocalDate.now(),
                "Comentario de Pueba",
                "Usuario",
                1L);

        // Simular las respuestas de los microservicios
        Map<String, Object> soporte = Map.of("idsoporte", 1L);

        when(soporteClient.getSoporteById(1L)).thenReturn(soporte);

        // simular el repositorio
        when(respuestaRepository.save(respuesta)).thenReturn(respuesta);

        // ejecutar el metodo a probar
        Respuesta result = respuestaService.saveRespuesta(respuesta);

        // verificar el resultado (criterios de aceptacion)
        assertThat(result).isEqualTo(respuesta);
    }

    @Test
    void findById_returnsRespuestaById() {
        // crear un elemento que simule la respuesta del repositorio
        Respuesta respuesta = new Respuesta(
                1L,
                LocalDate.now(),
                "Comentario de Pueba",
                "Usuario",
                1L);

        when(respuestaRepository.findById(1L)).thenReturn(java.util.Optional.of(respuesta));

        Respuesta result = respuestaService.getRespuestaPorId(1L);

        // verificar el resultado (criterios de aceptacion)
        assertThat(result).isEqualTo(respuesta);
    }

    @Test
    void deleteRespuesta_deleteRespuesta() {
        Long id = 1L;

        // Act
        respuestaService.eliminarrespuesta(id);

        // Assert
        verify(respuestaRepository).deleteById(id);
    }

    @Test
    void updateRespuesta_actualizaYDevuelveRespuesta() {
        Long id = 1L;

        Respuesta existente = new Respuesta(
                id,
                LocalDate.of(2025, 12, 1),
                "Comentario antiguo",
                "UsuarioAntiguo",
                1L);

        Respuesta modificacion = new Respuesta(
                id,
                LocalDate.of(2025, 6, 22),
                "Comentario nuevo",
                "UsuarioNuevo",
                2L);

        when(respuestaRepository.findById(id)).thenReturn(Optional.of(existente));
        when(respuestaRepository.save(any(Respuesta.class))).thenReturn(modificacion); 
        
        
        Respuesta resultado = respuestaService.actualizar(id, modificacion);


        assertThat(resultado.getFechaRespuesta()).isEqualTo(LocalDate.of(2025, 6, 22));
        assertThat(resultado.getComentario()).isEqualTo("Comentario nuevo");
        assertThat(resultado.getTipousuario()).isEqualTo("UsuarioNuevo");
        assertThat(resultado.getIdsoporte()).isEqualTo(2L);
    }

    @Test 
    void findByIdSoporte_returnsRespuestaList(){
        Long idsoporte=1L;
        Respuesta respuesta1= new Respuesta(
                1L,
                LocalDate.of(2025, 6, 22),
                "Comentario nuevo",
                "UsuarioNuevo",
                idsoporte);
         Respuesta respuesta2 = new Respuesta(
                2L,
                LocalDate.of(2025, 6, 22),
                "Comentario",
                "RESPUESTAS NNUEVAS ",
                idsoporte);

        List<Respuesta> respuestas = Arrays.asList(respuesta1,respuesta2);

        when(respuestaRepository.findBySoporte(idsoporte)).thenReturn(respuestas);
        List<Respuesta> result = respuestaService.obtenerReBySoporte(idsoporte);

         // Verificar que el resultado es el esperado
        assertThat(result).containsExactlyInAnyOrder(respuesta1,respuesta2);
    }
}
