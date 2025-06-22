package com.example.Soporte.Controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
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
import com.fasterxml.jackson.databind.ObjectMapper;

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
    private UserClient userClient;

    @MockBean
    private CategoriaClient categoriaClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllSoporte_returnsOKAndJson() throws Exception {
        // Crear una lista ficticia con un soporte
        Soporte soporte = new Soporte();
        soporte.setIdSoporte(1L);
        soporte.setFecha(LocalDate.now());
        soporte.setDescripcion("Problema con el sistema");
        soporte.setIdEstado(null);
        soporte.setIdCategoria(null);
        soporte.setIdusuario(null);

        List<Soporte> listaSoporte = Arrays.asList(soporte);

        // Simular comportamiento del servicio
        when(soporteService.getSoporte()).thenReturn(listaSoporte);

        // Realizar la petición GET y verificar el contenido
        mockMvc.perform(get("/api/soporte"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idSoporte").value(1L));
    }

    @Test
    void crearSoporte_returnsCreatedAndJson() throws Exception {
        LocalDate fecha = LocalDate.of(2025, 10, 10);

        Soporte soporte = new Soporte();
        soporte.setFecha(fecha);
        soporte.setDescripcion("Descripcion");
        soporte.setIdEstado(1L);
        soporte.setIdCategoria(1L);
        soporte.setIdusuario(1L);

        when(soporteService.saveSoporte(soporte)).thenReturn(soporte);

        mockMvc.perform(post("/api/soporte")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(soporte)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fecha").value(fecha.toString()))
                .andExpect(jsonPath("$.descripcion").value("Descripcion"))
                .andExpect(jsonPath("$.idEstado").value(1L))
                .andExpect(jsonPath("$.idCategoria").value(1L))
                .andExpect(jsonPath("$.idusuario").value(1L));
    }

    @Test
    void eliminarSoporte_returnsNoContent() throws Exception {
        Long id = 1L;
        doNothing().when(soporteService).eliminarPorId(id);

        mockMvc.perform(delete("/api/soporte/{id}", id))
                .andExpect(status().isNoContent());

        verify(soporteService).eliminarPorId(1L);
    }

    @Test
    void getSoporteById_returnsOKAndJson() throws Exception {
        Long id = 1L;
        LocalDate fecha = LocalDate.of(2025, 10, 10);

        Soporte soporte = new Soporte();
        soporte.setIdSoporte(id);
        soporte.setFecha(fecha);
        soporte.setDescripcion("Descripción de prueba");
        soporte.setIdEstado(1L);
        soporte.setIdCategoria(1L);
        soporte.setIdusuario(1L);

        when(soporteService.getSoportePorId(id)).thenReturn(soporte);

        mockMvc.perform(get("/api/soporte/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idSoporte").value(id))
                .andExpect(jsonPath("$.fecha").value(fecha.toString()))
                .andExpect(jsonPath("$.descripcion").value("Descripción de prueba"))
                .andExpect(jsonPath("$.idEstado").value(1L))
                .andExpect(jsonPath("$.idCategoria").value(1L))
                .andExpect(jsonPath("$.idusuario").value(1L));

        verify(soporteService).getSoportePorId(id);
    }

    @Test
    void getSoporteByUsuario_returnsOKAndJsonArray() throws Exception {
        Long idUsuario = 1L;

        Soporte soporte = new Soporte();
        soporte.setIdSoporte(1L);
        soporte.setFecha(LocalDate.of(2025, 10, 10));
        soporte.setDescripcion("Soporte del usuario");
        soporte.setIdEstado(1L);
        soporte.setIdCategoria(1L);
        soporte.setIdusuario(idUsuario);

        List<Soporte> soportes = List.of(soporte);

        when(soporteService.obtenerSoByUsuario(idUsuario)).thenReturn(soportes);

        mockMvc.perform(get("/api/soporte/usuario/{idUsuario}", idUsuario))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].idSoporte").value(1L))
                .andExpect(jsonPath("$[0].idusuario").value(idUsuario))
                .andExpect(jsonPath("$[0].descripcion").value("Soporte del usuario"));

        verify(soporteService).obtenerSoByUsuario(idUsuario);
    }
    

}