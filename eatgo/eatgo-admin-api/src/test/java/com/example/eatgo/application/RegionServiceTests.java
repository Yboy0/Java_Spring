package com.example.eatgo.application;

import com.example.eatgo.domain.Region;
import com.example.eatgo.domain.RegionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RegionServiceTests {


    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        regionService=new RegionService(regionRepository);
    }

    @Test
    public void getRegions(){
        List<Region> mockRegions = new ArrayList<>();
        mockRegions.add(Region.builder().name("Seoul").build());

        given(regionRepository.findAll()).willReturn(mockRegions);

        List<Region> regions = regionService.getRegions();

        Region region = regions.get(0);
        assertThat(region.getName()).isEqualTo("Seoul");
    }

    @Test
    public void addRegion(){
        regionService=new RegionService(regionRepository);
        Region region = regionService.addRegion("Seoul");

        verify(regionRepository).save(any());

        assertThat(region.getName()).isEqualTo("Seoul");

    }

}