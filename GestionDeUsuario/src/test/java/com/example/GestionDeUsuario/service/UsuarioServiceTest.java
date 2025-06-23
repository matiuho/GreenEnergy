package com.example.GestionDeUsuario.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(usuarios).isSameAs(mockUsuarios);
    }

    @Test
    void getUsuarioPorId_throwsExceptionIfNotFound() {

        Usuario mockUsuario = new Usuario(1L,
         "Juan", "Perez",
        "@gmail.com", "paswords",
        1L);


        when(repository.findById(1L)).thenReturn(java.util.Optional.of(mockUsuario));

        Usuario resultado = service.getUsuarioPorId(1L);

        assertThat(resultado).isSameAs(mockUsuario);
    }

   

    @Test
    void save_returnsSavedUsuario() {
        Usuario nuevoUsuario = new Usuario(1L,
         "Juan", "Perez",
          "@gmail.com", "pass",
           1L);
        

        Map<String, Object> usuarioMock = Map.of("idUsuario", 1L);
        when(rolClient.getRolesById(1L)).thenReturn(usuarioMock);

        when(repository.save(any())).thenReturn(nuevoUsuario);


        Usuario resultado = service.saveUsuario(nuevoUsuario);

        assertThat(resultado).isSameAs(nuevoUsuario);
    }

    @Test
    void eliminarUsuario_deletesUsuario() {

        service.eliminarUsuario(1L);

        verify(repository).deleteById(1L);
    }
    
}
