package com.example.Contrataciones.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Contrataciones.model.Contrataciones;
import com.example.Contrataciones.repository.ContratacionesRepository;
import com.example.Contrataciones.webclient.ClienteClient;
import com.example.Contrataciones.webclient.DireccionClient;


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
    
    //metodo para consultar todos los Contrataciones
    public List<Contrataciones> getContrataciones(){
        return contratacionesRepository.findAll();
    }
    //metodo para agregar un nuevo proyecto
    public Contrataciones saveContrataciones(Contrataciones nuevoproyecto) {    
        //verificar si el  servicio consultando al microservicio  de servicio
        Map<String, Object> servicio = clienteClient.getServicioById(nuevoproyecto.getIdServicio());
        //verifico si me trajo el estado o no
        if (servicio == null || servicio.isEmpty()) {
            throw new RuntimeException("Servicio no encontrado");
        }
        //verificar si el usuario exisyte consultando al microservicio de usuario
        Map<String, Object> direccion = direccionClient.getDireccionById(nuevoproyecto.getIdDireccion());
        //verifico si me trajo el usuario o no
        if (direccion == null || direccion.isEmpty()) {
            throw new RuntimeException("Direccion no encontrada");
            
        }
        return contratacionesRepository.save(nuevoproyecto);
        
    }

    //metodo para buscar un estado por su ID
    public Contrataciones getContrtacionPorId(Long id){
        return contratacionesRepository.findById(id).orElseThrow(()-> new RuntimeException("Contratacion no encontrado"));
    }




}
