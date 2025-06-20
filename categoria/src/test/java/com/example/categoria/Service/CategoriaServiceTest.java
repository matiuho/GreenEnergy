package com.example.categoria.Service;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.categoria.model.Categoria;
import com.example.categoria.repository.CategoriaRepository;
import com.example.categoria.service.CategoriaService;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {
    @Mock
    private CategoriaRepository categoriaRepository;
    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void findAllCategorias_returnsListFromRepository(){
        List<Categoria> mockList = Arrays.asList(new Categoria(1L,"Prueba"));

        when(categoriaRepository.findAll()).thenReturn(mockList);
        List<Categoria> result = categoriaService.obtenerCategoria();
        assertThat(result).isEqualTo(mockList);

    }
    @Test
    void findCategoriasById_returnsCategoriaFromRepository(){
        Categoria mockCategoria = new Categoria(1L, "Prueba");

        when(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.of(mockCategoria));
        Categoria result = categoriaService.getCategoriaById(1L);
        assertThat(result).isEqualTo(mockCategoria);
    }
    @Test
    void saveCategoria_returnsSavedCategoria() {
        Categoria mockCategoria = new Categoria(1L, "Prueba");

        when(categoriaRepository.save(mockCategoria)).thenReturn(mockCategoria);
        Categoria result = categoriaService.saveCategoria(mockCategoria);
        assertThat(result).isEqualTo(mockCategoria);
    }
    @Test
    void deleteCategoria_callsRepositoryDelete() {
        Long id = 1L;
        categoriaService.eliminarservicio(id);
    }
}
