package com.example.direccion.Controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.direccion.model.Region;
import com.example.direccion.service.RegionService;

@WebMvcTest(RegionControllerTest.class)
public class RegionControllerTest {

  
    @MockBean
    private RegionService regionService;

     //crear un mock proporcionado por spring
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllRegion_returnsOKAndJson(){
        //crear una reserva ficticia para la respuesta del servicio
        List<Region> listaRegion = Arrays.asList(new Region(1L,"Region Prueba"));

        //identificar el comportamiento del servicio
        when(regionService.()).thenReturn(listaRegion);

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
