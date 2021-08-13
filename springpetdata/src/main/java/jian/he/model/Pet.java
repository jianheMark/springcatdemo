package jian.he.model;

import java.time.LocalDate;

public class Pet {
    private PerType petType;
    private Owner owner;
    private LocalDate birthDate;

    public PerType getPetType() {
        return petType;
    }

    public void setPetType(PerType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
