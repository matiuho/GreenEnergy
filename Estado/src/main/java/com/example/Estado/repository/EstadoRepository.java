package com.example.Estado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Estado.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
