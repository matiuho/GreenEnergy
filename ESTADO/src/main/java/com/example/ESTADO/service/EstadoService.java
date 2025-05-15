package com.example.ESTADO.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ESTADO.model.Estado;
import com.example.ESTADO.repository.EstadoRepository;
import com.example.ESTADO.webclient.Proyectoproyec;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstadoService {
    @Autowired
    private EstadoRepository estadoRepository;
   


    //metodo para obtener todos los PEDIDOS
    public List<Estado> getEstado() {
        return estadoRepository.findAll();
    }

    //metodo para agregar un nuevo ESTADO
    public Estado saveEstado(Estado nuevoEstado) {
        //verificar si existe consultando al microservicio PROYECTO
        Map<String, Object> proyecto = proyectoproyect.getProyectoPorId(nuevoEstado.getIdEstado());
        //verifico si me trajo el proyecto
        if (proyecto != null || proyecto.isEmpty()) {
            throw new RuntimeException("El proyecto no existe");
        }
        return  estadoRepository.save(nuevoEstado);
    }
}

