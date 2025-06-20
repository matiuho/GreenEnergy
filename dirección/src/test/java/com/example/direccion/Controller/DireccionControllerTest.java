package com.example.direccion.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.direccion.controller.DireccionController;
import com.example.direccion.model.Direccion;
import com.example.direccion.service.DireccionService;
import com.example.direccion.webclient.ClienteClient;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//cargar el controlador que se va a simular
@WebMvcTest(DireccionController.class)
public class DireccionControllerTest {
    @MockBean
    private DireccionService direccionService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteClient clienteClient;

    @Test
    void obtenerDirecciones_returnsOkAndJson() throws Exception {
        Direccion direccion = new Direccion();
        direccion.setIdDireccion(1L);
        direccion.setNombre("Prueba");
        direccion.setComuna(null);
        direccion.setIdUsuario(null);

        List<Direccion> listaDirecciones = Arrays.asList(direccion);

        when(direccionService.getDirecciones()).thenReturn(listaDirecciones);
        mockMvc.perform(get("/api/direccion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idDireccion").value(1L));
        
    }


}
