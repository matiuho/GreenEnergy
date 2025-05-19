package com.example.Autenticacion.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Autenticacion.model.Usuario;
import com.example.Autenticacion.repository.RolRepository;
import com.example.Autenticacion.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario  saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
