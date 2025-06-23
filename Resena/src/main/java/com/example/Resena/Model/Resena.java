package com.example.Resena.Model;

import java.time.LocalDate;

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
@Table(name="resena")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa una reseña o comentario de un usuario sobre un servicio.")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la reseña", example = "1")
    private Long idResena;

    @Column(nullable = false,length = 100)
    @Schema(description = "Contenido del comentario de la reseña")
    private String comentario;

    @Column(nullable = false)
    @Schema(description = "Fecha en la que se realizó el comentario")
    private LocalDate fechaComentario;

    @Column(nullable = false)
    @Schema(description = "ID del usuario que realizó la reseña")
    private Long idUsuario;

    @Column(nullable = false)
    @Schema(description = "ID del servicio al que pertenece esta reseña")
    private Long idServicio;

    @Column(nullable = false)
    @Schema(description = "Indica si la reseña está activa y visible (true) o deshabilitada (false)", example = "true")
    private boolean activo = true;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


}
