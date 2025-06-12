package com.example.Contrataciones.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.Contrataciones.model.Contrataciones;
import com.example.Contrataciones.repository.ContratacionesRepository;
import com.example.Contrataciones.webclient.ClienteClient;
import com.example.Contrataciones.webclient.DireccionClient;
import com.example.Contrataciones.webclient.UsuarioClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContratacionesService {
    @Autowired
    private ContratacionesRepository contratacionesRepository;
    @Autowired
    private ClienteClient clienteClient;
    @Autowired
    private DireccionClient direccionClient;
    @Autowired
    private UsuarioClient usuarioClient;

    // metodo para consultar todos los Contrataciones
    public List<Contrataciones> getContrataciones() {
        return contratacionesRepository.findAll();
    }

    // metodo para agregar un nuevo proyecto
    public Contrataciones saveContrataciones(Contrataciones nuevaContrataciones) {
        // verificar si el proyecto consultando al microservicio de proyecto
        Map<String, Object> servicio = clienteClient.getServicioById(nuevaContrataciones.getIdServicio());
        // verifico si me trajo el estado o no
        if (servicio == null || servicio.isEmpty()) {
            throw new RuntimeException("Servicio no encontrado");
        }
        // verificar si el proyecto exisyte consultando al microservicio de proyecto
        Map<String, Object> direccion = direccionClient.getDireccionById(nuevaContrataciones.getIdDireccion());
        // verifico si me trajo el usuario o no
        if (direccion == null || direccion.isEmpty()) {
            throw new RuntimeException("Direccion no encontrada");

        }
        Map<String, Object> usuario = usuarioClient.getUsuarioById(nuevaContrataciones.getIdusuario());
        // verifico si me trajo el estado o no
        if (usuario == null || usuario.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        return contratacionesRepository.save(nuevaContrataciones);

    }

    // metodo para buscar un contrataciones por su ID
    public Contrataciones getContrtacionPorId(Long id) {
        return contratacionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contratacion no encontrado"));
    }
    // metodo para buscar por ID USUARIO
    public List<Contrataciones> obtenerContratacionByUsuario(Long idusuario) {
        return contratacionesRepository.findByIdUsuario(idusuario);
    }

    

}
