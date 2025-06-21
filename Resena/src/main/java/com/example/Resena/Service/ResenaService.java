package com.example.Resena.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Resena.Model.Resena;
import com.example.Resena.Repository.ResenaRepository;
import com.example.Resena.WebClient.ServicioClient;
import com.example.Resena.WebClient.UserClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ResenaService {
    @Autowired
    private ResenaRepository resenaRepository;
    @Autowired
    private UserClient userClient;
    @Autowired
    private ServicioClient servicioClient;


    //metodo para consultar todas las reseñas
    public List<Resena> getResenas(){
        return resenaRepository.findAll();
    }


    public Resena savResena(Resena nuevaResena) {
        //verificar si el usuario existe consultando al microservicio de usuario
        Map<String, Object> user = userClient.getUsuarioById(nuevaResena.getIdUsuario());
        //verifico si me trajo el usuario o no
        if (user == null || user.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        //verificar si el servicio existe consultando al microservicio de servicio
        Map<String, Object> servicio = servicioClient.getServicioById(nuevaResena.getIdServicio());
        //verifico si me trajo el servicio o no
        if (servicio == null || servicio.isEmpty()) {
            throw new RuntimeException("Servicio no encontrado");
        }
        return resenaRepository.save(nuevaResena);
    }


    public Resena getResenaPorId(Long id){
        return resenaRepository.findById(id).orElseThrow(()-> new RuntimeException("Resena no encontrado"));
    }

    
    //metodo para buscar por ID USUARIO
    public List<Resena> obtenerReByUsuario(Long idUsuario){
        return resenaRepository.findByIdUsuario(idUsuario);
    }


}

