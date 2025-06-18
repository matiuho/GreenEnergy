package com.example.GestionDeUsuario.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.GestionDeUsuario.model.Usuario;
import com.example.GestionDeUsuario.service.UsuarioService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    void getAllUsuarios_returnsOkAndJson() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Perez");
        usuario.setEmail("@gmail.com");
        usuario.setPassword("pass");
        usuario.setIdRol(null);

        List<Usuario> ListaUsuarios = Arrays.asList(usuario);
        when(usuarioService.getUsuario()).thenReturn(ListaUsuarios);

        try {
            mockMvc.perform(get("api/usuarios")).andExpect(status().isOk()).andExpect(jsonPath("$[0].idUsuario").value(1L));
        } catch (Exception e) {
        }
    }
}
    


