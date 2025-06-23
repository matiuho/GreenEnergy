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
    @Schema(description = "ID unico de Contrataciones")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContratacion;

    @Column(nullable = false)
    @Schema(description = "Fecha de la contratción")
    private LocalDate fechaContratacion;

    @Column(nullable = false)
    @Schema(description = "Fecha de inicio de la contratación")
    private LocalDate fechaInicio;

    @Column(nullable = false)
    @Schema(description = "Fecha del fin de la contratación")
    private LocalDate fechaFin;

    @Column(nullable = false)
    @Schema(description = "ID unico de Servicio")
    private Long idServicio;

    @Column(nullable = false)
    @Schema(description = "ID unico de Dirección")
    private Long idDireccion;

    @Column(nullable=false)
    @Schema(description = "ID unico de Usuario")
    private Long idusuario;

    

    

}
