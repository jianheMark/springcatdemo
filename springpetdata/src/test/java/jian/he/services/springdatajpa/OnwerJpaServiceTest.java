package jian.he.services.springdatajpa;

import jian.he.model.Owner;
import jian.he.repositories.OwnerRepository;
import jian.he.repositories.PetRepository;
import jian.he.repositories.PetTypeRepository;
import jian.he.services.OwnerService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OnwerJpaServiceTest {
    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @Mock
    PetRepository petRepository;
    Owner returnOwner;

    @InjectMocks
    OnwerJpaService ownerService;


    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = ownerService.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner savedOwner = ownerService.save(ownerToSave);
        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {

    }

    @Test
    void findAll() {
//        Set<Owner> returnOwnerSet = new HashSet<>();
//        returnOwnerSet.add(Owner.builder().id(1L).build());
//        returnOwnerSet.add(Owner.builder().id(2L).build());
//////        HashSet<Owner> a = new HashSet<>(ownerRepository.findAll());
//        when(ownerRepository.findAll()).thenReturn(returnOwnerSet);
//        Set<Owner> owners = ownerService.findAll();
//        assertNotNull(owners);
//        assertEquals(2,owners.size());
    }

    @Test
    void findByLastName() {
        Owner returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();

        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = ownerService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME,smith.getLastName());

        verify(ownerRepository).findByLastName(any());

    }

    @Test
    //this case will return many.
    void findAllByLastNameLike() {
//        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(
//                Owner.builder().id(1L).build(),
//                Owner.builder().id(2L).build()));
    }
}