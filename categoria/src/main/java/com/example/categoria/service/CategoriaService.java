package com.example.categoria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.categoria.model.Categoria;
import com.example.categoria.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    // buscar todas las categorias
    public List<Categoria> obtenerCategoria() {
        return categoriaRepository.findAll();
    }

    // buscar categorias por id
    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrado"));
    }

    // guardar categoria
    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // eliminar categoria
    public void eliminarservicio(Long id) {
        categoriaRepository.deleteById(id);
    }

}
