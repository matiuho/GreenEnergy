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
                Proyecto proyecto = new Proyecto(
                    null,
                    "Proyecto de energía solar",
                    LocalDate.parse("2023-01-01"),
                    1, // <- int se convierte a Long
                    1  // <- int se convierte a Long
                );

                proyectoRepository.save(proyecto);
                System.out.println("✅ Proyecto precargado correctamente");
            }
        };
    }
}


