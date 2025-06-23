package com.example.Roles.model;

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
@Table(name = "roles")
@Schema(description = "Detalles del Rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    @Id
    @Schema(description = "ID único del Rol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @Schema(description = "Nombre del Rol")
    @Column (nullable = false,length = 20)
    private String nombre;

}
