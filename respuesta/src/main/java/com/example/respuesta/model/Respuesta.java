package com.example.respuesta.model;

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
@Table(name="respuesta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa la respuesta a una consulta o solicitud de soporte.")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único de la respuesta", example = "1", required = true)
    private Long idrespuesta;
    
    @Column(nullable = false)
    @Schema(description = "Fecha en la que se generó la respuesta", example = "2023-03-15", required = true)
    private LocalDate fechaRespuesta;

    @Column(nullable = false,length = 80)
    @Schema(description = "Contenido del comentario de la respuesta", example = "Respuesta generada con éxito", required = true)
    private String comentario;

    @Column(nullable = false,length = 25)
    @Schema(description = "Tipo de usuario que emitió la respuesta", example = "Administrador", required = true)
    private String tipousuario;

    @Column(nullable = false)
    @Schema(description = "ID del ticket o solicitud de soporte a la que pertenece esta respuesta", example = "1", required = true)
    private Long idsoporte;

}
