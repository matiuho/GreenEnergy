package com.example.Autenticacion.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.Autenticacion.model.Rol;
import com.example.Autenticacion.model.Usuario;
import com.example.Autenticacion.service.RolService;
import com.example.Autenticacion.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private RolService rolService;
    @Autowired
    private UsuarioService usuarioService;

    //metodo para buscar todos los usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosUsuarios(){
        List<Usuario> users = usuarioService.obtenerUsuarios();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    //metodo para buscar todos los roles
    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> obtenerRoles(){
        List<Rol> roles = rolService.obtenerRoles();
        if(roles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    
    //metodo para crear un nuevo usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
    if (usuario.getRol() == null || usuario.getRol().getId() == null) {
        return ResponseEntity.badRequest().body("El campo 'rol.id' no puede ser nulo");
    }

    // Buscar el rol real desde la BD
    Optional<Rol> rolOpt = rolService.obtenerRoles().stream()
        .filter(rol -> rol.getId().equals(usuario.getRol().getId()))
        .findFirst();

    if (rolOpt.isEmpty()) {
        return ResponseEntity.badRequest().body("Rol no encontrado con ID: " + usuario.getRol().getId());
    }

    usuario.setRol(rolOpt.get()); // asignar el rol completo con nombre incluido

    Usuario guardado = usuarioService.saveUsuario(usuario);
    if (guardado == null) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el usuario");
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

     @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    



}
