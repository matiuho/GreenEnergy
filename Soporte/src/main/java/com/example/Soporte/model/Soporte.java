package com.example.Soporte.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "soporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Detalles de una solicitud de Soporte")
public class Soporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la solicitud de Soporte")
    private Long idSoporte;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @Schema(description = "Fecha de la solicitud de soporte (formato YYYY-MM-DD)") 
    private LocalDate fecha;

    @Column(nullable = false, length = 100)
    @Schema(description = "Descripción detallada del problema o solicitud de soporte")
    private String descripcion;

    @Column(nullable = false)
    @Schema(description = "ID del estado de la solicitud")
    private Long idEstado;

    @Column(nullable = false)
    @Schema(description = "ID de la categoría del soporte")
    private Long idCategoria;

    @Column(nullable = false)
    @Schema(description = "ID del usuario que realiza la solicitud de soporte")
    private Long idusuario;
}
