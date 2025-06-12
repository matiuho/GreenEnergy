package com.example.Contrataciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Contrataciones.model.Contrataciones;

@Repository
public interface ContratacionesRepository extends JpaRepository<Contrataciones, Long> {
    //Query pra bucar por el ID de cada uno
    @Query("SELECT c FROM Contrataciones c WHERE c.idusuario = :idusuario")
    List<Contrataciones> findByIdUsuario(@Param("idusuario") Long idusuario);

    @Query("SELECT c FROM Contrataciones c WHERE c.idServicio = :idServicio")
    List<Contrataciones> findByIServicio(@Param("idServicio") Long idServicio);

    @Query("SELECT c FROM Contrataciones c WHERE c.idDireccion = :idDireccion")
    List<Contrataciones> findByIdDireccion(@Param("idDireccion") Long idDireccion);


}
