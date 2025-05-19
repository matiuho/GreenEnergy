package com.example.Autenticacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/user")
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
    public ResponseEntity<?> crearUsuario(@RequestParam String username, @RequestParam String password, @RequestParam Long rolId){
        try {
            Usuario usernuevo = usuarioService.creUsuario(password, username, null, password, rolId);
            return ResponseEntity.status(HttpStatus.CREATED).body(usernuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
