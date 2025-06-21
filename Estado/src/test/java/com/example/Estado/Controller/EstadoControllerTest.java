package com.example.Estado.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.example.Estado.controller.EstadoController;
import com.example.Estado.model.Estado;
import com.example.Estado.service.EstadoService;

@WebMvcTest(EstadoController.class)
public class EstadoControllerTest {

    @MockBean
    private EstadoService estadoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllEstados_returnsOkAndJson() throws Exception {
        List<Estado> listaEstados = Arrays.asList(new Estado(1L, "Activo"));

        when(estadoService.getEstados()).thenReturn(listaEstados);

        mockMvc.perform(get("/api/estado"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void getEstadoPorId_returnsOkAndJson() throws Exception {
        Estado estado = new Estado();
        estado.setId(1L);
        estado.setNombre("Activo");

        when(estadoService.getEstadoPorId(1L)).thenReturn(estado);

        mockMvc.perform(get("/api/estado/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Activo"));
    }
}
