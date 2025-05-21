package com.example.direccion.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.example.direccion.model.Comuna;
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

    //metodo para mostrar todas las direcciones
    public List<Direccion> getDirecciones() {
        return direccionRepository.findAll();
    }


    //metodo para crear una nueva direccion
    public Direccion saveDireccion(Direccion nuevaDireccion) {
    // Paso 1: Verificar si el usuario existe en microservicio de autenticación
    Map<String, Object> usuario = clienteClient.getUsuarioById(nuevaDireccion.getIdUsuario());
    if (usuario == null || usuario.isEmpty()) {
        throw new RuntimeException(" Usuario no encontrado con ID: " + nuevaDireccion.getIdUsuario());
    }
    //  Verificar si la comuna existe localmente
    Long idComuna = nuevaDireccion.getComuna().getIdComuna();
    Comuna comuna = comunaRepository.findById(idComuna)
            .orElseThrow(() -> new RuntimeException(" Comuna no encontrada con ID: " + idComuna));
    //  Asignar la comuna a la dirección
    nuevaDireccion.setComuna(comuna);
    //  Guardar la dirección
    Direccion guardada = direccionRepository.save(nuevaDireccion);

    System.out.println("✅ Dirección creada correctamente: " + guardada);
    return guardada;
    }




    public Direccion obtenerDireccionPorId(Long id) {
        return direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Direccion no encontrada con ID: " + id));
    }



}
