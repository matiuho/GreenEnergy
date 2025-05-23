package com.example.Autenticacion.model;

import java.time.LocalDate;

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
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(nullable = false, length = 30)
    private String apellido;

    @Column(nullable = false)
    private LocalDate fnacimiento;

    @Column(nullable = false, length = 30)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_Rol", nullable = false)
    @JsonIgnoreProperties("rol")
    private Rol rol;

    public Usuario(String nombre, String apellido, LocalDate fnacimiento, String password, Rol rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fnacimiento = fnacimiento;
        this.password = password;
        this.rol = rol;
    }

}
