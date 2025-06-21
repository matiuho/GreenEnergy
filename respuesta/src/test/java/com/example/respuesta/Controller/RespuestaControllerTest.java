package com.example.respuesta.Controller;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cglib.core.Local;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.respuesta.controller.RespuestaController;
import com.example.respuesta.model.Respuesta;
import com.example.respuesta.service.RespuestaService;
import com.example.respuesta.webclient.SoporteClient;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(RespuestaController.class)
public class RespuestaControllerTest {
    @MockBean
    private RespuestaService respuestaService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SoporteClient soporteClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllRespuesta_returnsOkAndJson() throws Exception {
        Respuesta respuesta = new Respuesta();
        respuesta.setIdrespuesta(1L);
        respuesta.setFechaSoporte(LocalDate.now());
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
            "Usuario",
            1L);

        when(respuestaService.saveRespuesta(respuesta)).thenReturn(respuesta);

        mockMvc.perform(post("/api/respuesta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(respuesta)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idrespuesta").value(1L));
    }

    @Test
    void getAllRespuestaById_returnsOkAndJson() throws Exception {
        Respuesta respuesta = new Respuesta();
        respuesta.setIdrespuesta(1L);
        respuesta.setFechaSoporte(LocalDate.now());
        respuesta.setComentario("Comentario de prueba");
        respuesta.setTipousuario("Usuario");
        respuesta.setIdsoporte(null);

        when(respuestaService.getRespuestaPorId(1L)).thenReturn(respuesta);


        mockMvc.perform(get("/api/respuesta/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idrespuesta").value(1L));
    }

}
