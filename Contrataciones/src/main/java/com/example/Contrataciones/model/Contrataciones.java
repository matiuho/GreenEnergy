package com.example.Contrataciones.model;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contrataciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Contrataciones")
public class Contrataciones {
    @Id
    @Schema(description = "ID unico de Contrataciones", example = "1", required = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContratacion;

    @Column(nullable = false)
    @Schema(description = "Fecha de la contratción", example = "2023-01-01", required = true)
    private LocalDate fechaContratacion;

    @Column(nullable = false)
    @Schema(description = "Fecha de inicio de la contratación", example = "2023-01-01", required = true)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    @Schema(description = "Fecha del fin de la contratación", example = "2023-01-01", required = true)
    private LocalDate fechaFin;

    @Column(nullable = false)
    @Schema(description = "ID unico de Servicio", example = "1", required = true)
    private Long idServicio;

    @Column(nullable = false)
    @Schema(description = "ID unico de Dirección", example = "1", required = true)
    private Long idDireccion;

    @Column(nullable=false)
    @Schema(description = "ID unico de Usuario", example = "1", required = true)
    private Long idusuario;

    

    

}
