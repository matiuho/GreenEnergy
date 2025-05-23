package com.example.GestionDeUsuario.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GestionDeUsuario.model.Usuario;
import com.example.GestionDeUsuario.repository.UsuarioRepository;
import com.example.GestionDeUsuario.webclient.RolClient;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolClient rolClient;

    //mostrar todos los usuarios
    public List<Usuario> getUsuario() {
        return usuarioRepository.findAll();
    }
    //buscar Usuario por id
    public Usuario getUsuarioPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
    }
    //metodo para agregar un nuevo usuario
    public Usuario saveUsuario(Usuario nuevoUsuario) {

        //verificar si el rol existe consultando al microservicio roles
        Map<String, Object> roles  = rolClient.getRolesById(nuevoUsuario.getIdRol());
        //verifico si me trajo el rol o no
        if (roles == null||roles.isEmpty()) {
            throw new RuntimeException("Rol no encontrado");
        }

        return usuarioRepository.save(nuevoUsuario);
    }
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

}
