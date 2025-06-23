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
    CommandLineRunner InitDataBase(RegionRepository regionRepository,
            ComunaRepository comunaRepository) {
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
                comunaRepository.save(new Comuna("Calama", new Region(3, null)));
                comunaRepository.save(new Comuna("Ollague", new Region(3, null)));
                comunaRepository.save(new Comuna("San Pedro de Atacama", new Region(3, null)));
                comunaRepository.save(new Comuna("Tocopilla", new Region(3, null)));
                comunaRepository.save(new Comuna("María Elena", new Region(3, null)));

                // Región 4: Atacama

                comunaRepository.save(new Comuna("Copiapó", new Region(4, null)));
                comunaRepository.save(new Comuna("Caldera", new Region(4, null)));
                comunaRepository.save(new Comuna("Tierra Amarilla", new Region(4, null)));
                comunaRepository.save(new Comuna("Vallenar", new Region(4, null)));
                comunaRepository.save(new Comuna("Huasco", new Region(4, null)));
                comunaRepository.save(new Comuna("Freirina", new Region(4, null)));
                comunaRepository.save(new Comuna("Alto del Carmen", new Region(4, null)));
                comunaRepository.save(new Comuna("Chañaral", new Region(4, null)));
                comunaRepository.save(new Comuna("Diego de Almagro", new Region(4, null)));

                // Región 5: Coquimbo

                comunaRepository.save(new Comuna("La Serena", new Region(5, null)));
                comunaRepository.save(new Comuna("Coquimbo", new Region(5, null)));
                comunaRepository.save(new Comuna("Andacollo", new Region(5, null)));
                comunaRepository.save(new Comuna("La Higuera", new Region(5, null)));
                comunaRepository.save(new Comuna("Paihuano", new Region(5, null)));
                comunaRepository.save(new Comuna("Vicuña", new Region(5, null)));
                comunaRepository.save(new Comuna("Ovalle", new Region(5, null)));
                comunaRepository.save(new Comuna("Monte Patria", new Region(5, null)));
                comunaRepository.save(new Comuna("Combarbalá", new Region(5, null)));
                comunaRepository.save(new Comuna("Punitaqui", new Region(5, null)));
                comunaRepository.save(new Comuna("Río Hurtado", new Region(5, null)));
                comunaRepository.save(new Comuna("Illapel", new Region(5, null)));
                comunaRepository.save(new Comuna("Salamanca", new Region(5, null)));
                comunaRepository.save(new Comuna("Los Vilos", new Region(5, null)));
                comunaRepository.save(new Comuna("Canela", new Region(5, null)));

                // Región 6: Valparaíso

                comunaRepository.save(new Comuna("Valparaíso", new Region(6, null)));
                comunaRepository.save(new Comuna("Viña del Mar", new Region(6, null)));
                comunaRepository.save(new Comuna("Concón", new Region(6, null)));
                comunaRepository.save(new Comuna("Quilpué", new Region(6, null)));
                comunaRepository.save(new Comuna("Villa Alemana", new Region(6, null)));
                comunaRepository.save(new Comuna("Quintero", new Region(6, null)));
                comunaRepository.save(new Comuna("Puchuncaví", new Region(6, null)));
                comunaRepository.save(new Comuna("Casablanca", new Region(6, null)));
                comunaRepository.save(new Comuna("San Antonio", new Region(6, null)));
                comunaRepository.save(new Comuna("Cartagena", new Region(6, null)));
                comunaRepository.save(new Comuna("El Tabo", new Region(6, null)));
                comunaRepository.save(new Comuna("El Quisco", new Region(6, null)));
                comunaRepository.save(new Comuna("Algarrobo", new Region(6, null)));
                comunaRepository.save(new Comuna("Santo Domingo", new Region(6, null)));
                comunaRepository.save(new Comuna("San Felipe", new Region(6, null)));
                comunaRepository.save(new Comuna("Llaillay", new Region(6, null)));
                comunaRepository.save(new Comuna("Putaendo", new Region(6, null)));
                comunaRepository.save(new Comuna("Catemu", new Region(6, null)));
                comunaRepository.save(new Comuna("Santa María", new Region(6, null)));
                comunaRepository.save(new Comuna("Los Andes", new Region(6, null)));
                comunaRepository.save(new Comuna("Calle Larga", new Region(6, null)));
                comunaRepository.save(new Comuna("Rinconada", new Region(6, null)));
                comunaRepository.save(new Comuna("San Esteban", new Region(6, null)));
                comunaRepository.save(new Comuna("La Ligua", new Region(6, null)));
                comunaRepository.save(new Comuna("Petorca", new Region(6, null)));
                comunaRepository.save(new Comuna("Papudo", new Region(6, null)));
                comunaRepository.save(new Comuna("Zapallar", new Region(6, null)));
                comunaRepository.save(new Comuna("Cabildo", new Region(6, null)));
                comunaRepository.save(new Comuna("Isla de Pascua", new Region(6, null)));
                comunaRepository.save(new Comuna("Juan Fernández", new Region(6, null)));

                // Región 7: Metropolitana de Santiago

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
                comunaRepository.save(new Comuna("Santiago", new Region(7, null)));
                comunaRepository.save(new Comuna("Vitacura", new Region(7, null)));
                comunaRepository.save(new Comuna("Puente Alto", new Region(7, null)));
                comunaRepository.save(new Comuna("Pirque", new Region(7, null)));
                comunaRepository.save(new Comuna("San José de Maipo", new Region(7, null)));
                comunaRepository.save(new Comuna("Colina", new Region(7, null)));
                comunaRepository.save(new Comuna("Lampa", new Region(7, null)));
                comunaRepository.save(new Comuna("Tiltil", new Region(7, null)));
                comunaRepository.save(new Comuna("San Bernardo", new Region(7, null)));
                comunaRepository.save(new Comuna("Buin", new Region(7, null)));
                comunaRepository.save(new Comuna("Calera de Tango", new Region(7, null)));
                comunaRepository.save(new Comuna("Paine", new Region(7, null)));
                comunaRepository.save(new Comuna("Melipilla", new Region(7, null)));
                comunaRepository.save(new Comuna("Alhué", new Region(7, null)));
                comunaRepository.save(new Comuna("Curacaví", new Region(7, null)));
                comunaRepository.save(new Comuna("María Pinto", new Region(7, null)));
                comunaRepository.save(new Comuna("San Pedro", new Region(7, null)));
                comunaRepository.save(new Comuna("Talagante", new Region(7, null)));
                comunaRepository.save(new Comuna("El Monte", new Region(7, null)));
                comunaRepository.save(new Comuna("Isla de Maipo", new Region(7, null)));
                comunaRepository.save(new Comuna("Padre Hurtado", new Region(7, null)));
                comunaRepository.save(new Comuna("Peñaflor", new Region(7, null)));

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
                comunaRepository.save(new Comuna("Olivar", new Region(8, null)));
                comunaRepository.save(new Comuna("Peumo", new Region(8, null)));
                comunaRepository.save(new Comuna("Pichidegua", new Region(8, null)));
                comunaRepository.save(new Comuna("Quinta de Tilcoco", new Region(8, null)));
                comunaRepository.save(new Comuna("Rengo", new Region(8, null)));
                comunaRepository.save(new Comuna("Requínoa", new Region(8, null)));
                comunaRepository.save(new Comuna("San Vicente", new Region(8, null)));
                comunaRepository.save(new Comuna("Pichilemu", new Region(8, null)));
                comunaRepository.save(new Comuna("La Estrella", new Region(8, null)));
                comunaRepository.save(new Comuna("Litueche", new Region(8, null)));
                comunaRepository.save(new Comuna("Marchigüe", new Region(8, null)));
                comunaRepository.save(new Comuna("Navidad", new Region(8, null)));
                comunaRepository.save(new Comuna("Paredones", new Region(8, null)));
                comunaRepository.save(new Comuna("San Fernando", new Region(8, null)));
                comunaRepository.save(new Comuna("Chépica", new Region(8, null)));
                comunaRepository.save(new Comuna("Chimbarongo", new Region(8, null)));
                comunaRepository.save(new Comuna("Lolol", new Region(8, null)));
                comunaRepository.save(new Comuna("Nancagua", new Region(8, null)));
                comunaRepository.save(new Comuna("Palmilla", new Region(8, null)));
                comunaRepository.save(new Comuna("Peralillo", new Region(8, null)));
                comunaRepository.save(new Comuna("Placilla", new Region(8, null)));
                comunaRepository.save(new Comuna("Pumanque", new Region(8, null)));
                comunaRepository.save(new Comuna("Santa Cruz", new Region(8, null)));

                // Región 9: Maule

                comunaRepository.save(new Comuna("Talca", new Region(9, null)));
                comunaRepository.save(new Comuna("San Clemente", new Region(9, null)));
                comunaRepository.save(new Comuna("Pelarco", new Region(9, null)));
                comunaRepository.save(new Comuna("Pencahue", new Region(9, null)));
                comunaRepository.save(new Comuna("Maule", new Region(9, null)));
                comunaRepository.save(new Comuna("San Rafael", new Region(9, null)));
                comunaRepository.save(new Comuna("Curepto", new Region(9, null)));
                comunaRepository.save(new Comuna("Constitución", new Region(9, null)));
                comunaRepository.save(new Comuna("Empedrado", new Region(9, null)));
                comunaRepository.save(new Comuna("Río Claro", new Region(9, null)));
                comunaRepository.save(new Comuna("Linares", new Region(9, null)));
                comunaRepository.save(new Comuna("San Javier", new Region(9, null)));
                comunaRepository.save(new Comuna("Villa Alegre", new Region(9, null)));
                comunaRepository.save(new Comuna("Yerbas Buenas", new Region(9, null)));
                comunaRepository.save(new Comuna("Colbún", new Region(9, null)));
                comunaRepository.save(new Comuna("Parral", new Region(9, null)));
                comunaRepository.save(new Comuna("Retiro", new Region(9, null)));
                comunaRepository.save(new Comuna("Longaví", new Region(9, null)));
                comunaRepository.save(new Comuna("Cauquenes", new Region(9, null)));
                comunaRepository.save(new Comuna("Chanco", new Region(9, null)));
                comunaRepository.save(new Comuna("Pelluhue", new Region(9, null)));
                comunaRepository.save(new Comuna("Curicó", new Region(9, null)));
                comunaRepository.save(new Comuna("Teno", new Region(9, null)));
                comunaRepository.save(new Comuna("Romeral", new Region(9, null)));
                comunaRepository.save(new Comuna("Molina", new Region(9, null)));
                comunaRepository.save(new Comuna("Sagrada Familia", new Region(9, null)));
                comunaRepository.save(new Comuna("Hualañé", new Region(9, null)));
                comunaRepository.save(new Comuna("Licantén", new Region(9, null)));
                comunaRepository.save(new Comuna("Vichuquén", new Region(9, null)));
                comunaRepository.save(new Comuna("Rauco", new Region(9, null)));

                // Región 10: Ñuble

                comunaRepository.save(new Comuna("Chillán", new Region(10, null)));
                comunaRepository.save(new Comuna("Chillán Viejo", new Region(10, null)));
                comunaRepository.save(new Comuna("Quillón", new Region(10, null)));
                comunaRepository.save(new Comuna("Bulnes", new Region(10, null)));
                comunaRepository.save(new Comuna("San Ignacio", new Region(10, null)));
                comunaRepository.save(new Comuna("El Carmen", new Region(10, null)));
                comunaRepository.save(new Comuna("Pemuco", new Region(10, null)));
                comunaRepository.save(new Comuna("Yungay", new Region(10, null)));
                comunaRepository.save(new Comuna("Coihueco", new Region(10, null)));
                comunaRepository.save(new Comuna("San Nicolás", new Region(10, null)));
                comunaRepository.save(new Comuna("San Carlos", new Region(10, null)));
                comunaRepository.save(new Comuna("San Fabián", new Region(10, null)));
                comunaRepository.save(new Comuna("Ñiquén", new Region(10, null)));
                comunaRepository.save(new Comuna("San Gregorio de Ñiquén", new Region(10, null)));
                comunaRepository.save(new Comuna("Ninhue", new Region(10, null)));
                comunaRepository.save(new Comuna("Quirihue", new Region(10, null)));
                comunaRepository.save(new Comuna("Cobquecura", new Region(10, null)));
                comunaRepository.save(new Comuna("Treguaco", new Region(10, null)));
                comunaRepository.save(new Comuna("Portezuelo", new Region(10, null)));
                comunaRepository.save(new Comuna("Ránquil", new Region(10, null)));
                comunaRepository.save(new Comuna("Coelemu", new Region(10, null)));

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
                comunaRepository.save(new Comuna("Los Ángeles", new Region(11, null)));
                comunaRepository.save(new Comuna("Antuco", new Region(11, null)));
                comunaRepository.save(new Comuna("Cabrero", new Region(11, null)));
                comunaRepository.save(new Comuna("Laja", new Region(11, null)));
                comunaRepository.save(new Comuna("Mulchén", new Region(11, null)));
                comunaRepository.save(new Comuna("Nacimiento", new Region(11, null)));
                comunaRepository.save(new Comuna("Negrete", new Region(11, null)));
                comunaRepository.save(new Comuna("Quilaco", new Region(11, null)));
                comunaRepository.save(new Comuna("Quilleco", new Region(11, null)));
                comunaRepository.save(new Comuna("San Rosendo", new Region(11, null)));
                comunaRepository.save(new Comuna("Santa Bárbara", new Region(11, null)));
                comunaRepository.save(new Comuna("Tucapel", new Region(11, null)));
                comunaRepository.save(new Comuna("Yumbel", new Region(11, null)));
                comunaRepository.save(new Comuna("Alto Biobío", new Region(11, null)));
                comunaRepository.save(new Comuna("Arauco", new Region(11, null)));
                comunaRepository.save(new Comuna("Cañete", new Region(11, null)));
                comunaRepository.save(new Comuna("Contulmo", new Region(11, null)));
                comunaRepository.save(new Comuna("Curanilahue", new Region(11, null)));
                comunaRepository.save(new Comuna("Lebu", new Region(11, null)));
                comunaRepository.save(new Comuna("Los Álamos", new Region(11, null)));
                comunaRepository.save(new Comuna("Tirúa", new Region(11, null)));

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
                comunaRepository.save(new Comuna("Cholchol", new Region(12, null)));
                comunaRepository.save(new Comuna("Angol", new Region(12, null)));
                comunaRepository.save(new Comuna("Collipulli", new Region(12, null)));
                comunaRepository.save(new Comuna("Curacautín", new Region(12, null)));
                comunaRepository.save(new Comuna("Ercilla", new Region(12, null)));
                comunaRepository.save(new Comuna("Lonquimay", new Region(12, null)));
                comunaRepository.save(new Comuna("Los Sauces", new Region(12, null)));
                comunaRepository.save(new Comuna("Lumaco", new Region(12, null)));
                comunaRepository.save(new Comuna("Purén", new Region(12, null)));
                comunaRepository.save(new Comuna("Renaico", new Region(12, null)));
                comunaRepository.save(new Comuna("Traiguén", new Region(12, null)));
                comunaRepository.save(new Comuna("Victoria", new Region(12, null)));

                // Región 13: Los Ríos

                comunaRepository.save(new Comuna("Valdivia", new Region(13, null)));
                comunaRepository.save(new Comuna("Corral", new Region(13, null)));
                comunaRepository.save(new Comuna("Lanco", new Region(13, null)));
                comunaRepository.save(new Comuna("Los Lagos", new Region(13, null)));
                comunaRepository.save(new Comuna("Máfil", new Region(13, null)));
                comunaRepository.save(new Comuna("Mariquina", new Region(13, null)));
                comunaRepository.save(new Comuna("Paillaco", new Region(13, null)));
                comunaRepository.save(new Comuna("Panguipulli", new Region(13, null)));
                comunaRepository.save(new Comuna("La Unión", new Region(13, null)));
                comunaRepository.save(new Comuna("Futrono", new Region(13, null)));
                comunaRepository.save(new Comuna("Lago Ranco", new Region(13, null)));
                comunaRepository.save(new Comuna("Río Bueno", new Region(13, null)));

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
                comunaRepository.save(new Comuna("Ancud", new Region(14, null)));
                comunaRepository.save(new Comuna("Chonchi", new Region(14, null)));
                comunaRepository.save(new Comuna("Curaco de Vélez", new Region(14, null)));
                comunaRepository.save(new Comuna("Dalcahue", new Region(14, null)));
                comunaRepository.save(new Comuna("Puqueldón", new Region(14, null)));
                comunaRepository.save(new Comuna("Queilén", new Region(14, null)));
                comunaRepository.save(new Comuna("Quellón", new Region(14, null)));
                comunaRepository.save(new Comuna("Quemchi", new Region(14, null)));
                comunaRepository.save(new Comuna("Quinchao", new Region(14, null)));
                comunaRepository.save(new Comuna("Osorno", new Region(14, null)));
                comunaRepository.save(new Comuna("Puerto Octay", new Region(14, null)));
                comunaRepository.save(new Comuna("Purranque", new Region(14, null)));
                comunaRepository.save(new Comuna("Puyehue", new Region(14, null)));
                comunaRepository.save(new Comuna("Río Negro", new Region(14, null)));
                comunaRepository.save(new Comuna("San Juan de la Costa", new Region(14, null)));
                comunaRepository.save(new Comuna("San Pablo", new Region(14, null)));

                // Región 15: Aysén del General Carlos Ibáñez del Campo

                comunaRepository.save(new Comuna("Coyhaique", new Region(15, null)));
                comunaRepository.save(new Comuna("Lago Verde", new Region(15, null)));
                comunaRepository.save(new Comuna("Aysén", new Region(15, null)));
                comunaRepository.save(new Comuna("Cisnes", new Region(15, null)));
                comunaRepository.save(new Comuna("Guaitecas", new Region(15, null)));
                comunaRepository.save(new Comuna("Cochrane", new Region(15, null)));
                comunaRepository.save(new Comuna("O'Higgins", new Region(15, null)));
                comunaRepository.save(new Comuna("Tortel", new Region(15, null)));
                comunaRepository.save(new Comuna("Chile Chico", new Region(15, null)));
                comunaRepository.save(new Comuna("Río Ibáñez", new Region(15, null)));

                // Región 16: Magallanes y de la Antártica Chilena

                comunaRepository.save(new Comuna("Punta Arenas", new Region(16, null)));
                comunaRepository.save(new Comuna("Laguna Blanca", new Region(16, null)));
                comunaRepository.save(new Comuna("Río Verde", new Region(16, null)));
                comunaRepository.save(new Comuna("San Gregorio", new Region(16, null)));
                comunaRepository.save(new Comuna("Puerto Natales", new Region(16, null)));
                comunaRepository.save(new Comuna("Torres del Paine", new Region(16, null)));
                comunaRepository.save(new Comuna("Porvenir", new Region(16, null)));
                comunaRepository.save(new Comuna("Primavera", new Region(16, null)));
                comunaRepository.save(new Comuna("Timaukel", new Region(16, null)));
                comunaRepository.save(new Comuna("Cabo de Hornos", new Region(16, null)));
                comunaRepository.save(new Comuna("Antártica", new Region(16, null)));
                

                System.out.println("Regiones Cargadas  Y Comunas correctamente.");

            } else {
                System.out.println("Regiones y Comunas ya cargadas.");
            }

        };
        
    }
    
}
