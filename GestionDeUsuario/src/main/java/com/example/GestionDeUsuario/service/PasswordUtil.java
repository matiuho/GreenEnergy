package com.example.GestionDeUsuario.service;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordUtil {
    // SHA-256 Genera un codigo de 64 caracteres
    public static String hashPassword(String password) {
        try {
            // MessageDigest es una instancia que funciona para SHA-
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Convierte la cadena a bytes y aplica el algoritmo SHA-256
            byte[] hashedBytes = md.digest(password.getBytes());
            // Convertir los bytes en Base64 para almacenarlos
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }



}
