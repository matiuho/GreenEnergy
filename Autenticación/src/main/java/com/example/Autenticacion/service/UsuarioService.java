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
    private RolRepository rolRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario creUsuario(String user, String pass, Long idRol){
        Rol rol = rolRepository.findById(idRol).orElseThrow(()-> new RuntimeException("Rol no existe con ID: " + idRol));

        Usuario usuario1 = new Usuario();
        usuario1.setUsername(user);
        usuario1.setPassword(pass);
        usuario1.setRol(rol);
        return usuarioRepository.save(usuario1);
    }
}
