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
    @Autowired
    private ClienteClient clienteClient;
    @Autowired
    private UsuarioClient usuarioClient;
    @Autowired
    private ContratacionClient contratacionClient;

    //metodo para obtener todos los proyecto
    public List<Proyecto> getProyectos() {
        return proyectoRepository.findAll();
    }

    //metodo para agregar un nuevo proyecto
    public Proyecto saveProyecto(Proyecto nuevoproyecto) {
        //verificar si el estado existe consultando al microservicio estado
        Map<String, Object> estado = clienteClient.getEstadoById(nuevoproyecto.getEstadoId());
        //verifico si me trajo el estado o no
        if (estado == null || estado.isEmpty()) {
            throw new RuntimeException("Estado no encontrado");
        }
        //verificar si el usuario exisyte consultando al microservicio de usuario
        Map<String, Object> usuario = usuarioClient.getUsuarioById(nuevoproyecto.getUsuarioId());
        //verifico si me trajo el usuario o no
        if (usuario == null || usuario.isEmpty()) {
            throw new RuntimeException("Direccion no encontrada");  
        }
        Map<String, Object> contratacion = contratacionClient.getContratacionById(nuevoproyecto.getIdContratacion());
        //verifico si me trajo el usuario o no
        if (contratacion == null || contratacion.isEmpty()) {
            throw new RuntimeException("Contratación no encontrada");
        }
        
        return proyectoRepository.save(nuevoproyecto);

    }

    //metodo para buscar un estado por su ID
    public Proyecto getProyectoPorId(Long id){
        return proyectoRepository.findById(id).orElseThrow(()-> new RuntimeException("Proyecto no encontrado"));
    }


    

}
