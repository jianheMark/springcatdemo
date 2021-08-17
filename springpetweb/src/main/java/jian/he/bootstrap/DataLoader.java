package jian.he.bootstrap;

import jian.he.model.Owner;
import jian.he.model.Pet;
import jian.he.model.PetType;
import jian.he.model.Vet;
import jian.he.services.OwnerService;
import jian.he.services.PetTypeService;
import jian.he.services.VetService;
import jian.he.services.map.OwnerServiceMap;
import jian.he.services.map.VetServiceMap;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }


    @Override
    public void run(String... args) throws Exception {
        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCatPetType = petTypeService.save(cat);

        PetType dog = new PetType();
        cat.setName("dog");
        PetType savedDogPetType = petTypeService.save(dog);


        Owner owner1 = new Owner();
//        owner1.setId(1L);
        owner1.setFirstName("Dionne");
        owner1.setLastName("Warwick");
        owner1.setAddress("123 fairfield iowa");
        owner1.setCity("iowa");
        owner1.setTelephone("12324");
        ownerService.save(owner1);

        Pet mikesPet= new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPet().add(mikesPet);

        Owner owner2 = new Owner();
        owner2.setFirstName("The");
        owner2.setLastName("Crystals");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("34897");

        Pet fionasCat = new Pet();
        fionasCat.setName("Just Cat");
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        fionasCat.setPetType(savedCatPetType);
        owner2.getPet().add(fionasCat);


        ownerService.save(owner2);
        System.out.println("|||| Loading owners.");

        Vet vet1 = new Vet();
//        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
//        vet2.setId(2L);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vetService.save(vet2);
        System.out.println("Loading Vets");

    }
}
