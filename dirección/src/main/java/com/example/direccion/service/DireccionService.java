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
    public Direccion  saveProyecto(Direccion nuevaDireccion) {
        //verificar si el usuario existe 
        Map<String, Object> usuario = clienteClient.getUsuarioById(nuevaDireccion.getIdUsuario());
        //verifico si me trajo el eusuario  
        if (usuario == null || usuario.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        Long idComuna = nuevaDireccion.getComuna().getIdComuna(); // O podrías recibirlo por separado

        Comuna comuna = comunaRepository.findById(idComuna)
            .orElseThrow(() -> new RuntimeException("Comuna no encontrada con ID: " + idComuna));

        // Paso 3: Asociar comuna a la dirección
        nuevaDireccion.setComuna(comuna);


        return direccionRepository.save(nuevaDireccion);

    }


}
