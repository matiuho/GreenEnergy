package com.example.Contrataciones.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.Contrataciones.controller.ContratacionesController;
import com.example.Contrataciones.model.Contrataciones;
import com.example.Contrataciones.service.ContratacionesService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ContratacionesController.class)
public class ContratacionesControllerTest {

    @MockBean
    private ContratacionesService contratacionesService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllContrataciones_returnsOkAndJson(){
        //crear una reserva ficticia para la respuesta del servicio
        List<Contrataciones> listaContrataciones = Arrays.asList(new Contrataciones(
                            1L,
                            LocalDate.of(2025, 10, 10),
                            LocalDate.of(2025, 10, 27),
                            LocalDate.of(2026, 10, 11),
                            null, null, null));

        //identificar el comportamiento del servicio
        when(contratacionesService.getContrataciones()).thenReturn(listaContrataciones);
        //Ejecutar la funcion del controlador
        //Ejecutar el metodo GET (endpoint)
        //verficar que la respuesta sea 200 OK
        //validar que el archivo json contenga los id
        try {
            mockMvc.perform(get("api/contrataciones"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idContratacion").value(1L));
        } catch (Exception e) {
        }

    }

}
