package com.example.servicio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.servicio.model.Servicio;
import com.example.servicio.repository.ServicioRepository;

@Configuration
public class LocalDataBase {
    @Bean
    CommandLineRunner initDatabase(ServicioRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                //cargar datos de servicios 
                repository.save(new Servicio(null,"1 Servicio","instalacion de paneles soales",10000,"disponible"));
                System.out.println("Base de datos inicializada con datos de prueba.");
            } else {
                System.out.println("Base de datos ya contiene datos.");
                
            }
            // Aquí puedes inicializar la base de datos con datos de prueba si lo deseas
            // Por ejemplo:
            // repository.save(new Servicio("Servicio 1", 100.0));
            // repository.save(new Servicio("Servicio 2", 200.0));
        };
    }



}
