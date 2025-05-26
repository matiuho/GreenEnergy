package com.example.GestionDeUsuario.Security;

import java.util.Date;
import java.util.Base64;

public class TokenUtil {
    public static String generateToken(String nombre) {
        String data = nombre + ":" + new Date().getTime();
        return Base64.getEncoder().encodeToString(data.getBytes()); // Token básico
    }

    public static boolean validateToken(String token, String nombre) {
        String decoded = new String(Base64.getDecoder().decode(token));
        return decoded.startsWith(nombre); // Verifica si el token pertenece al usuario
    }

    

}
