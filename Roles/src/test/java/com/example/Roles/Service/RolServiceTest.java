package com.example.Roles.Service;


import static org.mockito.Mockito.when;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Roles.model.Roles;
import com.example.Roles.repository.RolesRepository;
import com.example.Roles.service.RolesService;

@ExtendWith(MockitoExtension.class)
public class RolServiceTest {
    @Mock
    private RolesRepository rolesRepository;

    @InjectMocks
    private RolesService rolService;

    @Test
    void findAll_returnsListFromRepository() {

        List<Roles> mockRoles = List.of(
                new Roles(1L, "ADMIN"));
        // Simular el comportamiento del repositorio
        when(rolesRepository.findAll()).thenReturn(mockRoles);
        List<Roles> roles = rolService.obtenerRoles();

        assertThat(roles).isEqualTo(mockRoles);
    }
    @Test
    void getRolesPorId_returnsListFromRepository() {
        Roles mockRoles = new Roles(1L, "ADMIN");
        // Simular el comportamiento del repositorio
        when(rolesRepository.findById(1L)).thenReturn(java.util.Optional.of(mockRoles));
        Roles rol = rolService.getRolesPorId(1L);

        assertThat(rol).isEqualTo(mockRoles);

    }
}
