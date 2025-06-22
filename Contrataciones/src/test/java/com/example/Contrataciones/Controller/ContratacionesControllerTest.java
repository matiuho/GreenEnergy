package com.example.Contrataciones.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import com.example.Contrataciones.controller.ContratacionesController;
import com.example.Contrataciones.model.Contrataciones;
import com.example.Contrataciones.service.ContratacionesService;
import com.example.Contrataciones.webclient.ClienteClient;
import com.example.Contrataciones.webclient.DireccionClient;
import com.example.Contrataciones.webclient.UsuarioClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ContratacionesController.class)
public class ContratacionesControllerTest {

    @MockBean
    private ContratacionesService contratacionesService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClienteClient clienteClient;
    @MockBean
    private DireccionClient direccionClient;
    @MockBean
    private UsuarioClient usuarioClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllContrataciones_returnsOkAndJson() throws Exception {
        // crear una reserva ficticia para la respuesta del servicio
        List<Contrataciones> listaContrataciones = Arrays.asList(new Contrataciones(
                1L,
                LocalDate.of(2025, 10, 10),
                LocalDate.of(2025, 10, 27),
                LocalDate.of(2026, 10, 11),
                null, null, null));

        // identificar el comportamiento del servicio
        when(contratacionesService.getContrataciones()).thenReturn(listaContrataciones);
        // Ejecutar la funcion del controlador
        // Ejecutar el metodo GET (endpoint)
        // verficar que la respuesta sea 200 OK
        // validar que el archivo json contenga los id

        mockMvc.perform(get("/api/contrataciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idContratacion").value(1L));

    }

    @Test
    void crearContratacion_returnsCreatedAndJson() throws Exception {
        LocalDate fecha = LocalDate.of(2025, 6, 20);

        Contrataciones contrataciones = new Contrataciones();
        contrataciones.setFechaContratacion(fecha);
        contrataciones.setFechaInicio(fecha);
        contrataciones.setFechaFin(fecha);
        contrataciones.setIdServicio(1L);
        contrataciones.setIdDireccion(1L);
        contrataciones.setIdusuario(1L);

        when(contratacionesService.saveContrataciones(contrataciones)).thenReturn(contrataciones);

        mockMvc.perform(post("/api/contrataciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contrataciones)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fechaContratacion").value(fecha.toString()))
                .andExpect(jsonPath("$.fechaInicio").value(fecha.toString()))
                .andExpect(jsonPath("$.fechaFin").value(fecha.toString()))
                .andExpect(jsonPath("$.idServicio").value(1L))
                .andExpect(jsonPath("$.idDireccion").value(1L))
                .andExpect(jsonPath("$.idusuario").value(1L));
    }

    @Test
    void getContratacionesPorId_returnsOkAndJson() {
        Contrataciones contrataciones = new Contrataciones();
        contrataciones.setIdContratacion(1L);
        contrataciones.setFechaContratacion(LocalDate.of(2025, 10, 10));
        contrataciones.setFechaInicio(LocalDate.of(2025, 10, 27));
        contrataciones.setFechaFin(LocalDate.of(2026, 10, 11));
        contrataciones.setIdServicio(1L);
        contrataciones.setIdDireccion(1L);
        contrataciones.setIdusuario(1L);

        when(contratacionesService.getContrtacionPorId(1L)).thenReturn(contrataciones);
        try {
            mockMvc.perform(get("/api/contrataciones/{id}", 1L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.idContratacion").value(1L));
        } catch (Exception e) {

        }
    }

    @Test
    void getContratacionesByUsuario_returnsOkAndJson() {
        List<Contrataciones> contrataciones = Arrays.asList(new Contrataciones(
                1L,
                LocalDate.of(2025, 10, 10),
                LocalDate.of(2025, 10, 27),
                LocalDate.of(2026, 10, 11),
                null, null, 1L));

        when(contratacionesService.obtenerContratacionByUsuario(1L)).thenReturn(contrataciones);
        try {
            mockMvc.perform(get("/api/contrataciones/usuario/{idusuario}", 1L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idusuario").value(1L));
        } catch (Exception e) {
        }
    }

}
