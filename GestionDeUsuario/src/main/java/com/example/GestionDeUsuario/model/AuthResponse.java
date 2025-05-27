package com.example.GestionDeUsuario.model;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    // en este caso es necesario crear 
    // un constructor
    public AuthResponse(String token) {
        this.token = token;
    }


    


}
