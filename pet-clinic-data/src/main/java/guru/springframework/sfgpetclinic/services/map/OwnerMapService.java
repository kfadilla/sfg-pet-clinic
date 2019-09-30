package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@Profile("Map")
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private PetTypeService petTypeService;
    private PetService petService;


    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long aLong) {
        return super.findByID(aLong);
    }

    @Override
    public Owner save(Owner owner) {
        if (owner != null) {
            if (owner.getPets() != null) {
                owner.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
                    if (pet.getPetType().getId() != null) {
                        pet.setPetType(petTypeService.save(pet.getPetType()));
                    }
                } else {
                        throw new RuntimeException("Pet type is needed");
                    }
                    if (pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            return super.save(owner);
        }
        else {
            return null;
        }
    }

    @Override
    public Owner findByLastName(String lastName) {
        Map<Long, Owner> map = super.map;
        for (Owner owner : map.values()) {
            if (owner.getLastName().equals(lastName)) return owner;
        }
        System.out.println("owner not found");
        return null;
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public void deleteById(Long aLong) {
        super.deleteById(aLong);
    }
}
