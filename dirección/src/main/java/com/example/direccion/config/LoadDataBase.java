package com.example.direccion.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.direccion.model.Comuna;
import com.example.direccion.model.Region;
import com.example.direccion.repository.ComunaRepository;
import com.example.direccion.repository.RegionRepository;

@Configuration
public class LoadDataBase {



    @Bean
    CommandLineRunner InitDataBase(RegionRepository regionRepository, ComunaRepository comunaRepository) {
        return args -> {
            if (regionRepository.count() == 0 ) {
                //insertar regiones
                regionRepository.save(new Region(1,"Arica y Parinacota"));
                regionRepository.save(new Region(2,"Tarapacá"));
                regionRepository.save(new Region(3,"Antofagasta"));
                regionRepository.save(new Region(4,"Atacama"));
                regionRepository.save(new Region(5,"Coquimbo"));
                regionRepository.save(new Region(6,"Valparaíso"));
                regionRepository.save(new Region(7,"Metropolitana de Santiago"));
                regionRepository.save(new Region(8,"O'Higgins"));
                regionRepository.save(new Region(9,"Maule"));
                regionRepository.save(new Region(10,"Ñuble"));
                regionRepository.save(new Region(11,"Biobío"));
                regionRepository.save(new Region(12,"La Araucanía"));
                regionRepository.save(new Region(13,"Los Ríos"));
                regionRepository.save(new Region(14,"Los Lagos"));
                regionRepository.save(new Region(15,"Aysén del General Carlos Ibáñez del Campo"));
                regionRepository.save(new Region(16,"Magallanes y de la Antártica Chilena"));

                //insertar comunas
                comunaRepository.save(new Comuna( "Arica",  new Region(1, null)));

                


                System.out.println("✅ Regiones Cargadas  correctamente.");

            } else {
                System.out.println("ℹ️ Datos ya existentes. No se cargó información inicial.");
            }
        };
    }

    
}

    



