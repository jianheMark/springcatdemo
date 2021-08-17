package jian.he.services.map;

import jian.he.model.PetType;
import jian.he.services.PetTypeService;

import java.util.Set;

public class PetTypeServiceMap extends AbstractMapService<PetType,Long> implements PetTypeService{
    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public PetType save(PetType object) {
        return super.save(object);
    }

    @Override
    public void delete(PetType object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

}
