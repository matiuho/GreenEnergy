package com.example.Autenticacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Autenticacion.model.Rol;
import com.example.Autenticacion.repository.RolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {
    @Autowired  
    private RolRepository rolRepository;

    public List<Rol> obtenerRoles(){
        return rolRepository.findAll();
    }

    public Rol obtenerRolPorId(Integer id){
        return rolRepository.findById(id).orElseThrow(()-> new RuntimeException("Rol no encontrado con ID:" + id));
    }
}
