package com.example.categoria.model;

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
@Table(name = "categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Categorias")
public class Categoria {
    @Id
    @Schema(description = "ID unico de Categoria", example = "1", required = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    @Schema(description = "Nombre de la categoría (entre 1 y 30 caracteres)", example = "Técnico Postinstalación", required = true)
    @Column(nullable = false,length = 30)
    private String nombre;
}
