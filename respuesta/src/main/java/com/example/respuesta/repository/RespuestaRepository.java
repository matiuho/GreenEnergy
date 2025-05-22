package com.example.respuesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.respuesta.model.Respuesta;

@Repository
public interface RespuestaRepository  extends JpaRepository<Respuesta,Long>{

}
