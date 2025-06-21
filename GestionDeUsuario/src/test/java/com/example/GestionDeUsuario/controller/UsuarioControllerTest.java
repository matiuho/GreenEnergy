package com.example.GestionDeUsuario.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.GestionDeUsuario.model.Usuario;
import com.example.GestionDeUsuario.service.UsuarioService;
import com.example.GestionDeUsuario.webclient.RolClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    // se inyecta un mock de reservaservice
    @MockBean
    private UsuarioService usuarioService;
    // Crear un mock proporcionado por Spring
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RolClient rolClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllUsuarios_returnsOkAndJson() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setEmail("juan.perez@gmail.com");
        usuario.setPassword("pass123");
        usuario.setIdRol(null);

        List<Usuario> listaUsuarios = Arrays.asList(usuario);
        when(usuarioService.getUsuario()).thenReturn(listaUsuarios);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idUsuario").value(1L))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].apellido").value("Perez"))
                .andExpect(jsonPath("$[0].email").value("juan.perez@gmail.com"))
                .andExpect(jsonPath("$[0].password").value("pass123"))
                .andExpect(jsonPath("$[0].idRol").doesNotExist());
    }

    @Test
    void getUsuarioPorId_returnsOkAndJson() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setEmail("juan.perez@gmail.com");
        usuario.setPassword("pass123");
        usuario.setIdRol(null);

        when(usuarioService.getUsuarioPorId(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuarios/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Perez"))
                .andExpect(jsonPath("$.email").value("juan.perez@gmail.com"))
                .andExpect(jsonPath("$.password").value("pass123"))
                .andExpect(jsonPath("$.idRol").doesNotExist());
    }

    @Test
    void crearUsuario_returnsCreatedAndJson() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setEmail("juan.perez@gmail.com");
        usuario.setPassword("pass1234");
        usuario.setIdRol(1L);

        when(usuarioService.saveUsuario(any(Usuario.class))).thenReturn(usuario);
        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idUsuario").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellido").value("Perez"))
                .andExpect(jsonPath("$.email").value("juan.perez@gmail.com"))
                .andExpect(jsonPath("$.password").value("pass1234"))
                .andExpect(jsonPath("$.idRol").value(1L));
    }

    @Test
    void eliminarUsuario_returnsNoContent() throws Exception {
        Long id = 1L;
        doNothing().when(usuarioService).eliminarUsuario(id);

        mockMvc.perform(delete("/api/usuarios/{id}", id))
                .andExpect(status().isNoContent());
    }

}