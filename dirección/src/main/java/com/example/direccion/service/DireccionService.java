package com.example.direccion.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.direccion.model.Comuna;
import com.example.direccion.model.Direccion;
import com.example.direccion.repository.ComunaRepository;
import com.example.direccion.repository.DireccionRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DireccionService {
    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ComunaRepository comunaRepository;

    //metodo para mostrar todas las direcciones
    public List<Direccion> obtenerTodasLasDirecciones() {
        return direccionRepository.findAll();
    }

    //metodo para crear una nueva direccion
    public Proyecto saveProyecto(Proyecto nuevoproyecto) {
        //verificar si el estado existe consultando al microservicio estado
        Map<String, Object> usuario = clienteClient.getEstadoById(nuevoproyecto.getEstadoId());
        //verifico si me trajo el estado o no
        if (estado == null || estado.isEmpty()) {
            throw new RuntimeException("Estado no encontrado");
        }

        return proyectoRepository.save(nuevoproyecto);

    }
    }
//usuario service


}
