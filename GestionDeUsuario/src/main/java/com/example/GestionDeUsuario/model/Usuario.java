package com.example.GestionDeUsuario.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Detalles del usuario del sistema")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del usuario")
    private Long idUsuario;

    @Column(nullable = false, length = 50)
    @Schema(description = "Nombre del usuario")
    private String nombre;

    @Column(nullable = false, length = 50)
    @Schema(description = "Apellido del usuario")
    private String apellido;

    @Column(nullable = false, length = 50,unique = true)
    @Schema(description = "Correo electrónico único del usuario")
    private String email;

    @Column(nullable = false, length = 80)
    @Schema(description = "Contraseña encriptada del usuario")
    private String password;

    @Column(nullable = false)
    @Schema(description = "ID del rol asignado al usuario")
    private Long idRol;

    public Usuario(String nombre, String apellido, String email, String password, Long idRol) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.password = password;
    this.idRol = idRol;
}
}
