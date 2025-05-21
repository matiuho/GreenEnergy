package com.example.direccion.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.direccion.model.Comuna;
import com.example.direccion.model.Direccion;
import com.example.direccion.model.Region;
import com.example.direccion.repository.ComunaRepository;
import com.example.direccion.repository.DireccionRepository;
import com.example.direccion.repository.RegionRepository;

@Configuration
public class LoadDataBase {

    @Bean
    CommandLineRunner InitDataBase(RegionRepository regionRepository, ComunaRepository comunaRepository, DireccionRepository direccionRepository) {
        return args -> {
            if (regionRepository.count() == 0) {
                // insertar regiones
                regionRepository.save(new Region(1, "Arica y Parinacota"));
                regionRepository.save(new Region(2, "Tarapacá"));
                regionRepository.save(new Region(3, "Antofagasta"));
                regionRepository.save(new Region(4, "Atacama"));
                regionRepository.save(new Region(5, "Coquimbo"));
                regionRepository.save(new Region(6, "Valparaíso"));
                regionRepository.save(new Region(7, "Metropolitana de Santiago"));
                regionRepository.save(new Region(8, "O'Higgins"));
                regionRepository.save(new Region(9, "Maule"));
                regionRepository.save(new Region(10, "Ñuble"));
                regionRepository.save(new Region(11, "Biobío"));
                regionRepository.save(new Region(12, "La Araucanía"));
                regionRepository.save(new Region(13, "Los Ríos"));
                regionRepository.save(new Region(14, "Los Lagos"));
                regionRepository.save(new Region(15, "Aysén del General Carlos Ibáñez del Campo"));
                regionRepository.save(new Region(16, "Magallanes y de la Antártica Chilena"));

                // insertar comunas
                // Región 1: Arica y Parinacota
                comunaRepository.save(new Comuna("Arica", new Region(1, null)));
                comunaRepository.save(new Comuna("Camarones", new Region(1, null)));
                comunaRepository.save(new Comuna("Putre", new Region(1, null)));
                comunaRepository.save(new Comuna("General Lagos", new Region(1, null)));

                // Región 2: Tarapacá
                comunaRepository.save(new Comuna("Iquique", new Region(2, null)));
                comunaRepository.save(new Comuna("Alto Hospicio", new Region(2, null)));

                // Región 3: Antofagasta
                comunaRepository.save(new Comuna("Antofagasta", new Region(3, null)));
                comunaRepository.save(new Comuna("Mejillones", new Region(3, null)));
                comunaRepository.save(new Comuna("Sierra Gorda", new Region(3, null)));
                comunaRepository.save(new Comuna("Taltal", new Region(3, null)));

                // Región 4: Atacama
                comunaRepository.save(new Comuna("Copiapó", new Region(4, null)));
                comunaRepository.save(new Comuna("Caldera", new Region(4, null)));
                comunaRepository.save(new Comuna("Tierra Amarilla", new Region(4, null)));

                // Región 5: Coquimbo
                comunaRepository.save(new Comuna("La Serena", new Region(5, null)));
                comunaRepository.save(new Comuna("Coquimbo", new Region(5, null)));
                comunaRepository.save(new Comuna("Andacollo", new Region(5, null)));
                comunaRepository.save(new Comuna("La Higuera", new Region(5, null)));
                comunaRepository.save(new Comuna("Paihuano", new Region(5, null)));
                comunaRepository.save(new Comuna("Vicuña", new Region(5, null)));

                // Región 6: Valparaíso
                comunaRepository.save(new Comuna("Valparaíso", new Region(6, null)));
                comunaRepository.save(new Comuna("Casablanca", new Region(6, null)));
                comunaRepository.save(new Comuna("Concón", new Region(6, null)));
                comunaRepository.save(new Comuna("Juan Fernández", new Region(6, null)));
                comunaRepository.save(new Comuna("Puchuncaví", new Region(6, null)));
                comunaRepository.save(new Comuna("Quilpué", new Region(6, null)));
                comunaRepository.save(new Comuna("Quintero", new Region(6, null)));
                comunaRepository.save(new Comuna("Villa Alemana", new Region(6, null)));

                // Región 7: Metropolitana de Santiago
                comunaRepository.save(new Comuna("Santiago", new Region(7, null)));
                comunaRepository.save(new Comuna("Cerrillos", new Region(7, null)));
                comunaRepository.save(new Comuna("Cerro Navia", new Region(7, null)));
                comunaRepository.save(new Comuna("Conchalí", new Region(7, null)));
                comunaRepository.save(new Comuna("El Bosque", new Region(7, null)));
                comunaRepository.save(new Comuna("Estación Central", new Region(7, null)));
                comunaRepository.save(new Comuna("Huechuraba", new Region(7, null)));
                comunaRepository.save(new Comuna("Independencia", new Region(7, null)));
                comunaRepository.save(new Comuna("La Cisterna", new Region(7, null)));
                comunaRepository.save(new Comuna("La Florida", new Region(7, null)));
                comunaRepository.save(new Comuna("La Granja", new Region(7, null)));
                comunaRepository.save(new Comuna("La Pintana", new Region(7, null)));
                comunaRepository.save(new Comuna("La Reina", new Region(7, null)));
                comunaRepository.save(new Comuna("Las Condes", new Region(7, null)));
                comunaRepository.save(new Comuna("Lo Barnechea", new Region(7, null)));
                comunaRepository.save(new Comuna("Lo Espejo", new Region(7, null)));
                comunaRepository.save(new Comuna("Lo Prado", new Region(7, null)));
                comunaRepository.save(new Comuna("Macul", new Region(7, null)));
                comunaRepository.save(new Comuna("Maipú", new Region(7, null)));
                comunaRepository.save(new Comuna("Ñuñoa", new Region(7, null)));
                comunaRepository.save(new Comuna("Pedro Aguirre Cerda", new Region(7, null)));
                comunaRepository.save(new Comuna("Peñalolén", new Region(7, null)));
                comunaRepository.save(new Comuna("Providencia", new Region(7, null)));
                comunaRepository.save(new Comuna("Pudahuel", new Region(7, null)));
                comunaRepository.save(new Comuna("Quilicura", new Region(7, null)));
                comunaRepository.save(new Comuna("Quinta Normal", new Region(7, null)));
                comunaRepository.save(new Comuna("Recoleta", new Region(7, null)));
                comunaRepository.save(new Comuna("Renca", new Region(7, null)));
                comunaRepository.save(new Comuna("San Joaquín", new Region(7, null)));
                comunaRepository.save(new Comuna("San Miguel", new Region(7, null)));
                comunaRepository.save(new Comuna("San Ramón", new Region(7, null)));
                comunaRepository.save(new Comuna("Vitacura", new Region(7, null)));

                // Región 8: O'Higgins
                comunaRepository.save(new Comuna("Rancagua", new Region(8, null)));
                comunaRepository.save(new Comuna("Codegua", new Region(8, null)));
                comunaRepository.save(new Comuna("Coinco", new Region(8, null)));
                comunaRepository.save(new Comuna("Coltauco", new Region(8, null)));
                comunaRepository.save(new Comuna("Doñihue", new Region(8, null)));
                comunaRepository.save(new Comuna("Graneros", new Region(8, null)));
                comunaRepository.save(new Comuna("Las Cabras", new Region(8, null)));
                comunaRepository.save(new Comuna("Machalí", new Region(8, null)));
                comunaRepository.save(new Comuna("Malloa", new Region(8, null)));
                comunaRepository.save(new Comuna("Mostazal", new Region(8, null)));
                comunaRepository.save(new Comuna("Olivar", new Region(8, null)));
                comunaRepository.save(new Comuna("Peumo", new Region(8, null)));
                comunaRepository.save(new Comuna("Pichidegua", new Region(8, null)));
                comunaRepository.save(new Comuna("Quinta de Tilcoco", new Region(8, null)));
                comunaRepository.save(new Comuna("Rengo", new Region(8, null)));
                comunaRepository.save(new Comuna("Requínoa", new Region(8, null)));
                comunaRepository.save(new Comuna("San Vicente", new Region(8, null)));

                // Región 9: Maule
                comunaRepository.save(new Comuna("Talca", new Region(9, null)));
                comunaRepository.save(new Comuna("Constitución", new Region(9, null)));
                comunaRepository.save(new Comuna("Curepto", new Region(9, null)));
                comunaRepository.save(new Comuna("Empedrado", new Region(9, null)));
                comunaRepository.save(new Comuna("Maule", new Region(9, null)));
                comunaRepository.save(new Comuna("Pelarco", new Region(9, null)));
                comunaRepository.save(new Comuna("Pencahue", new Region(9, null)));
                comunaRepository.save(new Comuna("Rauco", new Region(9, null)));
                comunaRepository.save(new Comuna("Romeral", new Region(9, null)));
                comunaRepository.save(new Comuna("Sagrada Familia", new Region(9, null)));
                comunaRepository.save(new Comuna("San Clemente", new Region(9, null)));
                comunaRepository.save(new Comuna("Teno", new Region(9, null)));
                comunaRepository.save(new Comuna("Vichuquén", new Region(9, null)));

                // Región 10: Ñuble
                comunaRepository.save(new Comuna("Chillán", new Region(10, null)));
                comunaRepository.save(new Comuna("Bulnes", new Region(10, null)));
                comunaRepository.save(new Comuna("Chillán Viejo", new Region(10, null)));
                comunaRepository.save(new Comuna("Cobquecura", new Region(10, null)));
                comunaRepository.save(new Comuna("Coelemu", new Region(10, null)));
                comunaRepository.save(new Comuna("Coihueco", new Region(10, null)));
                comunaRepository.save(new Comuna("Ninhue", new Region(10, null)));
                comunaRepository.save(new Comuna("Pemuco", new Region(10, null)));
                comunaRepository.save(new Comuna("Pinto", new Region(10, null)));
                comunaRepository.save(new Comuna("Quillón", new Region(10, null)));
                comunaRepository.save(new Comuna("Quirihue", new Region(10, null)));
                comunaRepository.save(new Comuna("Ránquil", new Region(10, null)));
                comunaRepository.save(new Comuna("San Carlos", new Region(10, null)));
                comunaRepository.save(new Comuna("San Fabián", new Region(10, null)));
                comunaRepository.save(new Comuna("San Ignacio", new Region(10, null)));
                comunaRepository.save(new Comuna("San Nicolás", new Region(10, null)));
                comunaRepository.save(new Comuna("Treguaco", new Region(10, null)));
                comunaRepository.save(new Comuna("Yungay", new Region(10, null)));

                // Región 11: Biobío
                comunaRepository.save(new Comuna("Concepción", new Region(11, null)));
                comunaRepository.save(new Comuna("Coronel", new Region(11, null)));
                comunaRepository.save(new Comuna("Chiguayante", new Region(11, null)));
                comunaRepository.save(new Comuna("Florida", new Region(11, null)));
                comunaRepository.save(new Comuna("Hualpén", new Region(11, null)));
                comunaRepository.save(new Comuna("Hualqui", new Region(11, null)));
                comunaRepository.save(new Comuna("Lota", new Region(11, null)));
                comunaRepository.save(new Comuna("Penco", new Region(11, null)));
                comunaRepository.save(new Comuna("San Pedro de la Paz", new Region(11, null)));
                comunaRepository.save(new Comuna("Santa Juana", new Region(11, null)));
                comunaRepository.save(new Comuna("Talcahuano", new Region(11, null)));
                comunaRepository.save(new Comuna("Tomé", new Region(11, null)));
                comunaRepository.save(new Comuna("Hualqui", new Region(11, null)));

                // Región 12: La Araucanía
                comunaRepository.save(new Comuna("Temuco", new Region(12, null)));
                comunaRepository.save(new Comuna("Carahue", new Region(12, null)));
                comunaRepository.save(new Comuna("Cunco", new Region(12, null)));
                comunaRepository.save(new Comuna("Curarrehue", new Region(12, null)));
                comunaRepository.save(new Comuna("Freire", new Region(12, null)));
                comunaRepository.save(new Comuna("Galvarino", new Region(12, null)));
                comunaRepository.save(new Comuna("Gorbea", new Region(12, null)));
                comunaRepository.save(new Comuna("Lautaro", new Region(12, null)));
                comunaRepository.save(new Comuna("Loncoche", new Region(12, null)));
                comunaRepository.save(new Comuna("Melipeuco", new Region(12, null)));
                comunaRepository.save(new Comuna("Nueva Imperial", new Region(12, null)));
                comunaRepository.save(new Comuna("Padre Las Casas", new Region(12, null)));
                comunaRepository.save(new Comuna("Perquenco", new Region(12, null)));
                comunaRepository.save(new Comuna("Pitrufquén", new Region(12, null)));
                comunaRepository.save(new Comuna("Pucón", new Region(12, null)));
                comunaRepository.save(new Comuna("Saavedra", new Region(12, null)));
                comunaRepository.save(new Comuna("Teodoro Schmidt", new Region(12, null)));
                comunaRepository.save(new Comuna("Toltén", new Region(12, null)));
                comunaRepository.save(new Comuna("Vilcún", new Region(12, null)));
                comunaRepository.save(new Comuna("Villarrica", new Region(12, null)));

                // Región 13: Los Ríos
                comunaRepository.save(new Comuna("Valdivia", new Region(13, null)));
                comunaRepository.save(new Comuna("Corral", new Region(13, null)));
                comunaRepository.save(new Comuna("Lanco", new Region(13, null)));
                comunaRepository.save(new Comuna("Los Lagos", new Region(13, null)));
                comunaRepository.save(new Comuna("Máfil", new Region(13, null)));
                comunaRepository.save(new Comuna("Mariquina", new Region(13, null)));
                comunaRepository.save(new Comuna("Paillaco", new Region(13, null)));
                comunaRepository.save(new Comuna("Panguipulli", new Region(13, null)));

                // Región 14: Los Lagos
                comunaRepository.save(new Comuna("Puerto Montt", new Region(14, null)));
                comunaRepository.save(new Comuna("Calbuco", new Region(14, null)));
                comunaRepository.save(new Comuna("Cochamó", new Region(14, null)));
                comunaRepository.save(new Comuna("Fresia", new Region(14, null)));
                comunaRepository.save(new Comuna("Frutillar", new Region(14, null)));
                comunaRepository.save(new Comuna("Llanquihue", new Region(14, null)));
                comunaRepository.save(new Comuna("Los Muermos", new Region(14, null)));
                comunaRepository.save(new Comuna("Maullín", new Region(14, null)));
                comunaRepository.save(new Comuna("Puerto Varas", new Region(14, null)));
                comunaRepository.save(new Comuna("Castro", new Region(14, null)));
                comunaRepository.save(new Comuna("Chonchi", new Region(14, null)));
                comunaRepository.save(new Comuna("Curaco de Vélez", new Region(14, null)));
                comunaRepository.save(new Comuna("Dalcahue", new Region(14, null)));
                comunaRepository.save(new Comuna("Puqueldón", new Region(14, null)));
                comunaRepository.save(new Comuna("Queilén", new Region(14, null)));
                comunaRepository.save(new Comuna("Quellón", new Region(14, null)));
                comunaRepository.save(new Comuna("Quemchi", new Region(14, null)));
                comunaRepository.save(new Comuna("Quinchao", new Region(14, null)));

                // Región 15: Aysén del General Carlos Ibáñez del Campo
                comunaRepository.save(new Comuna("Coihaique", new Region(15, null)));
                comunaRepository.save(new Comuna("Lago Verde", new Region(15, null)));
                comunaRepository.save(new Comuna("Aysén", new Region(15, null)));
                comunaRepository.save(new Comuna("Cisnes", new Region(15, null)));
                comunaRepository.save(new Comuna("Guaitecas", new Region(15, null)));
                comunaRepository.save(new Comuna("Chile Chico", new Region(15, null)));
                comunaRepository.save(new Comuna("Río Ibáñez", new Region(15, null)));

                // Región 16: Magallanes y de la Antártica Chilena
                comunaRepository.save(new Comuna("Punta Arenas", new Region(16, null)));
                comunaRepository.save(new Comuna("Laguna Blanca", new Region(16, null)));
                comunaRepository.save(new Comuna("Río Verde", new Region(16, null)));
                comunaRepository.save(new Comuna("San Gregorio", new Region(16, null)));
                comunaRepository.save(new Comuna("Porvenir", new Region(16, null)));
                comunaRepository.save(new Comuna("Primavera", new Region(16, null)));
                comunaRepository.save(new Comuna("Timaukel", new Region(16, null)));
                comunaRepository.save(new Comuna("Natales", new Region(16, null)));
                comunaRepository.save(new Comuna("Torres del Paine", new Region(16, null)));

                direccionRepository.save(new Direccion (null,"av el guanaco 2535", new Region(16)));

                System.out.println("✅ Regiones Cargadas  Y Comunas correctamente.");

            } else {
                System.out.println("Regiones y Comunas ya cargadas.");
            }
        };
    }
}
