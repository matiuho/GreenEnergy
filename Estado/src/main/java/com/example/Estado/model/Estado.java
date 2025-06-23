package com.example.Estado.model;

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
@Table(name = "estado")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Estados")
public class Estado {
    @Id
    @Schema(description = "ID unica de Estado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Condición del Estado", example = "Activo")
    @Column(nullable = false, length = 30)
    private String nombre;
}
