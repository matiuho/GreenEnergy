package com.example.resena.model;

import java.time.LocalDate;

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
@Table(name="reseña")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResena;

    @Column(nullable = false)
    private String comentario;
    
    @Column(nullable = false)
    private LocalDate fechaComentario;

    @Column(nullable = false)   
    private Long idUsuario;

    @Column(nullable = false)   
    private Long idServicio;

}
