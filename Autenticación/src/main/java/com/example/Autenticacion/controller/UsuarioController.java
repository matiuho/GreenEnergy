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
@RequestMapping("/api/v1")
public class UsuarioController {
    @Autowired
    private RolService rolService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/user")
    public ResponseEntity<List<Usuario>> obtenerTodosUsuarios(){
        List<Usuario> users = usuarioService.obtenerUsuarios();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Rol>> obtenerRoles(){
        List<Rol> roles = rolService.obtenerRoles();
        if(roles.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/users")
    public ResponseEntity<?> crearUsuario(@RequestParam String username, @RequestParam String password, @RequestParam Long rolId){
        try {
            Usuario usernuevo = usuarioService.creUsuario(username, password, rolId);
            return ResponseEntity.status(HttpStatus.CREATED).body(usernuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
