package com.example.servicio.Controller;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.servicio.controller.ServicioController;
import com.example.servicio.model.Servicio;
import com.example.servicio.service.ServicioService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ServicioController.class)
public class ServicioControllerTest {
    @MockBean
    private ServicioService servicioService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllServicios_returnsOKAndJson() throws Exception {
        Servicio servicio = new Servicio();
        servicio.setIdServicio(1L);
        servicio.setNombre("Servicio de Prueba");
        servicio.setDescripcion("Descripción del servicio de prueba");
        servicio.setPrecio(0);
        servicio.setDisponibilidad("Disponible");

        List<Servicio> listaServicios = Arrays.asList(servicio);

        when(servicioService.obtenerTodos()).thenReturn(listaServicios);

        mockMvc.perform(get("/api/servicios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idServicio").value(1L));
    }

}
