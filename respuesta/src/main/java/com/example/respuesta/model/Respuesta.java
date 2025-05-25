package com.example.respuesta.model;

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
@Table(name="respuesta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idrespuesta;
    
    @Column(nullable = false)
    private LocalDate fechaSoporte;

    @Column(nullable = false,length = 80)
    private String comentario;

    @Column(nullable = false,length = 25)
    private String tipousuario;

    @Column(nullable = false)
    private Long idsoporte;

}
