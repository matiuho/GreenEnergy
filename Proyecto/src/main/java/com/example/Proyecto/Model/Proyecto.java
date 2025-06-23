package com.example.Proyecto.Model;


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
@Table(name = "proyectos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa un proyecto dentro del sistema, asociado a un usuario y una contratación.")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del proyecto")
    private Long idProyecto;

    @Column(nullable = false, length = 100)
    @Schema(description = "Comentario o descripción breve del proyecto")
    private String comentario;

    @Column(nullable = false)
    @Schema(description = "ID del estado actual del proyecto")
    private Long estadoId;

    @Column(nullable = false)
    @Schema(description = "ID del usuario propietario o responsable del proyecto")
    private Long usuarioId;

    @Column(nullable = false)
    @Schema(description = "ID de la contratación a la que está asociado este proyecto")
    private Long contratacionId;




}
