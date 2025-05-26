package com.example.Contrataciones.model;

import java.time.LocalDate;

import org.hibernate.annotations.CollectionId;

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
public class Contrataciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContratacion;

    @Column(nullable = false)
    private LocalDate fechaContratacion;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false)
    private Long idServicio;

    @Column(nullable = false)
    private Long idDireccion;

    @Column(nullable=false)
    private Long idusuario;

    

    

}
