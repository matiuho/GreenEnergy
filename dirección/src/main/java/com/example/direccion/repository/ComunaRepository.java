package com.example.direccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.direccion.model.Comuna;
@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Long> {
    
}
