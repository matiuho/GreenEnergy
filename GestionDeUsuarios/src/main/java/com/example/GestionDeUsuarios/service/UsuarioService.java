package com.example.GestionDeUsuarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GestionDeUsuarios.model.Usuario;
import com.example.GestionDeUsuarios.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario crearUsuario(Usuario usuario) {
        usuario.setActivo(true);
        return repository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        existente.setNombre(usuario.getNombre());
        existente.setApellido(usuario.getApellido());
        existente.setCorreo(usuario.getCorreo());
        existente.setTelefono(usuario.getTelefono());
        existente.setDireccion(usuario.getDireccion());
        existente.setRol(usuario.getRol());

        return repository.save(existente);
    }

    public void desactivarUsuario(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        usuario.setActivo(false);
        repository.save(usuario);
    }

    public Usuario obtenerPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontraado con ID: " + id));
    }

    public List<Usuario> listarUsuariosActivos() {
        return repository.findByActivoTrue();
    }
}
