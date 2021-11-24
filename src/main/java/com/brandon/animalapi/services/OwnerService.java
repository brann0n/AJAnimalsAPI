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
public class OwnerService {
    private final OwnerRepository data;
    private final AnimalRepository animalRepository;

    public OwnerService(OwnerRepository data, AnimalRepository animalRepository) {
        this.data = data;
        this.animalRepository = animalRepository;
    }

    public List<OwnerDto> getOwners() {
        return data.getOwners().stream().map(Mapper::toOwnerDto).collect(Collectors.toList());
    }

    public OwnerDto getOwner(int id) {
        return Mapper.toOwnerDto(data.getOwner(id));
    }

    public List<AnimalDto> getOwnerAnimals(int ownerId) {
        return animalRepository.getAnimals().stream().filter((animal) -> animal.getOwner().getId() == ownerId).map(Mapper::toAnimalDto).collect(Collectors.toList());
    }

    public int createOwner(OwnerDto owner) {
        return data.createOwner(Mapper.toOwner(owner));
    }

    public void updateOwner(OwnerDto owner, int id) {
        Owner cOwner = Mapper.toOwner(owner);
        cOwner.setId(id);
        data.updateOwner(cOwner);
    }

    public void deleteOwner(int id) {
        data.removeOwner(id);
    }
}
