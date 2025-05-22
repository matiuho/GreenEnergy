package com.example.servicio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    @Column(nullable = false, length = 100)
    private String nombre;
    
    
    @Column(nullable = false, length = 100)
    private String descripcion;

    @Column(nullable = false)
    private int precio;

    @Column(nullable = false, length = 20)
    private String disponibilidad; // para poner si esta "Disponible" o "No disponible"
    

}
