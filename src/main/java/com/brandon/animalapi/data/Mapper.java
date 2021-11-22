package com.brandon.animalapi.data;

import com.brandon.animalapi.dto.AnimalDto;
import com.brandon.animalapi.dto.OwnerDto;
import com.brandon.animalapi.models.Animal;
import com.brandon.animalapi.models.Owner;

public class Mapper {

    public static AnimalDto toAnimalDto(Animal animal) {
        AnimalDto returnObject = new AnimalDto();
        returnObject.setAge(animal.getAge());
        returnObject.setId(animal.getId());
        returnObject.setName(animal.getName());
        returnObject.setType(animal.getType());
        returnObject.setOwnerId(animal.getOwnerId());
        return returnObject;
    }

    public static Animal toAnimal(AnimalDto animal) {
        return new Animal(animal.getName(), animal.getType(), animal.getAge(), animal.getOwnerId());
    }

    public static OwnerDto toOwnerDto(Owner owner) {
        OwnerDto returnObject = new OwnerDto();
        returnObject.setName(owner.getName());
        returnObject.setAddress(owner.getAddress());
        returnObject.setFamilySize(owner.getFamilySize());
        returnObject.setHasKids(owner.isHasKids());
        returnObject.setId(owner.getId());

        return returnObject;
    }

    public static Owner toOwner(OwnerDto owner) {
        return new Owner(owner.getName(), owner.getAddress(), owner.isHasKids(), owner.getFamilySize());
    }
}
