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
    @Schema(description = "ID único de la solicitud de Soporte", example = "1", required = true)
    private Long idSoporte;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    @Schema(description = "Fecha de la solicitud de soporte (formato YYYY-MM-DD)", example = "2023-03-15", required = true)
    private LocalDate fecha;

    @Column(nullable = false, length = 100)
    @Schema(description = "Descripción detallada del problema o solicitud de soporte", example = "El panel solar no funciona correctamente", required = true)
    private String descripcion;

    @Column(nullable = false)
    @Schema(description = "ID del estado de la solicitud", example = "1", required = true)
    private Long idEstado;

    @Column(nullable = false)
    @Schema(description = "ID de la categoría del soporte", example = "1", required = true)
    private Long idCategoria;

    @Column(nullable = false)
    @Schema(description = "ID del usuario que realiza la solicitud de soporte", example = "1", required = true)
    private Long idusuario;
}
