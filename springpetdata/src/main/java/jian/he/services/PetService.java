package jian.he.services;


import jian.he.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
