package com.example.Roles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Roles.model.Roles;
import com.example.Roles.repository.RolesRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolesService {
    @Autowired
    private RolesRepository rolesRepository;
    //mostrar todas los roles
    public List<Roles> obtenerRoles() {
        return rolesRepository.findAll();
    }
    //buscar roles por id
    public Roles getRolesPorId(Long id){
        return rolesRepository.findById(id).orElseThrow(()-> new RuntimeException("Rol no encontrado"));
    }
}
