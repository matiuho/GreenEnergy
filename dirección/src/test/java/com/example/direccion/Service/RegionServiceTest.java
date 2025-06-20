package com.example.direccion.Service;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import static org.mockito.Mockito.when;

import java.util.Arrays;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.direccion.model.Region;
import com.example.direccion.repository.RegionRepository;
import com.example.direccion.service.RegionService;

@ExtendWith(MockitoExtension.class)
public class RegionServiceTest {

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionService regionService;

    @Test 
    void findAll_returnsListFromRepository(){
        List<Region> listaRegion = Arrays.asList(new Region(1,"Region de Prueba"));
        when(regionRepository.findAll()).thenReturn(listaRegion);

        List<Region> result = regionService.getRegiones();

        assertThat(result).isEqualTo(listaRegion);
    }

    @Test
    void findById_returnsRegionById() {
        Integer id = 1;
        Region region = new Region(id, "Region de Prueba");

        when(regionRepository.findById(id)).thenReturn(java.util.Optional.of(region));

        Region result = regionService.obtenerRegionPorId(id);

        assertThat(result).isEqualTo(region);
    }

}
