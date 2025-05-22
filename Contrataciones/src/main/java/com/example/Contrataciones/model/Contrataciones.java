package com.example.Contrataciones.model;

import java.sql.Date;

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
    private Long id;

    @Column(nullable = false)
    private Date fechacontrato;

    @Column(nullable = false)
    private Date fechainicio;

    @Column(nullable = false)
    private Date fechafin;

    @Column(nullable = false)
    private int total;

    @Column(nullable = false)
    private long idServicio;

    @Column(nullable = false)
    private long iddireccion;

}
