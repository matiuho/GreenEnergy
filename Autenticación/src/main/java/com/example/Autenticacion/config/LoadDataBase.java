package com.example.Autenticacion.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Autenticacion.model.Rol;
import com.example.Autenticacion.model.Usuario;
import com.example.Autenticacion.repository.RolRepository;
import com.example.Autenticacion.repository.UsuarioRepository;

@Configuration
public class LoadDataBase {
    @Bean
    public CommandLineRunner initDatabase(RolRepository rolRepo, UsuarioRepository userRepo) {
        return args -> {
            if (rolRepo.count() == 0 && userRepo.count() == 0) {

                rolRepo.save(new Rol(1, "ADMINISTRADOR"));
                rolRepo.save(new Rol(2, "CLIENTE"));
                rolRepo.save(new Rol(3, "COORDINADOR"));
                rolRepo.save(new Rol(4, "TECNICO"));
                rolRepo.save(new Rol(5, "SOPORTE"));

                userRepo.save(new Usuario("Juan", "Pérez", LocalDate.of(1990, 1, 1), "password123", new Rol(1, "ADMINISTRADOR")));

                System.out.println("Datos iniciales cargados");
            } else {
                System.out.println("Datos ya existentes. No se cargaron nuevos datos");
            }
        };
    }

}
