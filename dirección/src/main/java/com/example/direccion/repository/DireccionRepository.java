package com.example.direccion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.direccion.model.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
    @Query("SELECT d FROM Direccion d WHERE d.idUsuario = :idUsuario")
    List<Direccion> findByIdUsuario(@Param("idUsuario") Long idUsuario);

}
