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
        // crear una reserva ficticia para la respuesta del servicio
        
        List<Comuna> ListaComuna = Arrays.asList(new Comuna(1L, "Comuna de prueba", new Region(1, "Región de prueba")));

        // identificar el comportamiento del servicio
        when(comunaService.getComunas()).thenReturn(ListaComuna);

        // Ejecutar la funcion del controlador
        // Ejecutar el metodo GET (endpoint)
        // verficar que la respuesta sea 200 OK
        // validar que el archivo json contenga los id
        try {
            mockMvc.perform(get("api/comuna"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idComuna")
                            .value(1L));
        } catch (Exception e) {

        }

    }

}
