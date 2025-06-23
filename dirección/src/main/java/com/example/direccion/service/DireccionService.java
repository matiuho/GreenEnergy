package com.example.direccion.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.direccion.model.Direccion;
import com.example.direccion.repository.ComunaRepository;
import com.example.direccion.repository.DireccionRepository;
import com.example.direccion.webclient.ClienteClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DireccionService {
    @Autowired
    private DireccionRepository direccionRepository;
    @Autowired
    private ComunaRepository comunaRepository;

    @Autowired
    private ClienteClient clienteClient;

    // metodo para mostrar todas las direcciones
    public List<Direccion> getDirecciones() {
        return direccionRepository.findAll();
    }

    // metodo para crear una nueva direccion
    public Direccion saveDireccion(Direccion nuevaDireccion) {
        try {
            // Verificar si el usuario existe
            Map<String, Object> usuario = clienteClient.getUsuarioById(nuevaDireccion.getIdUsuario());
            if (usuario == null || usuario.isEmpty()) {
                throw new RuntimeException("Usuario no encontrado con ID: " + nuevaDireccion.getIdUsuario());
            }

            // Verificar si la comuna existe
            Long idComuna = nuevaDireccion.getComuna().getIdComuna();
            var comuna = comunaRepository.findById(idComuna)
                    .orElseThrow(() -> new RuntimeException("Comuna no encontrada con ID: " + idComuna));

            // Asignar la comuna encontrada a la dirección
            nuevaDireccion.setComuna(comuna);

            // Guardar la dirección
            return direccionRepository.save(nuevaDireccion);

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la dirección: " + e.getMessage(), e);
        }
    }

    public Direccion obtenerDireccionPorId(Long id) {
        return direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Direccion no encontrada con ID: " + id));
    }

    public void eliminarDireccion(Long id) {
        direccionRepository.deleteById(id);
    }

    public List<Direccion> obtenerDiByUsuario(Long idusuario) {
        List<Direccion> direccion = direccionRepository.findByIdUsuario(idusuario);
        if (direccion == null || direccion.isEmpty()) {
            throw new RuntimeException("No se encontraron direcciones para el usuario con ID: " + idusuario);
        }
        return direccion;
    }

}
