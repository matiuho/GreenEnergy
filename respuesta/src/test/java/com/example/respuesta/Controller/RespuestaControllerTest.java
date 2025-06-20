package com.example.respuesta.Controller;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.respuesta.controller.RespuestaController;
import com.example.respuesta.model.Respuesta;
import com.example.respuesta.service.RespuestaService;
import com.example.respuesta.webclient.SoporteClient;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(RespuestaController.class)
public class RespuestaControllerTest {
    @MockBean
    private RespuestaService respuestaService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SoporteClient soporteClient;

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


}
