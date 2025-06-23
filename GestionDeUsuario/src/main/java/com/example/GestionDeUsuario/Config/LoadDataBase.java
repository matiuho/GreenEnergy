package com.example.GestionDeUsuario.Config;

import java.util.Map;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.GestionDeUsuario.model.Usuario;
import com.example.GestionDeUsuario.repository.UsuarioRepository;
import com.example.GestionDeUsuario.webclient.RolClient;
import com.example.GestionDeUsuario.service.PasswordUtil;
@Configuration
public class LoadDataBase {

    

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository,
                                    RolClient rolClient) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                Map<String, Object> rol = rolClient.getRolesById(1L);
                Long idRol = ((Number) rol.get("idRol")).longValue();

                usuarioRepository.save(new Usuario("juan","Correa","Juan@gmail.com",PasswordUtil.hashPassword("password"),idRol));

                
                System.out.println("Usuario Cargado Correctamente");
            } else {
                System.out.println("Usuarios ya cargados");
            }
        };
    }
}
