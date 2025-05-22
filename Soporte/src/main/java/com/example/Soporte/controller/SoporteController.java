package com.example.Soporte.controller;

import com.example.Soporte.model.Soporte;
import com.example.Soporte.service.SoporteService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @GetMapping
    public List<Soporte> listarTodos() {
        return soporteService.obtenerTodos();
    }

    @PostMapping
    public Soporte crearSoporte(@RequestBody Soporte soporte) {
        return soporteService.guardarSoporte(soporte);
    }

    @DeleteMapping("/{id}")
    public void eliminarSoporte(@PathVariable Long id) {
        soporteService.eliminarPorId(id);
    }
}