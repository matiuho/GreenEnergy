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
                repository.save(new Servicio(null,
                                 "Instalación On-Grid",
                                "Conectada a la red eléctrica; permite reducir el consumo de la compañía eléctrica.",
                                1500000,true));
                repository.save(new Servicio(null,
                                "Instalación Off-Grid",
                                "Funciona de forma autónoma usando baterías; ideal para zonas sin acceso a la red.",
                                1000000,true));
                repository.save(new Servicio(null,
                                " Instalación Híbrida",
                                "Combina red eléctrica y baterías para asegurar suministro continuo.",
                                3000000,true));
                repository.save(new Servicio(null,
                                " Instalación Comercial",
                                "Diseñada para oficinas o negocios que buscan ahorro energético.",
                                2500000,true));
                repository.save(new Servicio(null,
                                " Instalación Agrícola o Rural",
                                "Instalación en zonas de campo para alimentar equipos como bombas o cercas.",
                                2500000,true));
                repository.save(new Servicio(null,
                                " Instalación en Suelo",
                                "Paneles montados directamente en el terreno, con estructuras fijas o móviles.",
                                1850000,true));
                repository.save(new Servicio(null,
                                "Instalación con Seguimiento Solar",
                                "Paneles que se mueven siguiendo al sol para captar más energía.",
                                3500000,true));
                                System.out.println("Base de datos inicializada con datos");
            } else {
                System.out.println("Base de datos ya contiene datos.");  
            }
        };
    }
}
