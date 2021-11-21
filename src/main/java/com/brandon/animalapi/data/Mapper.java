package com.brandon.animalapi.data;

import com.brandon.animalapi.dto.AnimalDto;
import com.brandon.animalapi.dto.OwnerDto;
import com.brandon.animalapi.models.Animal;
import com.brandon.animalapi.models.IDataModel;
import com.brandon.animalapi.models.Owner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Mapper {

    public Mapper() {

    }

    public AnimalDto toAnimalDto(Animal animal, int index) {
        AnimalDto returnObject = new AnimalDto();
        returnObject.setAge(animal.getAge());
        returnObject.setIndex(index);
        returnObject.setName(animal.getName());
        returnObject.setType(animal.getType());
        return returnObject;
    }

    public AnimalDto toAnimalDto(Map.Entry<Integer, IDataModel> animal) {
        Animal obj = (Animal) animal.getValue();
        AnimalDto returnObject = new AnimalDto();
        returnObject.setAge(obj.getAge());
        returnObject.setIndex(animal.getKey());
        returnObject.setName(obj.getName());
        returnObject.setType(obj.getType());
        return returnObject;
    }

    public Animal toAnimal(AnimalDto animal) {
        return new Animal();
    }

    public OwnerDto toOwnerDto(Owner owner){
        return new OwnerDto();
    }

    public Owner toOwner(OwnerDto owner){
        return new Owner();
    }
}
