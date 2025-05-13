package com.example.Soporte.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "soporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoporteModel {
    private long idServicio;

    private String nombre;

    private String descripcion;

    private boolean disponibilidad;

}
