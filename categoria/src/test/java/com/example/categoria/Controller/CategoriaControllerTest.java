package com.example.categoria.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.categoria.controller.CategoriaController;
import com.example.categoria.model.Categoria;

@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {
    @MockBean
    private CategoriaController categoriaController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCategorias_returnsOkAndJson()  {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1L);
        categoria.setNombre("prueba");

        List<Categoria> listaCategorias = Arrays.asList(categoria);
        when(categoriaController.obtenerTodos()).thenReturn(listaCategorias);

        try {
            mockMvc.perform(get("api/categorias"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idCategoria").value(1L));
        } catch (Exception e) {
        }

    }
}
        
    


