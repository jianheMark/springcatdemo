package jian.he.repositories;

import jian.he.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Owner findByLastName(String lastName);
}
