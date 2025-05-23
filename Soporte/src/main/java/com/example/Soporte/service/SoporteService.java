package com.example.Soporte.service;

import com.example.Soporte.model.Soporte;
import com.example.Soporte.repository.SoporteRepository;
import com.example.Soporte.webclient.CategoriaClient;
import com.example.Soporte.webclient.EstadoClient;
import com.example.Soporte.webclient.RespuestaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    @Autowired
    private CategoriaClient categoriaClient;

    @Autowired
    private EstadoClient estadoClient;

    @Autowired
    private RespuestaClient respuestaClient;

    //metodo para consultar todos los Contrataciones
    public List<Soporte> getSoporte(){
        return soporteRepository.findAll();
    }

    //metodo para agregar un nuevo proyecto
    public Soporte saveSoporte(Soporte nuevosoporte) {

        //verificar si la categoria existe consultando al microservicio categoria
        Map<String, Object> categoria  = categoriaClient.getCategoriaById(nuevosoporte.getIdCategoria());
        //verifico si me trajo el estado o no
        if (categoria == null || categoria.isEmpty()) {
            throw new RuntimeException("Categoria no encontrado");
        }

        //verificar si el estado existe consultando al microservicio de estado
        Map<String, Object> estado = estadoClient.getEstadoById(nuevosoporte.getIdEstado());
        //verifico si me trajo el usuario o no
        if (estado == null || estado.isEmpty()) {
            throw new RuntimeException("Direccion no encontrada");
        }

        //verificar si el proyecto existe consultando al microservicio de proyecto
        Map<String, Object> respuesta = respuestaClient.getRespuestaById(nuevosoporte.getIdRespuesta());
        //verifico si me trajo el proyecto o no
        if (respuesta == null || respuesta.isEmpty()) {
            throw new RuntimeException("Respuesta no encontrada");
        }

        return soporteRepository.save(nuevosoporte);
    }
    
    
    public void eliminarPorId(Long id) {
        soporteRepository.deleteById(id);
    }
    //metodo para buscar un estado por su ID
     public Soporte getSoportePorId(Long id){
        return soporteRepository.findById(id).orElseThrow(()-> new RuntimeException("Soporte no encontrado"));
    }
    

}