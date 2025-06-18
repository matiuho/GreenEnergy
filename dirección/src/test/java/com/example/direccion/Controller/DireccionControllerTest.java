package com.example.direccion.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.direccion.controller.DireccionController;
import com.example.direccion.model.Direccion;
import com.example.direccion.service.DireccionService;

@WebMvcTest(DireccionController.class)
public class DireccionControllerTest {
    @MockBean
    private DireccionService direccionService;
    @Autowired
    private MockMvc mockMvc;
    //crear una reserva ficticia para la respuesta del servicio
        List<Direccion> listaDireccion = Arrays.asList(new Direccion(1L,"Avenida el Guanaco",1,null));

        //identificar el comportamiento del servicio
        when(direccionService


        //Ejecutar la funcion del controlador
        //Ejecutar el metodo GET (endpoint)
        //verficar que la respuesta sea 200 OK
        //validar que el archivo json contenga los id
        try{
            mockMvc.perform(get("api/reservas")).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1L));
        }catch(Exception e){

        }
        

    }



}
