package jian.he.services.springdatajpa;

import jian.he.model.Owner;
import jian.he.model.Pet;
import jian.he.model.PetType;
import jian.he.repositories.OwnerRepository;
import jian.he.repositories.PetRepository;
import jian.he.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetJpaService implements PetService {
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public PetJpaService(PetRepository petRepository, OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }


    @Override
    public Pet findById(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }


//    @Transactional
    @Override
    public Pet save(Pet object) {
        System.out.println("Trying to do the save operation.");
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }
}
