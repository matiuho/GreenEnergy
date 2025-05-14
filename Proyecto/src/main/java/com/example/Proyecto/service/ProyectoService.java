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

        



}
