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
    @Schema(description = "ID único del Servicio")
    private Long idServicio;

    @Column(nullable = false, length = 50)
    @Schema(description = "Nombre del Servicio")
    private String nombre;

    @Column(nullable = false, length = 100)
    @Schema(description = "Descripción detallada del Servicio")
    private String descripcion;

    @Column(nullable = false)
    @Schema(description = "Precio del Servicio")
    private int precio;

    @Column(nullable = false)
    @Schema(description = "Indica si el servicio está activo o no (true/false)")
    private boolean activo = true;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
