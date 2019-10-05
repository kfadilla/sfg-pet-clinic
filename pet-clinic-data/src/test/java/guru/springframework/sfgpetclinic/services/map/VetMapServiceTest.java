package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VetMapServiceTest {

    VetMapService vetMapService;
    Specialty dental = Specialty.builder().description("aaa").build();
    @BeforeEach
    void setUp() {
        vetMapService = new VetMapService(new SpecialtyMapService());
        Set<Specialty> set = new HashSet<>();
        set.add(dental);
        vetMapService.save(Vet.builder().specialties(set).build());
    }

    @Test
    void findAll() {
        assertEquals(1, vetMapService.findAll().size());
    }

    @Test
    void findById() {
        assertNotNull(vetMapService.findById(1L));
    }

    @Test
    void save() {
        vetMapService.save(Vet.builder().specialties(new HashSet<>()).build());
        assertEquals(2, vetMapService.findAll().size());
    }

    @Test
    void delete() {
        vetMapService.delete(vetMapService.findById(1L));
        assertEquals(0, vetMapService.findAll().size());
    }

    @Test
    void deleteById() {
        vetMapService.deleteById(1L);
        assertEquals(0, vetMapService.findAll().size());
    }
}