package com.example.direccion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Table(name = "comuna")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComuna;

    @Column(nullable = false, length = 30)
    private String nombre;


    @ManyToOne
    @JoinColumn(name = "id_region") // clave foránea en la tabla 
    @JsonIgnoreProperties("comunas") // evita recursión si Region tiene lista de comunas
    private Region region;
    //constructor para prcargar las tablas de la base de datos
    public Comuna(String nombre, Region region) {
        
        this.nombre = nombre;
        this.region = region;
    }
}

