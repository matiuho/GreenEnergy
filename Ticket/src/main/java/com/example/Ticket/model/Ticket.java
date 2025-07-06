package com.example.Ticket.model;

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
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Detalles de un Ticket de soporte o seguimiento")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Schema(description = "ID unico de Categoria", example = "1", required = true)
    private Long id;

    @Column(nullable = false,length = 100)
    @Schema(description = "Descripción del Ticket", example = "Problema con el panel solar", required = true)
    private String descripcion;

    @Column(nullable = false)
    @Schema(description = "ID del usuario que creó el Ticket", example = "1", required = true)
    private Long idusuario;

    @Column(nullable = false)
    @Schema(description = "ID del soporte asociado al Ticket", example = "1", required = true)
    private Long idsoporte;
}
