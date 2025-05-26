package com.example.GestionDeUsuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GestionDeUsuario.model.AuthResponse;
import com.example.GestionDeUsuario.model.LoginRequest;
import com.example.GestionDeUsuario.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = usuarioService.autenticarUsuario(loginRequest.getNombre(), loginRequest.getPassword()); 

        if (token == null) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        return ResponseEntity.ok(new AuthResponse(token));
    }

}
