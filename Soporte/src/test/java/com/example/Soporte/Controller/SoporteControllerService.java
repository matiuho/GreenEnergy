package com.example.Soporte.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Soporte.controller.SoporteController;
import com.example.Soporte.model.Soporte;
import com.example.Soporte.service.SoporteService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SoporteController.class)
public class SoporteControllerService {
    @MockBean
    private SoporteService soporteService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllSoporte_returnsOKAndJson() throws Exception {
        // Crear una lista ficticia con un soporte
        List<Soporte> listaSoporte = Arrays.asList(
                new Soporte(1L,
                        LocalDate.of(2025, 10, 10),
                        "Problema con el sistema",
                        2L, 3L, 4L));

        // Simular comportamiento del servicio
        when(soporteService.getSoporte()).thenReturn(listaSoporte);

        // Ejecutar GET y verificar respuesta
        mockMvc.perform(get("/api/soporte"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idSoporte").value(1L))
                .andExpect(jsonPath("$[0].descripcion").value("Problema con el sistema"));
    }

}