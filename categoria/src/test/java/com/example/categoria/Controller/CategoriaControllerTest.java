package com.example.categoria.Controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.doNothing;
import com.example.categoria.controller.CategoriaController;
import com.example.categoria.model.Categoria;
import com.example.categoria.service.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {
    @MockBean
    private CategoriaService categoriaService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllCategorias_returnsOkAndJson() {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1L);
        categoria.setNombre("prueba");

        List<Categoria> listaCategorias = Arrays.asList(categoria);
        when(categoriaService.obtenerCategoria()).thenReturn(listaCategorias);

        try {
            mockMvc.perform(get("api/categorias"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].idCategoria").value(1L));
        } catch (Exception e) {
        }

    }

    @Test
    void getCategoriaById_returnsOkAndJson() {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(1L);
        categoria.setNombre("prueba");

        when(categoriaService.getCategoriaById(1L)).thenReturn((categoria));
        try {
            mockMvc.perform(get("api/categoria/{id}", 1L))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.idCategoria").value(1L));
        } catch (Exception e) {

        }
    }

    @Test
    void crearCategoria_returnsCreatedAndJson() throws Exception {
        Categoria categoria = new Categoria();
        categoria.setNombre("prueba");

        when(categoriaService.saveCategoria(categoria)).thenReturn(categoria);
        mockMvc.perform(post("/api/categoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("prueba"));
    }

    @Test
    void eliminarCategoria_returnsNoContent() throws Exception {
        Long id = 1L;

        doNothing().when(categoriaService).eliminarcategoria(id);

        mockMvc.perform(delete("/api/categoria/{id}", id))
                .andExpect(status().isNoContent());
    }
}
