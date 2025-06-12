package com.example.Proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Proyecto.model.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long>{
    @Query("SELECT p FROM Proyecto p WHERE p.usuarioId = :usuaroId")
    List<Proyecto> findByIdUsuario(@Param("usuarioId") Long usuarioId);

}
