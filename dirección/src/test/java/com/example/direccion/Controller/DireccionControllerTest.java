package com.example.direccion.Controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.direccion.controller.DireccionController;
import com.example.direccion.model.Comuna;
import com.example.direccion.model.Direccion;
import com.example.direccion.service.DireccionService;
import com.example.direccion.webclient.ClienteClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//cargar el controlador que se va a simular
@WebMvcTest(DireccionController.class)
public class DireccionControllerTest {

    @MockBean
    private DireccionService direccionService;

    @MockBean
    private ClienteClient clienteClient;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    void crearDireccion_returnsCreatedAndJson() throws Exception {
        Direccion direccion = new Direccion();
        direccion.setNombre("Parral 2406");
        direccion.setComuna(new Comuna(1L, "Santiago", null));
        direccion.setIdUsuario(1L);

        when(direccionService.saveDireccion(any(Direccion.class))).thenReturn(direccion);
        mockMvc.perform(post("/api/direccion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(direccion)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Parral 2406"))
                .andExpect(jsonPath("$.comuna.idComuna").value(1L))
                .andExpect(jsonPath("$.idUsuario").value(1L));

    }

    @Test
    void obtenerDireccionPorId_returnsOkAndJson() throws Exception {
        Direccion direccion = new Direccion(1L, "Parral 2406", new Comuna(1L, "Santiago", null), 1L);

        when(direccionService.obtenerDireccionPorId(1L)).thenReturn(direccion);
        mockMvc.perform(get("/api/direccion/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDireccion").value(1L))
                .andExpect(jsonPath("$.nombre").value("Parral 2406"))
                .andExpect(jsonPath("$.comuna.idComuna").value(1L))
                .andExpect(jsonPath("$.idUsuario").value(1L));
    }

    @Test
    void eliminarDireccion_returnsNoContent() throws Exception {
        Long id = 1L;

        doNothing().when(direccionService).eliminarDireccion(id);

        mockMvc.perform(delete("/api/direccion/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void obtenerDireccionByUsuario_returnsOkAndJson() {
        List<Direccion> listaDirecciones = Arrays
                .asList(new Direccion(1L, "Parral 2406", new Comuna(1L, "Santiago", null), 1L));

        when(direccionService.obtenerDireccionesPorUsuario(1L)).thenReturn(listaDirecciones);
        try {
            mockMvc.perform(get("/api/direccion/usuario/{idUsuario}", 1L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idDireccion").value(1L));
        } catch (Exception e) {
        }
    }

    @Test
    void actualizarDireccion_returnsOkAndJson() throws Exception {
        Direccion direccion = new Direccion(1L,
                "Nueva Direccion",
                new Comuna(1L, "Nueva Comuna", null),
                1L);

        Direccion nuevaDireccion = new Direccion(1L,
                "Actualizada Direccion",
                new Comuna(2L, "Comuna Actualizada", null),
                1L);

        when(direccionService.obtenerDireccionPorId(1L)).thenReturn(direccion);
        when(direccionService.saveDireccion(direccion)).thenReturn(nuevaDireccion);

        mockMvc.perform(put("/api/direccion/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(direccion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDireccion").value(1L))
                .andExpect(jsonPath("$.nombre").value("Actualizada Direccion"))
                .andExpect(jsonPath("$.comuna.idComuna").value(2L));
    }
}