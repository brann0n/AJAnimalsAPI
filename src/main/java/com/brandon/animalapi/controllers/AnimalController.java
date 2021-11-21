package com.brandon.animalapi.controllers;

import com.brandon.animalapi.data.Mapper;
import com.brandon.animalapi.dto.AnimalDto;
import com.brandon.animalapi.models.IDataModel;
import com.brandon.animalapi.services.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("animals")
public class AnimalController {

    private final AnimalService animalService;
    private final Mapper mapper;

    public AnimalController(AnimalService animalService, Mapper mapper) {
        this.animalService = animalService;
        this.mapper = mapper;
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<AnimalDto>> get() {
        return ResponseEntity.ok(animalService.getAnimals()
                .entrySet()
                .stream()
                .map(mapper::toAnimalDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{animalId}")
    public @ResponseBody ResponseEntity<AnimalDto> get(@PathVariable(value="animalId") final Integer animalId) {
        return ResponseEntity.ok(mapper.toAnimalDto(animalService.getAnimal(animalId), animalId));
    }

    @PostMapping
    public @ResponseBody ResponseEntity<String> post() {
        return new ResponseEntity<>("POST Response", HttpStatus.OK);
    }

    @PutMapping
    public @ResponseBody ResponseEntity<String> put() {
        return new ResponseEntity<String>("PUT Response", HttpStatus.OK);
    }

    @DeleteMapping()
    public @ResponseBody ResponseEntity<String> delete() {
        return new ResponseEntity<>("DELETE Response", HttpStatus.OK);
    }
}
