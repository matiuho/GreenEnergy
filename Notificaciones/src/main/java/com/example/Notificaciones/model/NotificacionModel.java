package com.example.Notificaciones.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //Indicar esta clase como una entidad JPA
@Table(name="Notificacion") //especifica el nombre de la tabla en la BD

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificacionModel {

    @Id //identifico la llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //indico que el campo se genera automáticamente
    private Integer id;

    @Column(unique = true, length = 12, nullable = false) //definir las restricciones del campo
    private String tipo;

    @Column(nullable = false, length = 100)
    private String mensaje;

  

    
}
