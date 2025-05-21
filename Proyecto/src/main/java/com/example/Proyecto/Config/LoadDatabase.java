package com.example.Proyecto.Config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Proyecto.model.Proyecto;
import com.example.Proyecto.repository.ProyectoRepository;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDataBase(ProyectoRepository proyectoRepository) {
        return args -> {
            if (proyectoRepository.count() == 0) {
             proyectoRepository.save(new Proyecto(null, "probando", LocalDate.parse("2023-01-01"), Long.valueOf(1), Long.valueOf(1)));


                System.out.println("✅ Proyecto precargado correctamente");
            }
        };
    }
}


