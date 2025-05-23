package com.example.resena.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.resena.Repository.ResenaRepository;
import com.example.resena.model.Resena;
import com.example.resena.webclient.ClienteClient;
import com.example.resena.webclient.UserClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ResenaService {
    @Autowired
    private ResenaRepository resenaRepository;
    @Autowired
    private UserClient userClient;
    @Autowired
    private ClienteClient clienteClient;
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
        Map<String, Object> servicio = clienteClient.getServicioById(nuevaResena.getIdServicio());
        //verifico si me trajo el servicio o no
        if (servicio == null || servicio.isEmpty()) {
            throw new RuntimeException("Servicio no encontrado");
        }
        return resenaRepository.save(nuevaResena);
    }


}
