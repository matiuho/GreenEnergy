package com.example.Autenticacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Autenticacion.model.Rol;
import com.example.Autenticacion.model.Usuario;
import com.example.Autenticacion.repository.RolRepository;
import com.example.Autenticacion.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + id + " no encontrado"));
    }

    public Usuario saveUsuario(Usuario nuevoUsuario, Integer idRol) {
        Rol rol = rolRepository.findById(idRol)
                .orElseThrow(() -> new RuntimeException("Rol con ID " + idRol + " no encontrado"));
        nuevoUsuario.setRol(rol);
        return usuarioRepository.save(nuevoUsuario);
    }

}
