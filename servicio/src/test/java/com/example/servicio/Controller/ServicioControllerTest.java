package com.example.servicio.Controller;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    void getServicioById_returnsOKAndJson() throws Exception {
        Servicio servicio = new Servicio();
        servicio.setIdServicio(1L);
        servicio.setNombre("Servicio de Prueba");
        servicio.setDescripcion("Descripción del servicio de prueba");
        servicio.setPrecio(1000);
        servicio.setDisponibilidad("Disponible");

        when(servicioService.getServicioPorId(1L)).thenReturn(servicio);

        mockMvc.perform(get("/api/servicios/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idServicio").value(1L))
                .andExpect(jsonPath("$.nombre").value("Servicio de Prueba"))
                .andExpect(jsonPath("$.descripcion").value("Descripción del servicio de prueba"))
                .andExpect(jsonPath("$.precio").value(1000))
                .andExpect(jsonPath("$.disponibilidad").value("Disponible"));

    }

    @Test
    void crearServicio_returnsCreatedAndJson() throws Exception {
        Servicio servicio = new Servicio();
        servicio.setNombre("Nuevo Servicio");
        servicio.setDescripcion("Descripción del nuevo servicio");
        servicio.setPrecio(1500);
        servicio.setDisponibilidad("Disponible");

        when(servicioService.saveServicio(servicio)).thenReturn(servicio);

        mockMvc.perform(post("/api/servicios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicio)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Nuevo Servicio"))
                .andExpect(jsonPath("$.descripcion").value("Descripción del nuevo servicio"))
                .andExpect(jsonPath("$.precio").value(1500))
                .andExpect(jsonPath("$.disponibilidad").value("Disponible"));
    }

    @Test
    void eliminarServicio_returnsNoContent() throws Exception {
        Long id = 1L;
        doNothing().when(servicioService).eliminarservicio(id);
        mockMvc.perform(delete("/api/servicios/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void actualizarServicio_devuelve200YJsonActualizado() throws Exception {
        Servicio servicio = new Servicio(
                1L,
                "nombreActualizado",
                "descripcion actualizada",
                50000,
                "Disponible");

        when(servicioService.getServicioPorId(1L)).thenReturn(servicio);
        when(servicioService.saveServicio(any(Servicio.class))).thenReturn(servicio);

        mockMvc.perform(put("/api/servicios/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(servicio)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("nombreActualizado"))
                .andExpect(jsonPath("$.descripcion").value("descripcion actualizada"))
                .andExpect(jsonPath("$.precio").value(50000))
                .andExpect(jsonPath("$.disponibilidad").value("Disponible"));
    }

}
