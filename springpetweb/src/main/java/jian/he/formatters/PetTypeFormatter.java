package jian.he.formatters;

import jian.he.model.PetType;
import jian.he.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }
    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();
//        System.out.println("findPetTypes Collection test: "+ findPetTypes.size());
        for(PetType type: findPetTypes){
            if(type.getName().equals(s)){
                System.out.println(type.getClass());
                System.out.println(type);
                return type;
            }
        }
        throw new ParseException("type not found: " + s, 0);
    }
}
