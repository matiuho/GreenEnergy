package com.example.Soporte.repository;

import com.example.Soporte.model.Soporte;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SoporteRepository extends JpaRepository<Soporte, Long> {
    @Query("SELECT s FROM Soporte s WHERE s.idusuario = :idusuario")
    List<Soporte> findByUsuario(@Param("idusuario") Long idusuario);
}