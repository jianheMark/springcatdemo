package jian.he.services.map;
import jian.he.model.Vet;
import jian.he.services.CrudService;
import jian.he.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet,Long> implements VetService {
    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
    @Override
    public Vet save(Vet object) {
        return super.save(object);
    }
    @Override
    public void delete(Vet object) {
        super.delete(object);
    }
    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }
}
