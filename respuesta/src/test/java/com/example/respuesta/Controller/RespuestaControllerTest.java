package com.example.respuesta.Controller;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.respuesta.controller.RespuestaController;
import com.example.respuesta.model.Respuesta;
import com.example.respuesta.service.RespuestaService;
import com.example.respuesta.webclient.SoporteClient;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(RespuestaController.class)
public class RespuestaControllerTest {
    @MockBean
    private RespuestaService respuestaService;
    @MockBean
    private SoporteClient soporteClient;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllRespuesta_returnsOkAndJson() throws Exception {
        Respuesta respuesta = new Respuesta();
        respuesta.setIdrespuesta(1L);
        respuesta.setFechaRespuesta(LocalDate.now());
        respuesta.setComentario("Comentario de prueba");
        respuesta.setTipousuario("Usuario");
        respuesta.setIdsoporte(null);

        List<Respuesta> listaRespuestas = Arrays.asList(respuesta);

        when(respuestaService.obtenerRespuestas()).thenReturn(listaRespuestas);

        mockMvc.perform(get("/api/respuesta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idrespuesta").value(1L));
    }

    @Test
    void crearRespuesta_returnsCreatedAndJson() throws Exception {
        Respuesta respuesta = new Respuesta(
                1L,
                LocalDate.now(),
                "Comentario de prueba",
                "Cliente",
                1L);

        when(respuestaService.saveRespuesta(respuesta)).thenReturn(respuesta);

        mockMvc.perform(post("/api/respuesta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(respuesta)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idrespuesta").value(1L))
                .andExpect(jsonPath("$.comentario").value("Comentario de prueba"))
                .andExpect(jsonPath("$.tipousuario").value("Cliente"));
    }

    @Test
    void getAllRespuestaById_returnsOkAndJson() throws Exception {
        Respuesta respuesta = new Respuesta();
        respuesta.setIdrespuesta(1L);
        respuesta.setFechaRespuesta(LocalDate.now());
        respuesta.setComentario("Comentario de prueba");
        respuesta.setTipousuario("Cliente");
        respuesta.setIdsoporte(null);

        when(respuestaService.getRespuestaPorId(1L)).thenReturn(respuesta);

        mockMvc.perform(get("/api/respuesta/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idrespuesta").value(1L));
    }

    @Test
    void deleteRespuesta_returnsNoContent() throws Exception {
        Long idRespuesta = 1L;
        doNothing().when(respuestaService).eliminarrespuesta(idRespuesta);
        mockMvc.perform(delete("/api/respuesta/{id}", idRespuesta))
                .andExpect(status().isNoContent());
    }

    @Test
    void actualizarRespuesta_returnsOkAndJson() throws Exception {
        Respuesta respuesta = new Respuesta(
                1L,
                LocalDate.now(),
                "Comentario actualizado",
                "Cliente",
                1L);

        when(respuestaService.actualizar(1L, respuesta)).thenReturn(respuesta);

        mockMvc.perform(put("/api/respuesta/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(respuesta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idrespuesta").value(1L))
                .andExpect(jsonPath("$.comentario").value("Comentario actualizado"))
                .andExpect(jsonPath("$.tipousuario").value("Cliente"));
    }

    @Test
    void getRespuestaBySoporte_returnsOkAndJson() throws Exception{
        Respuesta respuesta = new Respuesta();
        respuesta.setIdrespuesta(1L);
        respuesta.setFechaRespuesta(LocalDate.now());
        respuesta.setComentario("Nueva Respuesta");
        respuesta.setTipousuario("Cliente");
        respuesta.setIdsoporte(null);

        List<Respuesta> listaRespuestas = Arrays.asList(respuesta);

        when(respuestaService.obtenerReBySoporte(1L)).thenReturn(listaRespuestas);

         mockMvc.perform(get("/api/respuesta/soporte/{idsoporte}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idrespuesta").value(1L)) 
                .andExpect(jsonPath("$[0].comentario").value("Nueva Respuesta"))
                .andExpect(jsonPath("$[0].tipousuario").value("Cliente"))
                .andExpect(jsonPath("$[0].idsoporte").value(1L));
    }

}

