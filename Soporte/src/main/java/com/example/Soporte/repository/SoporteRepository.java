package com.example.Soporte.repository;

import com.example.Soporte.model.Soporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SoporteRepository extends JpaRepository<Soporte, Long> {
}