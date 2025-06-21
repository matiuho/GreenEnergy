package com.example.Resena.Controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Resena.Model.Resena;
import com.example.Resena.Service.ResenaService;
import com.example.Resena.WebClient.ServicioClient;
import com.example.Resena.WebClient.UserClient;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;

@WebMvcTest(ResenaController.class)
public class ResenaControllerTest {
    @MockBean
    private ResenaService resenaService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserClient userClient;
    @MockBean
    private ServicioClient servicioClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllResena_returnsOkAndJson() throws Exception {
        Resena resena = new Resena();
        resena.setIdResena(1L);
        resena.setFechaComentario(LocalDate.now());
        resena.setComentario("Comentario de prueba");
        resena.setIdUsuario(null);
        resena.setIdServicio(null);

        List<Resena> listaResenas = Arrays.asList(resena);

        when(resenaService.getResenas()).thenReturn(listaResenas);

        mockMvc.perform(get("/api/resena"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idResena").value(1L));
    }

    @Test
    void crearResena_returnsCreatedAndJSON() throws Exception{

        LocalDate fecha = LocalDate.of(2025, 6, 20);

        Resena resena = new Resena();
        resena.setFechaComentario(fecha);
        resena.setComentario("Comentario de prueba");
        resena.setIdUsuario(1L);
        resena.setIdServicio(1L);

        when(resenaService.savResena(resena)).thenReturn(resena);

        mockMvc.perform(post("/api/resena")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(resena)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fechaComentario").value(fecha.toString()))
                .andExpect(jsonPath("$.comentario").value("Comentario de prueba"))
                .andExpect(jsonPath("$.idUsuario").value(1L))
                .andExpect(jsonPath("$.idServicio").value(1L));
    }

    @Test
    void obtenerResenaPorId_returnsOkAndJson() throws Exception {
        Resena resena = new Resena();
        resena.setIdResena(1L);
        resena.setFechaComentario(LocalDate.of(2025, 10, 10));
        resena.setComentario("Comentario de prueba");
        resena.setIdUsuario(null);
        resena.setIdServicio(null);

        when(resenaService.getResenaPorId(1L)).thenReturn(resena);

        mockMvc.perform(get("/api/resena/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idResena").value(1L));
    }

    @Test
    void getResenaByUsuario_returnsOkAndJson() throws Exception {
        Resena resena = new Resena();
        resena.setIdResena(1L);
        resena.setFechaComentario(LocalDate.of(2025, 10, 10));
        resena.setComentario("Comentario de prueba");
        resena.setIdUsuario(1L);
        resena.setIdServicio(null);

        List<Resena> listaResenas = Arrays.asList(resena);

        when(resenaService.obtenerReByUsuario(1L)).thenReturn(listaResenas);

        mockMvc.perform(get("/api/resena/usuario/{idUsuario}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idResena").value(1L));
    }

}
