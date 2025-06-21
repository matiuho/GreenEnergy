package com.example.Resena.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.time.LocalDate;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Resena.Model.Resena;
import com.example.Resena.Repository.ResenaRepository;
import com.example.Resena.WebClient.ServicioClient;
import com.example.Resena.WebClient.UserClient;

@ExtendWith(MockitoExtension.class)
public class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @Mock
    private ServicioClient servicioClient;

    @Mock
    private UserClient userClient;

    @InjectMocks
    private ResenaService resenaService;

    @Test
    void findAll_returnsListFromRepository() {
        Resena resena = new Resena();
        resena.setIdResena(1L);
        resena.setFechaComentario(LocalDate.of(2025, 12, 12));
        resena.setComentario("Comentario de prueba");
        resena.setIdServicio(null);
        resena.setIdUsuario(null);

        when(resenaRepository.findAll()).thenReturn(Arrays.asList(resena));

        List<Resena> result = resenaService.getResenas();

        assertThat(result).isEqualTo(Arrays.asList(resena));
    }

    @Test
    void save_returnsSavedResena() {
        Resena resena = new Resena(
            1L,
            "Comentario de prueba",
            LocalDate.of(2025, 12, 12),
            1L, 1L);

        //simular las respuestas de los otros microservicios 
        Map<String, Object> usuarioMock = Map.of("id", 1L, "name", "Usuario Test");
        Map<String, Object> servicioMock = Map.of("id", 1L, "name", "Servicio Test");

        when(userClient.getUsuarioById(1L)).thenReturn(usuarioMock);
        when(servicioClient.getServicioById(1L)).thenReturn(servicioMock);

        // simular el repositorio
        when(resenaRepository.save(resena)).thenReturn(resena);

        Resena result = resenaService.savResena(resena);

        assertThat(result).isEqualTo(resena);
    }

    @Test
    void findByid_returnsResenaById(){
        Long idResena = 1L;
        Resena resena = new Resena(
            idResena,
            "Comentario de prueba",
            LocalDate.of(2025, 12, 12),
            1L, 1L);

        // Simular el repositorio
        when(resenaRepository.findById(idResena)).thenReturn(java.util.Optional.of(resena));

        Resena result = resenaService.getResenaPorId(idResena);

        assertThat(result).isEqualTo(resena);
    }

    @Test
    void findByUsuario_returnsResenasByUsuario() {
        Long idUsuario = 1L;

        Resena resena1 = new Resena(
        1L,
        "Comentario 1",
        LocalDate.of(2025, 12, 12),
        idUsuario, 1L);

        Resena resena2 = new Resena(
        2L,
        "Comentario 2",
        LocalDate.of(2025, 12, 13),
        idUsuario, 1L);

        List<Resena> resenas = Arrays.asList(resena1, resena2);

        when(resenaRepository.findByIdUsuario(idUsuario)).thenReturn(resenas);

        List<Resena> result = resenaService.obtenerReByUsuario(idUsuario);

        assertThat(result).containsExactlyInAnyOrder(resena1, resena2);
    }

}
