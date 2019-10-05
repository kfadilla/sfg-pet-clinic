package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecialtyMapServiceTest {

    SpecialtyMapService specialtyMapService;
    String name = "Dental";

    @BeforeEach
    void setUp() {
        specialtyMapService = new SpecialtyMapService();
        specialtyMapService.save(Specialty.builder().description(name).build());
    }

    @Test
    void findAll() {
        assertEquals(1, specialtyMapService.findAll().size());
    }

    @Test
    void findById() {
        assertNotNull(specialtyMapService.findById(1L));
    }

    @Test
    void save() {
        specialtyMapService.save(Specialty.builder().build());
        assertEquals(2, specialtyMapService.findAll().size());
    }

    @Test
    void deleteById() {
        specialtyMapService.deleteById(1L);
        assertEquals(0, specialtyMapService.findAll().size());
    }

    @Test
    void delete() {
        specialtyMapService.delete(specialtyMapService.findById(1L));
        assertNull(specialtyMapService.findById(1L));
    }
}