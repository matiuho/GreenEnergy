package com.example.direccion.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.direccion.controller.ComunaController;
import com.example.direccion.model.Comuna;
import com.example.direccion.model.Region;
import com.example.direccion.service.ComunaService;

@WebMvcTest(ComunaController.class)
public class ComunaControllerTest {

    @MockBean
    private ComunaService comunaService;

    // crear un mock proporcionado por spring
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllComunas_returnsOkAndJson() {
        
        List<Comuna> ListaComuna = Arrays.asList(new Comuna(1L, "Comuna de prueba", new Region(1, "Región de prueba")));

        when(comunaService.getComunas()).thenReturn(ListaComuna);

        try {
            mockMvc.perform(get("/api/comuna"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idComuna")
                            .value(1L));
        } catch (Exception e) {

        }
        

    }
    @Test
    void getComunaById_returnsOkAndJson() {
        Comuna mockComuna = new Comuna(1L, "Comuna de prueba", new Region(1, "Región de prueba"));

        when(comunaService.obtenerComunaPorId(1L)).thenReturn(mockComuna);

        try {
            mockMvc.perform(get("/api/comuna/{id}",1L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.idComuna").value(1L))
                    .andExpect(jsonPath("$.nombre").value("Comuna de prueba"));
        } catch (Exception e) {

        }
        
    }

}
