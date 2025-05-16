package com.example.Estado.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Estado.model.Estado;
import com.example.Estado.service.EstadoService;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {
    @Autowired
    private EstadoService estadoService; //se conecta a las funciones del servicio

    //endpoint buscar todos los clientes
    @GetMapping
    public ResponseEntity<List<Estado>> obtenerEstado(){
        //lista auxiliar de estados
        List<Estado> lista = estadoService.getEstados();
        //valido si la lista esta vacia
        if (lista.isEmpty()){
            //retorno codigo 204
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    //endpoint para buscar un estado mediante su id
    @GetMapping("/{id}")
    public ResponseEntity<Estado> obtenerEstadoPorId(@PathVariable Long id){
        try {
            //verificar si existe el estado
            Estado estado = estadoService.getEstadoPorId(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //endpoint para crear un nuevo estado
    @PostMapping
    public ResponseEntity<Estado> guardarEstado(@RequestBody Estado nuevo){
        return ResponseEntity.status(201).body(estadoService.saveEstado(nuevo));
    }


}
