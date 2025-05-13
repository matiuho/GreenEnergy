package com.example.Gestion_de_Mantenimiento.Model;

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
@Table(name = "mantenimiento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long idMantenimiento;

    @Column(nullable = false, length = 30)
    private Data fechaInicio;

    @Column(nullable = false, length = 15)
    private String estado;

    @Column(nullable = false, length = 45)
    private String observaciones;
    
    @Column(nullable = false, length = 20)
    private String tipoMantenimiento;
    
    

    

}
