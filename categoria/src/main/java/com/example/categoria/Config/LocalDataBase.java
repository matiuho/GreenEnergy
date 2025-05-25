package com.example.categoria.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.categoria.model.Categoria;
import com.example.categoria.repository.CategoriaRepository;
@Configuration
public class LocalDataBase {
    @Bean
    CommandLineRunner initDatabase(CategoriaRepository categoriaRepository){
     return args -> {
            if (categoriaRepository.count() == 0) {
                //cargar datos de servicios 
                categoriaRepository.save(new Categoria(null,"Técnico Postinstalación"));
                categoriaRepository.save(new Categoria(null,"Mantenimiento Preventivo"));
                categoriaRepository.save(new Categoria(null,"Monitorización Remota"));
                categoriaRepository.save(new Categoria(null,"Capacitación al Usuario"));
                System.out.println("Base de datos inicializada con datos");
            } else {
                System.out.println("Base de datos ya contiene datos.");
            }
        };
    }
}
