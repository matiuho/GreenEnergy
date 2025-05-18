package com.example.direccion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.direccion.model.Direccion;
@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    

}
