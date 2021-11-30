package com.brandon.animalapi.services;

import com.brandon.animalapi.data.AnimalRepository;
import com.brandon.animalapi.data.Mapper;
import com.brandon.animalapi.data.OwnerRepository;
import com.brandon.animalapi.dto.AnimalDto;
import com.brandon.animalapi.dto.OwnerDto;
import com.brandon.animalapi.models.Animal;
import com.brandon.animalapi.models.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    private final AnimalRepository data;
    private final OwnerRepository ownerRepository;

    public AnimalService(AnimalRepository data, OwnerRepository ownerRepository) {
        this.data = data;
        this.ownerRepository = ownerRepository;
    }

    public List<AnimalDto> getAnimals(String search) {
        if(search == null || search.isEmpty())
            return data.getAnimals().stream().map(Mapper::toAnimalDto).collect(Collectors.toList());

        //if a search param is provided: try to search for it
        /*
         * Search options:
         * - name:[text]
         * - type:[text]
         * - age:[number]
         * - owner: [id]
         */
        String[] searchParams = search.split(":");
        if(searchParams.length == 1)
            return getAnimalsByName(searchParams[0]);

        switch(searchParams[0]){
            case "type":
                return getAnimalsByType(searchParams[1]);
            case "age":
                return getAnimalsByAge(Integer.parseInt(searchParams[1]));
            case "owner":
                return getAnimalsByOwner(Integer.parseInt(searchParams[1]));
            case "name":
                return getAnimalsByName(searchParams[1]);
            default:
                return getAnimalsByName(searchParams[0]);
        }
    }

    public AnimalDto getAnimal(long index) {
        return Mapper.toAnimalDto(data.getAnimal(index));
    }

    public OwnerDto getAnimalOwner(long animalId) {
        return Mapper.toOwnerDto(data.getAnimal(animalId).getOwner());
    }

    public long createAnimal(AnimalDto animal) {
        // try to get the owner, if owner cannot be found an exception is thrown. This is handled through
        // the global controller exception handler.
        Owner owner = ownerRepository.getOwner(animal.getOwnerId());
        Animal newAnimal = Mapper.toAnimal(animal);
        newAnimal.setOwner(owner);
        return data.createAnimal(newAnimal);
    }

    public void updateAnimal(AnimalDto animal, long id) {
        Animal cAnimal = Mapper.toAnimal(animal);
        cAnimal.setId(id);
        cAnimal.setOwner(ownerRepository.getOwner(animal.getOwnerId()));
        data.updateAnimal(cAnimal);
    }

    public void deleteAnimal(long id) {
        data.deleteAnimal(id);
    }

    private List<AnimalDto> getAnimalsByName(String name){
        return data.getAnimals()
                .stream()
                .filter((index) -> index.getName().toLowerCase().contains(name.toLowerCase()))
                .map(Mapper::toAnimalDto)
                .collect(Collectors.toList());
    }

    private List<AnimalDto> getAnimalsByType(String type){
        return data.getAnimals()
                .stream()
                .filter((index) -> index.getType().contains(type))
                .map(Mapper::toAnimalDto)
                .collect(Collectors.toList());
    }

    private List<AnimalDto> getAnimalsByAge(int age){
        return data.getAnimals()
                .stream()
                .filter((index) -> index.getAge() == age)
                .map(Mapper::toAnimalDto)
                .collect(Collectors.toList());
    }

    private List<AnimalDto> getAnimalsByOwner(long id){
        return data.getAnimals()
                .stream()
                .filter((index) -> index.getOwner().getId() == id)
                .map(Mapper::toAnimalDto)
                .collect(Collectors.toList());
    }
}
