package com.example.categoria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.categoria.model.Categoria;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria,Long> {

}
