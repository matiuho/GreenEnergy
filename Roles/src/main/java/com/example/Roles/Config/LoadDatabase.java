package com.example.Roles.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Roles.model.Roles;
import com.example.Roles.repository.RolesRepository;

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(RolesRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Roles(null, "ADMINISTRADOR"));
                repository.save(new Roles(null, "COORDINADOR"));
                repository.save(new Roles(null, "TECNICO INSTALADOR"));
                repository.save(new Roles(null, "SOPORTE"));
                repository.save(new Roles(null, "CLIENTE"));
                System.out.println("Datos iniciales cargados");

            } else {
                System.out.println("Ya existen datos en la base de datos");
            }
        };
    }

}
