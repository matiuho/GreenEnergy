package com.example.GestionDeUsuario.service;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.GestionDeUsuario.model.Usuario;
import com.example.GestionDeUsuario.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class) //habilitar la inicialización automatica de los mocks

public class UsuarioServiceTest {
    @Mock 
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Test
    void findAll_returnsListFromRepository() {
        // crear un elemento que simule la respuesta del repositorio

    List<Usuario> mockUsuarios = List.of(
        new Usuario (1L, "Juan", "Perez", "@gmail.com", "password123", 1L),
        new Usuario (2L, "Maria", "Lopez", "@gmail.com", "password456", 2L)
    );

        // simular el comportamiento del repositorio
        when(repository.findAll()).thenReturn(mockUsuarios);

        // llamar al método del servicio
        List<Usuario> usuarios = service.getUsuario();

        // verificar que la lista devuelta es la misma que la del mock
        assertEquals(mockUsuarios, usuarios);
    }
}
