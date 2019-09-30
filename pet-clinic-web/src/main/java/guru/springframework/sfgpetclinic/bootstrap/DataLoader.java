package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;


@Component
public class DataLoader implements CommandLineRunner {

    private final VisitService visitService;
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;
    private final SpecialtyService specialtyService;
    public DataLoader(VisitService visitService, SpecialtyService specialtyService,
                      OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, PetService petService) {
        this.visitService = visitService;
        this.specialtyService = specialtyService;
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
    }


    @Override
    public void run(String... args) throws Exception {
        Set set = (Set) petTypeService.findAll();
        int count = set.size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType cat = new PetType();
        cat.setName("cat");
        PetType catType = petTypeService.save(cat);

        PetType dog = new PetType();
        dog.setName("dog");
        PetType dogType = petTypeService.save(dog);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadio = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Fadillah");
        owner1.setAddress("123 fenbrook lane");
        owner1.setCity("Bloomington");
        owner1.setPhone("123456789");

        Pet pipi = new Pet();
        pipi.setPetType(catType);
        pipi.setName("Pipi");
        pipi.setBirthDate(LocalDate.now());
        pipi.setOwner(owner1);
        owner1.getPets().add(pipi);

        ownerService.save(owner1);

        Pet pet2 = new Pet();
        pet2.setPetType(dogType);
        pet2.setName("Keegan");
        pet2.setBirthDate(LocalDate.now());

        Owner owner2 = new Owner();
        owner2.setFirstName("David");
        owner2.setLastName("Fadillah");
        owner2.setAddress("121 PL SE");
        owner2.setCity("Seattle");
        owner2.setPhone("987654321");
        pet2.setOwner(owner2);
        owner2.getPets().add(pet2);
        ownerService.save(owner2);
        System.out.println("loaded owner..");

        Visit visit1 = new Visit();
        visit1.setDate(LocalDate.now());
        visit1.setDescription("health check");
        visit1.setPet(pet2);
        visitService.save(visit1);
        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("ab");
        vet1.getSpecialities().add(savedRadio);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Anna");
        vet2.setLastName("Hung");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);
        System.out.println("loaded vet..");
    }
}
