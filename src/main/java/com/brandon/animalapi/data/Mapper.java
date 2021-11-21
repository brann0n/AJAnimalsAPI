package com.brandon.animalapi.data;

import com.brandon.animalapi.dto.AnimalDto;
import com.brandon.animalapi.dto.OwnerDto;
import com.brandon.animalapi.models.Animal;
import com.brandon.animalapi.models.Owner;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Mapper() {

    }

    public AnimalDto toAnimalDto(Animal animal) {
        AnimalDto returnObject = new AnimalDto();
        returnObject.setAge(animal.getAge());
        returnObject.setId(animal.getId());
        returnObject.setName(animal.getName());
        returnObject.setType(animal.getType());
        returnObject.setOwnerId(animal.getOwnerId());
        return returnObject;
    }

    public Animal toAnimal(AnimalDto animal) {
        return new Animal(animal.getName(), animal.getType(), animal.getAge(), animal.getOwnerId());
    }

    public OwnerDto toOwnerDto(Owner owner) {
        OwnerDto returnObject = new OwnerDto();
        returnObject.setName(owner.getName());
        returnObject.setAddress(owner.getAddress());
        returnObject.setFamilySize(owner.getFamilySize());
        returnObject.setHasKids(owner.isHasKids());
        returnObject.setId(owner.getId());

        return returnObject;
    }

    public Owner toOwner(OwnerDto owner) {
        return new Owner(owner.getName(), owner.getAddress(), owner.isHasKids(), owner.getFamilySize());
    }
}
