package jian.he.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
@Entity
@Table(name ="owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends Person{
    @Builder
    public Owner(Long id, String firstName,String lastName,String address,
                 String city,String telephone,Set<Pet> pets){
        super(id,firstName,lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if(pets != null){
        this.pets = pets;}
    }

    @Column(name="address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name="telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public Pet getPet(String name){
        return getPet(name, false);
    }

    public Pet getPet(String name, boolean ignoreNew){
        name = name.toLowerCase();
        for(Pet pet :pets){
            if(!ignoreNew || !pet.isNew()){
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if(compName.equals(name)){
                    return pet;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "LastName='" + this.getLastName() + '\'' +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
