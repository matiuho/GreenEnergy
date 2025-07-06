package com.example.servicio.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicio")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Detalles de un Servicio ofrecido")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del Servicio", example = "1", required = true)
    private Long idServicio;

    @Column(nullable = false, length = 50)
    @Schema(description = "Nombre del Servicio", example = "Paneles Solares de Techo", required = true)
    private String nombre;

    @Column(nullable = false, length = 100)
    @Schema(description = "Descripción detallada del Servicio", example = "Instalación de paneles solares en techos residenciales", required = true)
    private String descripcion;

    @Column(nullable = false)
    @Schema(description = "Precio del Servicio", example = "1500", required = true)
    private int precio;

    @Column(nullable = false)
    @Schema(description = "Indica si el servicio está activo o no (true/false)", required = true)
    private boolean activo = true;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
