package com.brandon.animalapi.services;

import com.brandon.animalapi.data.Mapper;
import com.brandon.animalapi.data.OwnerRepository;
import com.brandon.animalapi.dto.OwnerDto;
import com.brandon.animalapi.models.Owner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OwnerService {
    private final OwnerRepository data;
    private final Mapper mapper;

    public OwnerService(OwnerRepository data, Mapper mapper) {
        this.data = data;
        this.mapper = mapper;
    }

    public List<OwnerDto> getOwners() {
        return data.getOwners().stream().map(mapper::toOwnerDto).collect(Collectors.toList());
    }

    public OwnerDto getOwner(int id) {
        return mapper.toOwnerDto(data.getOwner(id));
    }

    public int createOwner(OwnerDto owner) {
        return data.createOwner(mapper.toOwner(owner));
    }

    public void updateOwner(OwnerDto owner, int id) {
        Owner cOwner = mapper.toOwner(owner);
        cOwner.setId(id);
        data.updateOwner(cOwner);
    }

    public void deleteOwner(int id) {
        data.removeOwner(id);
    }
}
