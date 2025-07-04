package com.example.Resena.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Resena.Model.Resena;


@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long>{
    @Query("SELECT r FROM Resena r WHERE r.idUsuario = :idUsuario")
    List<Resena> findByActivoTrue(@Param("idUsuario") Long idUsuario);

    List<Resena>findAllByActivoTrue();


}
