package com.example.Gestion_de_Mantenimiento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Gestion_de_Mantenimiento.Model.Mantenimiento;

public interface ManteRepository extends JpaRepository<Mantenimiento, Long> {
    

}
