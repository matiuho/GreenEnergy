package com.example.Estado.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Estado.model.Estado;
import com.example.Estado.repository.EstadoRepository;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(EstadoRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Estado(null, "Activo"));
                repository.save(new Estado(null, "Inactivo"));
                repository.save(new Estado(null, "Pendiente"));
                System.out.println("Datos iniciales cargados.");
            } else {
                System.out.println("La base de datos ya contiene datos, no se cargan datos iniciales.");
            }
        };
    }
}




