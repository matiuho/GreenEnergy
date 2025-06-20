package com.example.Proyecto.Controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Proyecto.Model.Proyecto;
import com.example.Proyecto.Service.ProyectosService;
import com.example.Proyecto.WebClient.ContratacionClient;
import com.example.Proyecto.WebClient.EstadoClient;
import com.example.Proyecto.WebClient.UsuarioClient;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

}
