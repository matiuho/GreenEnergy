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
   
    }





    

}
