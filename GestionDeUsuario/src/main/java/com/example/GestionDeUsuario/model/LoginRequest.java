package com.example.GestionDeUsuario.model;

public class LoginRequest {
    private String nombre; 
    private String password;

    // Constructor vacío
    public LoginRequest() {}

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



