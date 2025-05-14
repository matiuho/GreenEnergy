package com.example.Proyecto.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Proyecto.model.Proyecto;
import com.example.Proyecto.service.ProyectoService;

@RestController
@RequestMapping("api/proyecto")
public class ProyectoController {
    @Autowired
    private ProyectoService proyectoService; //se conecta a las funciones del servicio
    
    
    //endpoint buscar todos los clientes
    @GetMapping
    public ResponseEntity<List<Proyecto>> obtenerProyectos(){
        //lista auxiliar de clientes
        List<Proyecto> listaProyectos = proyectoService.list();
        //valido si la lista esta vacia
        if(listaProyectos.isEmpty()){
            //retorno codigo 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaProyectos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> obtenerProyectoPorId(@PathVariable Long id){
        //busco el cliente por su ID
        try {
            Proyecto proyecto = proyectoService.getProeyctoPorId(id);
            return ResponseEntity.ok(proyecto);
        } catch (Exception e) {
            //retorno codigo 404
            return ResponseEntity.notFound().build();
        }   
    }

    //endpoint para crear un nuevo proyecto
    @PostMapping
    public ResponseEntity<Proyecto> guardarProyecto(@RequestBody Proyecto nuevo){
        return ResponseEntity.status(201).body(proyectoService.saveProyecto(nuevo));
    }





    

}
