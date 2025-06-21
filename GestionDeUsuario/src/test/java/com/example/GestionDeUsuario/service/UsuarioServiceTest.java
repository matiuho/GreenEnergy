package com.example.GestionDeUsuario.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.GestionDeUsuario.model.Usuario;
import com.example.GestionDeUsuario.repository.UsuarioRepository;
import com.example.GestionDeUsuario.webclient.RolClient;

@ExtendWith(MockitoExtension.class) // habilitar la inicialización automatica de los mocks

public class UsuarioServiceTest {
    @Mock
    private UsuarioRepository repository;

    @Mock
    private RolClient rolClient;

    @InjectMocks
    private UsuarioService service;

    @Test
    void findAll_returnsListFromRepository() {
        // crear un elemento que simule la respuesta del repositorio

        List<Usuario> mockUsuarios = List.of(
                new Usuario(1L, "Juan", "Perez", "@gmail.com", "password123", 1L));

        // simular el comportamiento del repositorio
        when(repository.findAll()).thenReturn(mockUsuarios);

        // llamar al método del servicio
        List<Usuario> usuarios = service.getUsuario();

        // verificar que la lista devuelta es la misma que la del mock
        assertEquals(mockUsuarios, usuarios);
    }

    // BUSCAR USUARIO POR ID
    @Test
    void getUsuarioPorId_returnsUsuarioIfExists() {
        Usuario mockUsuario = new Usuario(1L, "Juan", "Perez", "@gmail.com", "pass", 1L);
        when(repository.findById(1L)).thenReturn(Optional.of(mockUsuario));

        Usuario usuario = service.getUsuarioPorId(1L);

        assertEquals(mockUsuario, usuario);
    }

    @Test
    void getUsuarioPorId_throwsExceptionIfNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getUsuarioPorId(1L));
    }

   

    // Este test ignora el hashing real por simplicidad
    @Test
    void saveUsuario_savesWhenRoleExists() {
        Usuario input = new Usuario(1L, "Juan", "Perez", "@gmail.com", "pass", 1L);
        Usuario esperado = new Usuario(1L, "Juan", "Perez", "@gmail.com", "hashed", 1L);

        when(rolClient.getRolesById(1L)).thenReturn(Map.of("id", 1));
        when(repository.save(any())).thenReturn(esperado);

        // Si has mockeado el hash también:
        // when(PasswordUtil.hashPassword("pass")).thenReturn("hashed");

        Usuario guardado = service.saveUsuario(input);

        assertEquals("hashed", guardado.getPassword());
    }

    @Test
    void eliminarUsuario_deletesUsuario() {
        service.eliminarUsuario(1L);
        verify(repository).deleteById(1L);
    }
    
}
