package com.example.GestionDeUsuario.Security;

import java.util.Date;
import java.util.Base64;

public class TokenUtil {
    public static String generateToken(String nombre) {
        //Crear Token Nombre del usuario 
        //new DateGetTime lo que representa la hora de la creacion
        String data = nombre + ":" + new Date().getTime();
        // devuelve una cadena en en ese formato
        return Base64.getEncoder().encodeToString(data.getBytes()); 
    }

    public static boolean validateToken(String token, String nombre) {
        // toma el token codificado  y lo convierte a su formato original
        String decoded = new String(Base64.getDecoder().decode(token));
        // Verifica si el token empieza con el nombre del usuario
        return decoded.startsWith(nombre); 
    }

    

}
