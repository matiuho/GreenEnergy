package com.example.Proyecto.Controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Proyecto.Model.Proyecto;
import com.example.Proyecto.Service.ProyectosService;
import com.example.Proyecto.WebClient.ContratacionClient;
import com.example.Proyecto.WebClient.EstadoClient;
import com.example.Proyecto.WebClient.UsuarioClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(ProyectoController.class)
public class ProyectoControllerTest {
    @MockBean
    private ProyectosService proyectosService;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ContratacionClient contratacionClient;
    @MockBean
    private EstadoClient estadoClient;
    @MockBean
    private UsuarioClient usuarioClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void obtenerProyectos_returnsOkAndJson() throws Exception {
        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(1L);
        proyecto.setComentario("Todo correcto");
        proyecto.setEstadoId(null);
        proyecto.setUsuarioId(null);
        proyecto.setContratacionId(null);

        List<Proyecto> listaProyectos = Arrays.asList(proyecto);

        when(proyectosService.getProyectos()).thenReturn(listaProyectos);
        mockMvc.perform(get("/api/proyecto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idProyecto").value(1L));

    }

    @Test
    void obtenerProyectoPorId_returnsOkAndJson() throws Exception {
        Proyecto proyecto = new Proyecto(1L, "prueba", 1L, 1L, 1L);

        when(proyectosService.getProyectoPorId(1L)).thenReturn(proyecto);
        mockMvc.perform(get("/api/proyecto/{id}", 1L))
                .andExpect(jsonPath("$.idProyecto").value(1L))
                .andExpect(jsonPath("$.comentario").value("prueba"))
                .andExpect(jsonPath("$.estadoId").value(1L))
                .andExpect(jsonPath("$.usuarioId").value(1L))
                .andExpect(jsonPath("$.contratacionId").value(1L));

    }

    @Test
    void getProyectoByUsuario_returnsOkAndJson() throws Exception {
        Proyecto proyecto = new Proyecto(1L, "Avance correcto", 1L, 1L, 1L);
        List<Proyecto> proyectos = Arrays.asList(proyecto);

        when(proyectosService.obtenerProByUsuarioo(1L)).thenReturn(proyectos);

        mockMvc.perform(get("/api/proyecto/usuario/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idProyecto").value(1L))
                .andExpect(jsonPath("$[0].comentario").value("Avance correcto"))
                .andExpect(jsonPath("$[0].estadoId").value(1L))
                .andExpect(jsonPath("$[0].usuarioId").value(1L))
                .andExpect(jsonPath("$[0].contratacionId").value(1L));
    }

    @Test
    void crearProyecto_returnsCreatedAndJson() throws Exception {
        Proyecto proyecto = new Proyecto(1L, "Proyecto nuevo", 1L, 1L, 1L);

        when(proyectosService.saveProyecto(any(Proyecto.class))).thenReturn(proyecto);

        mockMvc.perform(post("/api/proyecto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(proyecto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idProyecto").value(1L))
                .andExpect(jsonPath("$.comentario").value("Proyecto nuevo"))
                .andExpect(jsonPath("$.estadoId").value(1L))
                .andExpect(jsonPath("$.usuarioId").value(1L))
                .andExpect(jsonPath("$.contratacionId").value(1L));
    }

}
