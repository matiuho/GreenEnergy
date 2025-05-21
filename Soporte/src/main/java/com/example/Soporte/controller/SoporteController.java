package com.example.Soporte.controller;

import com.example.Soporte.model.Soporte;
import com.example.Soporte.service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/soportes")
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

    @GetMapping("/fecha")
    public List<Soporte> buscarPorFecha(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) {
        return soporteService.obtenerPorFecha(fecha);
    }
}