package com.example.direccion.Service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.direccion.model.Comuna;
import com.example.direccion.model.Direccion;
import com.example.direccion.model.Region;
import com.example.direccion.repository.ComunaRepository;
import com.example.direccion.repository.DireccionRepository;
import com.example.direccion.service.DireccionService;
import com.example.direccion.webclient.ClienteClient;

@ExtendWith(MockitoExtension.class)
public class DireccionServiceTest {

    @Mock
    private DireccionRepository direccionRepository;
    @Mock
    private ClienteClient clienteClient;
    @Mock
    private ComunaRepository comunaRepository;

    @InjectMocks
    private DireccionService direccionService;

    @Test
    void findAll_returnsListFromRepository() {
        List<Direccion> listaDireccion = Arrays.asList(new Direccion(1L, "Calle Prueba", null, null));

        // definir el comportamiento del mock (repositorio)
        when(direccionRepository.findAll()).thenReturn(listaDireccion);

        // ejecutar el metodo a probar
        List<Direccion> result = direccionService.getDirecciones();
        // verificar el resultado (criterios de aceptacion)
        assertThat(result).isEqualTo(listaDireccion);

    }

    @Test
    void save_returnsSaveDireccion() {

        Direccion nuevaDireccion = new Direccion();
        nuevaDireccion.setIdDireccion(1L);
        nuevaDireccion.setNombre("Prueba Direccion");
        nuevaDireccion.setComuna(new Comuna(1L, "Comuna Prueba", new Region(1, "Region Prueba")));
        nuevaDireccion.setIdUsuario(1L);

        // Simular las respuestas del microservicio

        Map<String, Object> usuarioMock = Map.of("idUsuario", 1L);

        when(clienteClient.getUsuarioById(1L)).thenReturn(usuarioMock);
        when(comunaRepository.findById(1L))
                .thenReturn(Optional.of(new Comuna(1L, "Comuna Prueba", new Region(1, "Region Prueba"))));
        // Simular el repositorio
        when(direccionRepository.save(nuevaDireccion)).thenReturn(nuevaDireccion);

        // ejecutar el metodo a probar
        Direccion resultado = direccionService.saveDireccion(nuevaDireccion);

        // comprueba que devuelven lo mismo
        assertThat(resultado).isEqualTo(nuevaDireccion);
    }

    @Test
    void findById_returnsDireccionById() {
        Long id = 1L;
        Direccion direccion = new Direccion(id, "Calle Prueba", null, null);

        // simular el repositorio
        when(direccionRepository.findById(id)).thenReturn(Optional.of(direccion));

        Direccion result = direccionService.obtenerDireccionPorId(id);

        // verificar el resultado (criterios de aceptacion)
        assertThat(result).isEqualTo(direccion);
    }

    @Test
    void deleteById_deletesDireccion() {
        Long id = 1L;
        Direccion direccion = new Direccion(id, "Calle Prueba", null, null);

        // Simulamos que inicialmente sí existe
        when(direccionRepository.findById(id)).thenReturn(Optional.of(direccion));

        direccionService.eliminarDireccion(id);

        // Simulamos que luego de la eliminación ya no está
        when(direccionRepository.findById(id)).thenReturn(Optional.empty());
        Optional<Direccion> resultadoPostEliminacion = direccionRepository.findById(id);
        assertThat(resultadoPostEliminacion).isEmpty();
        verify(direccionRepository).deleteById(id);
    }

    @Test
    void findById_returnsDireccionesPorUsuario() {
        // Arrange
        Long idUsuario = 1L;
        Direccion direccion1 = new Direccion(1L, "Calle Prueba 1", null, idUsuario);
        Direccion direccion2 = new Direccion(2L, "Calle Prueba 2", null, idUsuario);
        List<Direccion> lista = Arrays.asList(direccion1, direccion2);

        // Simular el repositorio
        when(direccionRepository.findByIdUsuario(idUsuario)).thenReturn(lista);

        List<Direccion> resultado = direccionService.obtenerDiByUsuario(idUsuario);

        assertThat(resultado).isEqualTo(lista);
    }

}