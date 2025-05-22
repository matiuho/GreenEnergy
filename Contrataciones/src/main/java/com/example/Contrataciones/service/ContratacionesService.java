package com.example.Contrataciones.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Contrataciones.model.Contrataciones;
import com.example.Contrataciones.repository.ContratacionesRepository;
import com.example.Contrataciones.webclient.ClienteClient;
import com.example.Contrataciones.webclient.DireccionClient;
import com.example.Contrataciones.webclient.ProyectClient;

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
    private ProyectClient proyectClient;
    //metodo para consultar todos los Contrataciones
    public List<Contrataciones> getContrataciones(){
        return contratacionesRepository.findAll();
    }
    //metodo para agregar un nuevo proyecto
    public Contrataciones saveContrataciones(Contrataciones nuevoproyecto) {
         System.out.println("🧾 Contratación recibida: " + nuevoproyecto);
         System.out.println("🧪 ID del servicio: " + nuevoproyecto.getIdServicio());

        //verificar si el estado existe consultando al microservicio estado
        Map<String, Object> contrataciones = clienteClient.getServicioById(nuevoproyecto.getIdServicio());
        //verifico si me trajo el estado o no
        if (contrataciones == null || contrataciones.isEmpty()) {
            throw new RuntimeException("Servicio no encontrado");
        }
        //verificar si el usuario exisyte consultando al microservicio de usuario
        Map<String, Object> direccion = direccionClient.getDireccionById(nuevoproyecto.getIdDireccion());
        //verifico si me trajo el usuario o no
        if (direccion == null || direccion.isEmpty()) {
            throw new RuntimeException("Direccion no encontrada");
            
        }
        //verificar si el proyecto existe consultando al microservicio de proyecto
        Map<String, Object> proyecto = proyectClient.getProyectoById(nuevoproyecto.getIdProyecto());
        //verifico si me trajo el proyecto o no
        if (proyecto == null || proyecto.isEmpty()) {
            throw new RuntimeException("Proyecto no encontrado");
        }
        return contratacionesRepository.save(nuevoproyecto);

    }


}
