package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetTypeMapServiceTest {

    PetTypeMapService petTypeMapService;
    String type = "dog";
    @BeforeEach
    void setUp() {
        petTypeMapService = new PetTypeMapService();
        petTypeMapService.save(PetType.builder().name(type).build());
    }

    @Test
    void findAll() {
        assertEquals(1, petTypeMapService.findAll().size());
    }

    @Test
    void findById() {
        assertNotNull(petTypeMapService.findById(1L));
    }

    @Test
    void save() {
        PetType petType2 = PetType.builder().build();
        petTypeMapService.save(petType2);
        assertEquals(2, petTypeMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petTypeMapService.deleteById(1L);
        assertEquals(0, petTypeMapService.findAll().size());
    }

    @Test
    void delete() {
        petTypeMapService.delete(petTypeMapService.findById(1L));
        assertEquals(0, petTypeMapService.findAll().size());
    }
}