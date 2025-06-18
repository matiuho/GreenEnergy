package com.example.direccion.Controller;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.direccion.controller.DireccionController;
import com.example.direccion.model.Direccion;
import com.example.direccion.service.DireccionService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//cargar el controlador que se va a simular
@WebMvcTest(DireccionController.class)
public class DireccionControllerTest {


   
    @MockBean
    private DireccionService direccionService;

    //crear un mock proporcionado por spring
    @Autowired
    private MockMvc mockMvc;



    @Test
    void getAllDirecciones_returnsOkAndJson() {
        List<Direccion> listaDireccion = Arrays.asList(new Direccion(1L,"Avenida el Guanaco",1,null));

        //identificar el comportamiento del servicio
        when(direccionService.getDirecciones()).thenReturn(listaDireccion);


        //Ejecutar la funcion del controlador
        //Ejecutar el metodo GET (endpoint)
        //verficar que la respuesta sea 200 OK
        //validar que el archivo json contenga los id
        try{
            mockMvc.perform(get("api/reservas"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L));
        }catch(Exception e){

        }
        

    }



}
