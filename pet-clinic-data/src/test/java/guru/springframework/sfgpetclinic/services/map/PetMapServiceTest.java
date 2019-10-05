package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetMapServiceTest {


    PetMapService petMapService;
    String name = "pipi";
    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        Pet pet = Pet.builder().name(name).build();
        petMapService.save(pet);
    }

    @Test
    void findAll() {
        assertEquals(1,petMapService.findAll().size());
    }

    @Test
    void findById() {
        assertNotNull(petMapService.findById(1L));
    }

    @Test
    void save() {
        Pet pet2 = Pet.builder().name("abc").build();
        petMapService.save(pet2);
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void delete() {
        Pet pet = petMapService.findById(1L);
        petMapService.delete(pet);
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(1L);
        assertEquals(0, petMapService.findAll().size());
    }
}