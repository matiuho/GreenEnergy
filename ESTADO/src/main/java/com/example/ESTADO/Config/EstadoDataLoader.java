package com.example.ESTADO.Config;

import com.example.ESTADO.model.Estado;
import com.example.ESTADO.repository.EstadoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EstadoDataLoader {

    @Bean
    public CommandLineRunner cargarEstados(EstadoRepository estadoRepository) {
        return args -> {
            if (estadoRepository.count() == 0) {
                estadoRepository.save(new Estado(null, "Planificación"));
                estadoRepository.save(new Estado(null, "Ejecución"));
                estadoRepository.save(new Estado(null, "Finalizado"));
                estadoRepository.save(new Estado(null, "Cancelado"));
            }
        };
    }
}
