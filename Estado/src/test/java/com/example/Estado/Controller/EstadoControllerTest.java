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
    void getAllEstados_returnsOkAndJson() {
        List<Estado> ListaEstados = Arrays.asList(new Estado(1L,"Activo"));

        when(estadoService.getEstados()).thenReturn(ListaEstados);

        try {
            mockMvc.perform(get("api/estados"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idEstado").value(1L));
        } catch (Exception e) {
            e.printStackTrace();

    }
        
    }
}
