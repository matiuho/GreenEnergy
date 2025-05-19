package com.example.direccion.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "region")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Region {

    @Id
    private int idRegion;

    @Column(nullable = false, length = 100)
    private String nombre;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("region") // evita recursión al serializar
    private List<Comuna> comunas = new ArrayList<>();
    //constructor para prcargar las tablas de la base de datos
    public Region(int idRegion  ,  String nombre) {
        this.nombre = nombre;
        this.idRegion = idRegion;
    }
}


