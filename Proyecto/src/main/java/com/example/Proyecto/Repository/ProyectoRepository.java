package com.example.Proyecto.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Proyecto.Model.Proyecto;


@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long>{
    @Query("SELECT p FROM Proyecto p WHERE p.usuarioId = :usuarioId")
    List<Proyecto> findByIdUsuario(@Param("usuarioId") Long usuarioId);

}

