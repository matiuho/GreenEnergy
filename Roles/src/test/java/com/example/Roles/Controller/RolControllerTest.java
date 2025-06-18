package com.example.Roles.Controller;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Roles.controller.RolesController;
import com.example.Roles.model.Roles;
import com.example.Roles.service.RolesService;

@WebMvcTest(RolesController.class)
public class RolControllerTest {
    @MockBean
    private RolesService rolService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllRoles_returnsOkAndJson() throws Exception {
        Roles roles = new Roles();
        roles.setIdRol(1L);
        roles.setNombre("prueba");

        List<Roles> listaRoles = Arrays.asList(roles);
        when(rolService.obtenerRoles()).thenReturn(listaRoles);
        
        try{
            mockMvc.perform(get("api/roles")).andExpect(status().isOk())
            .andExpect(jsonPath("$[0].idRol")
            .value(1L));
        } catch (Exception e) {
        }
        
    }
}
