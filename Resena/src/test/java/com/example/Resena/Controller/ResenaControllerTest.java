package com.example.Resena.Controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

}
