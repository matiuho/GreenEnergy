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
            System.out.println(" Intentando crear dirección para usuario ID: " + nuevaDireccion.getIdUsuario());
            System.out.println("📦 Dirección recibida: " + nuevaDireccion);
            System.out.println("📦 Comuna recibida: " + nuevaDireccion.getComuna());

            // Paso 1: Verificar si el usuario existe
            Map<String, Object> usuario = clienteClient.getUsuarioById(nuevaDireccion.getIdUsuario());
            if (usuario == null || usuario.isEmpty()) {
                throw new RuntimeException(" Usuario no encontrado con ID: " + nuevaDireccion.getIdUsuario());
            }
            System.out.println("🟢 Usuario verificado correctamente: " + usuario.get("nombre"));

            // Paso 2: Verificar si la comuna es válida
            if (nuevaDireccion.getComuna() == null || nuevaDireccion.getComuna().getIdComuna() == null) {
                throw new RuntimeException("❌ La comuna no puede ser nula");
            }

            Long idComuna = nuevaDireccion.getComuna().getIdComuna();
            nuevaDireccion.setComuna(comunaRepository.findById(idComuna)
                    .orElseThrow(() -> new RuntimeException("❌ Comuna no encontrada con ID: " + idComuna)));

            // Paso 3: Guardar dirección
            Direccion guardada = direccionRepository.save(nuevaDireccion);
            System.out.println("✅ Dirección creada correctamente: " + guardada);

            return guardada;

        } catch (RuntimeException e) {
            System.err.println("❗ [ERROR DE LÓGICA] " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("❗ [ERROR DESCONOCIDO] " + e.getMessage());
            throw new RuntimeException("Error inesperado al guardar dirección", e);
        }
    }

    public Direccion obtenerDireccionPorId(Long id) {
        return direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Direccion no encontrada con ID: " + id));
    }

}
