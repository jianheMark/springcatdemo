package jian.he.repositories;

import jian.he.model.PetType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetType,Long> {
}
