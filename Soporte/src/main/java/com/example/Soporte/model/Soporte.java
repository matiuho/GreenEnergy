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

@Entity
@Table(name = "soporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Soporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSoporte;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, length = 100)
    private String descripcion;

    @Column(nullable = false)
    private Long idEstado;

    @Column(nullable = false)
    private Long idCategoria;

    @Column(nullable = false)
    private Long idusuario;
}
