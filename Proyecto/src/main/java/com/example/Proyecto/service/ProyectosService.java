package com.example.Proyecto.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyecto.model.Proyecto;
import com.example.Proyecto.repository.ProyectoRepository;
import com.example.Proyecto.webclient.ClienteClient;
import com.example.Proyecto.webclient.ContratacionClient;
import com.example.Proyecto.webclient.UsuarioClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProyectosService {
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired ClienteClient clienteClient;
    @Autowired UsuarioClient usuarioClient;
    @Autowired ContratacionClient contratacionClient;

    // metodo para obtener todos los proyecto
    public List<Proyecto> getProyectos() {
        return proyectoRepository.findAll();
    }

    // metodo para agregar un nuevo proyecto
    public Proyecto saveProyecto(Proyecto nuevoproyecto) {
        // verificar si el estado existe consultando al microservicio estado
        Map<String, Object> estado = clienteClient.getEstadoById(nuevoproyecto.getEstadoId());
        // verifico si me trajo el estado o no
        if (estado == null || estado.isEmpty()) {
            throw new RuntimeException("Estado no encontrado");
        }
        // verificar si el tecnico exisyte consultando al microservicio de gestion de
        // usuario
        Map<String, Object> usuario = usuarioClient.getUsuarioById(nuevoproyecto.getUsuarioId());
        // verifico si me trajo el usuario o no
        if (usuario == null || usuario.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        // Validar que el usuario tiene el rol de técnico idRol = 3
        Integer idRol = (Integer) usuario.get("idRol"); // Extraer idRol del JSON recibido
        if (idRol == null || idRol != 3) {
            throw new RuntimeException("Solo los usuarios con rol de técnico (ID 3) pueden ser asignados.");
        }

        Map<String, Object> contratacion = contratacionClient.getContratacionById(nuevoproyecto.getContratacionId());
        // verifico si me trajo la contratacion o no
        if (contratacion == null || contratacion.isEmpty()) {
            throw new RuntimeException("Contratación no encontrada");
        }

        return proyectoRepository.save(nuevoproyecto);

    }

    // metodo para buscar un estado por su ID
    public Proyecto getProyectoPorId(Long id) {
        return proyectoRepository.findById(id).orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
    }

    // metodo para buscar por ID USUARIO
    public List<Proyecto> obtenerProByUsuarioo(Long usuarioId) {
        return proyectoRepository.findByIdUsuario(usuarioId);
    }
}
