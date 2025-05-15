package com.example.Proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Proyecto.model.Proyecto;
import com.example.Proyecto.repository.ProyectoRepository;

@Service
@Transactional
public class ProyectoService {

        @Autowired
        private ProyectoRepository proyectoRepository;

        //metodo para consultar todos los proyectos
        public List<Proyecto> list() {
            return proyectoRepository.findAll();
        }

        //metodo para buscar un proyecto por su ID
        public Proyecto getProyectoPorId(Long id){
        return proyectoRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Proyecto No encontrado"));
        }

        //metodo para agregar un proyecto nuevo
        public Proyecto saveProyecto(Proyecto nuevo){
        return proyectoRepository.save(nuevo);
        }







}
