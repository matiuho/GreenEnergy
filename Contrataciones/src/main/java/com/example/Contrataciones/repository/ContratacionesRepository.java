package com.example.Contrataciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Contrataciones.model.Contrataciones;

@Repository
public interface ContratacionesRepository extends JpaRepository<Contrataciones, Long> {

}
