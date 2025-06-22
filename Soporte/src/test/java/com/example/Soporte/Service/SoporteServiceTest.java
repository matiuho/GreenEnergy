package com.example.Soporte.Service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Soporte.model.Soporte;
import com.example.Soporte.repository.SoporteRepository;
import com.example.Soporte.service.SoporteService;
import com.example.Soporte.webclient.CategoriaClient;
import com.example.Soporte.webclient.EstadoClient;
import com.example.Soporte.webclient.UserClient;

@ExtendWith(MockitoExtension.class)
public class SoporteServiceTest {
    @Mock
    private SoporteRepository soporteRepository;

    @Mock
    private EstadoClient estadoClient;

    @Mock
    private UserClient userClient;

    @Mock
    private CategoriaClient categoriaClient;

    @InjectMocks
    private SoporteService soporteService;

    @Test
    void findAll_returnsListFromRepository() {
        List<Soporte> mockSoportes = List.of(
                new Soporte(1L, LocalDate.now(), "Problema con el sistema", null, null, null));
        when(soporteRepository.findAll()).thenReturn(mockSoportes);
        List<Soporte> soportes = soporteService.getSoporte();

        assertThat(soportes).isEqualTo(mockSoportes);
    }

    @Test
    void crearSoporte_returnsCreatedAndJson() throws Exception {
        Soporte soporte = new Soporte();
        soporte.setFecha(LocalDate.of(2025, 10, 10));
        soporte.setDescripcion("descripcion");
        soporte.setIdEstado(1L);
        soporte.setIdCategoria(1L);
        soporte.setIdusuario(1L);

        Map<String, Object> estadoMock = Map.of("idEstado", 1L);
        Map<String, Object> categoriaMock = Map.of("idCategoria", 1L);
        Map<String, Object> usuarioMock = Map.of("idusuario", 1L);

        when(categoriaClient.getCategoriaById(1L)).thenReturn(categoriaMock);
        when(estadoClient.getEstadoById(1L)).thenReturn(estadoMock);
        when(userClient.getUsuarioById(1L)).thenReturn(usuarioMock);
        when(soporteRepository.save(any(Soporte.class))).thenReturn(soporte);

        Soporte resultado = soporteService.saveSoporte(soporte);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getDescripcion()).isEqualTo("descripcion");
        assertThat(resultado.getIdEstado()).isEqualTo(1L);
        assertThat(resultado.getIdCategoria()).isEqualTo(1L);
        assertThat(resultado.getIdusuario()).isEqualTo(1L);

        verify(categoriaClient).getCategoriaById(1L);
        verify(estadoClient).getEstadoById(1L);
        verify(userClient).getUsuarioById(1L);
        verify(soporteRepository).save(any(Soporte.class));
    }

    @Test
    void eliminarSoporte_eliminaCorrectamente() {
        Long id = 1L;

        soporteService.eliminarPorId(id);

        verify(soporteRepository).deleteById(id);
    }

    @Test
    void getSoportePorId_returnOkAndJson() {
        Long id = 1L;
        Soporte soporte = new Soporte();
        soporte.setIdSoporte(id);
        soporte.setDescripcion("Soporte técnico");
        soporte.setIdusuario(1L);

        when(soporteRepository.findById(id)).thenReturn(Optional.of(soporte));

        Soporte resultado = soporteService.getSoportePorId(id);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getIdSoporte()).isEqualTo(1L);
        assertThat(resultado.getDescripcion()).isEqualTo("Soporte técnico");
    }

    @Test
    void getSoportesPorUsuario_devuelveListaCorrecta() {
        Long idUsuario = 1L;

        Soporte soporte = new Soporte();
        soporte.setIdSoporte(1L);
        soporte.setDescripcion("Soporte asignado");
        soporte.setIdusuario(idUsuario);

        List<Soporte> soportes = List.of(soporte);

        when(soporteRepository.findByUsuario(idUsuario)).thenReturn(soportes);

        List<Soporte> resultado = soporteService.obtenerSoByUsuario(idUsuario);

        assertThat(resultado).isNotEmpty();
        assertThat(resultado.get(0).getIdusuario()).isEqualTo(idUsuario);
        assertThat(resultado.get(0).getDescripcion()).isEqualTo("Soporte asignado");
    }

}