package com.brandon.animalapi.services;

import com.brandon.animalapi.data.AnimalRepository;
import com.brandon.animalapi.data.Mapper;
import com.brandon.animalapi.data.OwnerRepository;
import com.brandon.animalapi.dto.AnimalDto;
import com.brandon.animalapi.models.Animal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    private final AnimalRepository data;
    private final Mapper mapper;
    private final OwnerRepository ownerRepository;

    public AnimalService(AnimalRepository data, Mapper mapper, OwnerRepository ownerRepository) {
        this.data = data;
        this.mapper = mapper;
        this.ownerRepository = ownerRepository;
    }

    public List<AnimalDto> getAnimals() {
        return data.getAnimals().stream().map(mapper::toAnimalDto).collect(Collectors.toList());
    }

    public AnimalDto getAnimal(int index) {
        return mapper.toAnimalDto(data.getAnimal(index));
    }

    public int createAnimal(AnimalDto animal) {
        // try to get the owner, if owner cannot be found an exception is thrown. This is handled through
        // the global controller exception handler.
        ownerRepository.getOwner(animal.getOwnerId());
        return data.createAnimal(mapper.toAnimal(animal));
    }

    public void updateAnimal(AnimalDto animal, int id) {
        Animal cAnimal = mapper.toAnimal(animal);
        cAnimal.setId(id);
        data.updateAnimal(cAnimal);
    }

    public void deleteAnimal(int id) {
        data.deleteAnimal(id);
    }
}
