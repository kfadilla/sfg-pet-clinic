package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class   OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    private final Long ownerid = 1L;
    private final String lastname = "Mike";
    @BeforeEach
    void SetUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        Owner owner = Owner.builder().id(1L).lastName(lastname).build();
        ownerMapService.save(owner);
    }
    @Test
    void findAll() {
        Set<Owner> set = ownerMapService.findAll();
        assertEquals(1, set.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(1L);
        assertEquals(ownerid, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(id, savedOwner.getId());
        assertEquals(2, ownerMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerid));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerid);
        assertEquals(0, ownerMapService.findAll().size());
    }
}