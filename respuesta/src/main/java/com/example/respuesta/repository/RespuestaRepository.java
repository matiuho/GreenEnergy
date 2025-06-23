package com.example.respuesta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.respuesta.model.Respuesta;

@Repository
public interface RespuestaRepository  extends JpaRepository<Respuesta,Long>{
    @Query("SELECT r FROM Respuesta r WHERE r.idsoporte = :idsoporte")
    List<Respuesta> findBySoporte(@Param("idsoporte") Long idsoporte);
}
