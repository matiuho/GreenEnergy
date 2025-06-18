package com.example.Soporte.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
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
    void getAllSoportes_returnsOkAndJson() throws Exception {
        Soporte soporte = new Soporte();
        soporte.setIdSoporte(1L);
        soporte.setFecha(LocalDate.parse("2025-06-20")); 
        soporte.setDescripcion("Prueba");
        soporte.setIdEstado(null);
        soporte.setIdCategoria(null);
        soporte.setIdusuario(null);

        when(soporteService.getSoporte()).thenReturn(List.of(soporte));

        mockMvc.perform(get("/api/soportes"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idSoporte").value(1L))
            .andExpect(jsonPath("$[0].descripcion").value("Prueba"))
            .andExpect(jsonPath("$[0].fecha").value("2025-06-20")); // Este es el valor que espera el JSON
    }
}