package com.example.Soporte.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
import com.example.Soporte.webclient.CategoriaClient;
import com.example.Soporte.webclient.EstadoClient;
import com.example.Soporte.webclient.UserClient;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SoporteController.class)
public class SoporteControllerTest {
    @MockBean
    private SoporteService soporteService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EstadoClient estadoClient;

    @MockBean
    private UserClient usuarioClient;

    @MockBean
    private CategoriaClient categoriaClient;

    @Test
    void getAllSoporte_returnsOKAndJson() throws Exception {
        // Crear una lista ficticia con un soporte
        Soporte soporte = new Soporte();
        soporte.setIdSoporte(1L);
        soporte.setDescripcion("Problema con el sistema");
        soporte.setIdEstado(null);
        soporte.setIdCategoria(null);
        soporte.setIdusuario(null);

        List<Soporte> listaSoporte = Arrays.asList(soporte);
        // Simular comportamiento del servicio
        when(soporteService.getSoporte()).thenReturn(listaSoporte);

        try {
            // Realizar la petición GET y verificar el estado y el contenido JSON
            mockMvc.perform(get("api/soporte"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idSoporte").value(1L));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}