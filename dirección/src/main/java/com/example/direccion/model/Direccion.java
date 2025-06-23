package com.example.direccion.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "direccion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dirección")
public class Direccion {

    @Id
    @Schema(description = "ID unico de Dirección")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;

    @Schema(description = "Nombre de la dirección")
    @Column(nullable = false, length = 50)
    private String nombre;

    @Schema(description = "ID unico de comuna")
    @ManyToOne
    @JoinColumn(name = "comuna_id", nullable = false)
    private Comuna comuna;

    @Schema(description = "ID unico de usuario")
    @Column(nullable = false)
    private Long idUsuario;
}