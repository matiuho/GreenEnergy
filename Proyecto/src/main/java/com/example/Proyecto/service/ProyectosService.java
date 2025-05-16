package com.example.Proyecto.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proyecto.model.Proyecto;
import com.example.Proyecto.repository.ProyectoRepository;
import com.example.Proyecto.webclient.ClienteClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProyectosService {
    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private ClienteClient clienteClient;

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

        return proyectoRepository.save(nuevoproyecto);

    }


    

}
